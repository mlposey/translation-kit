package com.github.mlposey;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * <p>A {@link Translator} for the Google Translate service.</p>
 *
 * Docs: https://cloud.google.com/translate/docs/ <br>
 * Terms: https://cloud.google.com/translate/attribution
 */
public class GoogleTranslator extends Translator {
    private JsonParser parser;

    /**
     * Constructs a GoogleTranslator object.
     *
     * @param apiKey the Google Translate API key
     * @param host the host URL (e.g., https://translation.googleapis.com/language/translate/v2)
     */
    public GoogleTranslator(final Key apiKey, String host) {
        super(apiKey, host);
        parser = new JsonParser();
    }

    @Override
    protected Text[] translateImpl(Text from, Language to) throws TranslationError {
        HttpRequest request = getRequest(
                "","key", getApiKey(),
                "q", from.toString(),
                "target", to.toString()
        );

        if (!request.ok()) { throw new TranslationError(request.code()); }

        JsonArray translations = getDataObject(request.body())
                .getAsJsonArray("translations");

        ArrayList<Text> texts = new ArrayList<>();
        for (JsonElement translation : translations) {
            texts.add(new Text(translation
                    .getAsJsonObject()
                    .get("translatedText")
                    .getAsString(), to));
        }

        return texts.toArray(new Text[texts.size()]);
    }

    @Override
    protected Language[] identifyImpl(String mysteryText) throws TranslationError {
        HttpRequest request = getRequest(
                "/detect",
                "key", getApiKey(),
                "q", mysteryText
        );

        if (!request.ok()) { throw new TranslationError(request.code()); }

        // Multiple strings can be submitted to the service. Each string's results
        // would be in a languageGroup. However, batch string requests are not
        // currently implemented, which means languageGroup only has one element.
        // TODO: Batch string requests.
        JsonArray languageGroups = getDataObject(request.body())
                .getAsJsonArray("detections");

        ArrayList<Language> results = new ArrayList<>();
        for (JsonElement languageGroup : languageGroups) {
            for (JsonElement language : languageGroup.getAsJsonArray()) {
                try {
                    results.add(Language.valueOf(language
                            .getAsJsonObject()
                            .get("language")
                            .getAsString()
                            .toUpperCase()));
                } catch (IllegalArgumentException e) {
                    // Google will set the language to "und", which is not
                    // a valid Language value, if the text cannot be identified.
                    if (e.getMessage().contains("Language.UND")) {
                        // TODO: Normalize this across Translators.
                        throw new TranslationError(404);
                    }
                }
            }
        }
        return results.toArray(new Language[results.size()]);
    }

    @Override
    protected boolean hasSupportImpl(Language from, Language to) throws TranslationError {
        HttpRequest request = getRequest(
                "/languages",
                "key", getApiKey(),
                "target", to.toString()
        );

        if (!request.ok()) { throw new TranslationError(request.code()); }

        JsonArray sourceLanguages = getDataObject(request.body())
                .getAsJsonArray("languages");

        for (JsonElement sourceLanguage : sourceLanguages) {
            if (sourceLanguage
                    .getAsJsonObject()
                    .get("language")
                    .getAsString()
                    .equals(from.toString())) {
                return true;
            }
        }
        return false;
    }

    private HttpRequest getRequest(String path, Object... params) {
        return HttpRequest.post(
                getHost() + path, true,
                params
        ).header("Content-Length", 0).send("");
    }

    private JsonObject getDataObject(String requestBody) {
        return parser.parse(requestBody).getAsJsonObject().getAsJsonObject("data");
    }
}
