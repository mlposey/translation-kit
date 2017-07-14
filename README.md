# Translation Kit
Translation kit is an asynchronous abstraction on top of common translation
services. It offers built-in support for Yandex.Translate (v1.5) and Google
Translate (v2). Its primary aim is to offer a modern Java interface while
allowing easy switching between services during program execution.

You can find the Javadocs [here](https://mlposey.github.io/translation-kit/).

## API Keys
Before using the kit, you should acquire a keys for the services you plan to
use. Set the key values as environment variables with the following
names:
* Google Translate  
`GOOGLE_API_KEY`
* Yandex.Translate  
`YANDEX_API_KEY`

On certain operating systems, you may need to start your IDE through a terminal
in order for the values to be recognized.

## Using Translators
Translators perform operations asynchronously and are easily used with lambdas.
You can perform simple operations, ignoring possible errors. Although this is
isn't suggest, here is an example:
```Java
Translator translator = Translators.get(Key.YANDEX);

translator.translate(new Text("test"), Language.NL, possibleResults -> {
    if (possibleResults != null)
        System.out.println(possibleResults[0]);
});
```
Error handling relies on an additional consumer that handles a TranslationError.
You can retrieve the service's response code from such errors and act accordingly.
```Java
Translator translator = Translators.get(com.github.mlposey.Key.YANDEX);

translator.translate(new Text("test"), Language.NL,
    possibleResults -> {
        if (possibleResults != null)
            System.out.println(possibleResults[0]);
    },
    error -> {
        if (error.getCode() == 401)
            System.out.println("The API key is invalid.");
    }
);
```
The Translator class defines operations for:
* translating text and retrieving possible translations
* detecting possible languages a text belongs to
* determining if translation is supported from one language to another