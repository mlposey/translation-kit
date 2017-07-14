package com.github.mlposey;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

/**
 * A test environment for classes that extend Translator.
 *
 * If you choose to implement a new service (i.e., by creating a new class that
 * extends {@link Translator}) you can run these tests by creating a test class
 * that extends this class and passing the {@link Key} to this constructor.
 *
 * TranslatorTest is primarily meant to test client-server operations and whether
 * subclasses uphold the expected functionality of abstract components. The quality
 * of translations is subject to the algorithms a service uses, not to client-side
 * logic.
 */
public class TranslatorTest {
    protected final int timeout = 10;
    protected final Key key;

    protected Language[] langs;
    protected boolean hasSupport;
    protected Text[] translations;

    protected int code;

    public TranslatorTest(Key key) { this.key = key; }


    /**
     * Translator.identify should recognize 'test' as being from the
     * English language. While assuming English support isn't too far
     * a leap, language support could result in issues here.
     */
    @Test
    public void testIdentify() throws InterruptedException {
        langs = null;

        Translator translator = Translators.get(key);
        translator.identify("test", l -> langs = l);
        translator.shutdown(timeout);

        Assert.assertNotNull(langs);
        Assert.assertEquals(Language.EN, langs[0]);
    }

    /**
     * Translator.identify should produce an exception when supplied text
     * text that does not belong to any language.
     */
    @Test
    public void testIdentify_handleError() throws InterruptedException {
        code = 0;

        Translator translator = Translators.get(key);
        translator.identify(";)", l -> langs = l,
                err -> code = err.getCode());
        translator.shutdown(timeout);

        Assert.assertNotEquals(0, code);
    }

    /**
     * Translator.hasSupport should produce a true value for English to Russian
     * translations. The two services provided (i.e., Yandex.Translate and Google
     * Translate) offer this direction, but others you add may not. If this fails,
     * confirm translation support for the service.
     */
    @Test
    public void testHasSupport() throws InterruptedException {
        hasSupport = false;

        Translator translator = Translators.get(key);
        translator.hasSupport(Language.EN, Language.RU, b -> hasSupport = b);
        translator.shutdown(timeout);

        Assert.assertTrue(hasSupport);
    }

    /**
     * Translator.translate should produce a text which is converted from
     * one language (English) to another (Dutch). This translation direction
     * exists for Yandex.Translate and Google Translate as of writing this test.
     * If a new service is added and failures arise here, ensure it supports
     * this direction.
     */
    @Test
    public void testTranslate() throws InterruptedException{
        translations = null;

        Translator translator = Translators.get(key);
        translator.translate(new Text("dog", Language.EN), Language.NL,
                texts -> translations = texts);
        translator.shutdown(timeout);

        Assert.assertNotNull(translations);
        Assert.assertTrue(Arrays
                .stream(translations)
                .anyMatch(t -> t.toString().equalsIgnoreCase("hond")));
    }

    /**
     * Translator.translate should produce an error if supplied text that belongs
     * to no language. It is assumed that using a generated UUID as the text
     * content will not produce valid results.
     */
    @Test
    public void testTranslate_handleError() throws InterruptedException {
        code = 0;

        Translator translator = Translators.get(key);
        translator.translate(new Text(UUID.randomUUID().toString(), Language.EN),
                Language.TEST, texts -> translations = texts,
                err -> code = err.getCode());
        translator.shutdown(timeout);

        Assert.assertNotEquals(0, code);
    }
}
