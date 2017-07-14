package com.github.mlposey;

/**
 * A convenience class for creating {@link Translator} objects.
 *
 * @see Translator
 * @see Key
 */
public class Translators {

    /**
     * Returns a Translator for a service that is associated with apiKey.
     *
     * @throws RuntimeException if the environment variable indicated by apiKey.var()
     *                          is not defined in the system
     */
    public static Translator get(final Key apiKey) throws RuntimeException {
        if (apiKey.toString() == null) {
            throw new RuntimeException("API key " + apiKey.var() + " not set");
        }

        switch (apiKey) {
            case YANDEX:
                return new YandexTranslator(
                        apiKey,
                        "https://translate.yandex.net/api/v1.5/tr.json/"
                );
            case GOOGLE:
                return new GoogleTranslator(
                        apiKey,
                        "https://translation.googleapis.com/language/translate/v2"
                );
        }
        return null;
    }
}
