package com.github.mlposey;

/**
 * A key for a translation service's API.
 *
 * <p>Keys are set as environment variables. The name of the variable is retrievable
 * with {@link Key#var()}, and the key itself is mapped to the toString method.</p>
 *
 * Standard Names: <br>
 * - Yandex.Translate: YANDEX_API_KEY <br>
 * - Google Translate: GOOGLE_API_KEY
 */
public enum Key {
    YANDEX {
        @Override
        public String var() { return "YANDEX_API_KEY"; }
    },
    GOOGLE {
        @Override
        public String var() { return "GOOGLE_API_KEY"; }
    };

    /** Returns the name of the key's environment variable. */
    public abstract String var();

    /** Returns the value of the key. */
    @Override
    public String toString() {
        return System.getenv(var());
    }
}
