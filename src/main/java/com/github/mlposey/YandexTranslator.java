package com.github.mlposey;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * <p>A {@link Translator} for the Yandex.Translate service.</p>
 *
 * Docs: https://tech.yandex.com/translate/doc/dg/concepts/api-overview-docpage/ <br>
 * Terms: https://yandex.com/legal/offer_translate_api/index.html
 */
public class YandexTranslator extends Translator {
    // The response code of a successful request
    private final int okResponse = 200;

    /**
     * A JSON response from the API.
     *
     * Useful for Gson marshaling with translate and identify.
     */
    protected class JSONResponse {
        public int code;
        public String lang;
        public String[] text;
    }

    /**
     * Constructs a YandexTranslator object.
     *
     * @param apiKey the Yandex.Translate API key
     * @param host the host URL (e.g., https://translate.yandex.net/api/v1.5/tr.json/)
     */
    public YandexTranslator(final Key apiKey, String host) {
        super(apiKey, host);
    }

    @Override
    protected Text[] translateImpl(Text from, Language to) throws TranslationError {
        String responseBody = getResponseBody(
                "translate",
                "key", getApiKey(),
                "text", from,
                "lang", from.isLanguageSet() ?
                        from.getLanguage().concat(to) :
                        to
        );

        JSONResponse response = new Gson()
                .fromJson(responseBody, JSONResponse.class);

        if (response.code != okResponse) {
            throw new TranslationError(response.code);
        }

        Text[] translations = new Text[response.text.length];

        for (int i = 0; i < response.text.length; i++) {
            translations[i] = new Text(response.text[i], to);
        }
        return translations;
    }

    @Override
    protected Language[] identifyImpl(String mysteryText) {
        String responseBody = getResponseBody(
                "detect",
                "key", getApiKey(),
                "text", mysteryText
        );
        JSONResponse response = new Gson().fromJson(responseBody, JSONResponse.class);

        if (response.code != okResponse) {
            throw new TranslationError(response.code);
        }

        return new Language[]{Language.valueOf(response.lang.toUpperCase())};
    }

    @Override
    protected boolean hasSupportImpl(final Language from, final Language to) {
        String responseBody = getResponseBody(
                "getLangs",
                "key", getApiKey(),
                "ui", from
        );
        JsonObject response = new JsonParser().parse(responseBody).getAsJsonObject();

        JsonPrimitive prim = response.getAsJsonPrimitive("code");
        if (prim != null) {
            throw new TranslationError(prim.getAsInt());
        }

        return response.getAsJsonObject("langs").has(to.toString());
    }

    private String getResponseBody(String path, Object... params) {
        return HttpRequest.post(
                getHost() + path,
                true,
                params
        ).body();
    }
}
