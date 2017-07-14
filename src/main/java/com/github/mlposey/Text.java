package com.github.mlposey;

/**
 * A string of text that can be associated with a language.
 */
public class Text {
    private String content;
    private final Language lang;

    /**
     * Constructs a Text object that has no language.
     */
    public Text(String content) {
        this(content, null);
    }

    /**
     * Constructs a Text object with a language.
     *
     * @param content the contents of the text
     * @param lang the language that content is written in
     */
    public Text(String content, final Language lang) {
        this.content = content;
        this.lang = lang;
    }

    /**
     * Returns true if the text is associated with a language; false otherwise
     */
    public boolean isLanguageSet() { return lang != null; }

    /** Returns the language that the text is written in. */
    public Language getLanguage() { return lang; }

    /** Returns the text's contents. */
    public String toString() { return content; }
}
