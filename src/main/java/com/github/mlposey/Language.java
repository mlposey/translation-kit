package com.github.mlposey;

/**
 * <p>An ISO 639-1 code for a language.</p>
 *
 * Based on: http://data.okfn.org/data/core/language-codes
 */
public enum Language {
            AA, // Afar
            AB, // Abkhazian
            AE, // Avestan
            AF, // Afrikaans
            AK, // Akan
            AM, // Amharic
            AN, // Aragonese
            AR, // Arabic
            AS, // Assamese
            AV, // Avaric
            AY, // Aymara
            AZ, // Azerbaijani
            BA, // Bashkir
            BE, // Belarusian
            BG, // Bulgarian
            BH, // Bihari languages
            BI, // Bislama
            BM, // Bambara
            BN, // Bengali
            BO, // Tibetan
            BR, // Breton
            BS, // Bosnian
            CA, // Catalan; Valencian
            CE, // Chechen
            CH, // Chamorro
            CO, // Corsican
            CR, // Cree
            CS, // Czech
            CU, // Church Slavic; Old Slavonic; Church Slavonic; Old Bulgarian; Old Church Slavonic
            CV, // Chuvash
            CY, // Welsh
            DA, // Danish
            DE, // German
            DV, // Divehi; Dhivehi; Maldivian
            DZ, // Dzongkha
            EE, // Ewe
            EL, // Greek, Modern (1453-)
            EN, // English
            EO, // Esperanto
            ES, // Spanish; Castilian
            ET, // Estonian
            EU, // Basque
            FA, // Persian
            FF, // Fulah
            FI, // Finnish
            FJ, // Fijian
            FO, // Faroese
            FR, // French
            FY, // Western Frisian
            GA, // Irish
            GD, // Gaelic; Scottish Gaelic
            GL, // Galician
            GN, // Guarani
            GU, // Gujarati
            GV, // Manx
            HA, // Hausa
            HE, // Hebrew
            HI, // Hindi
            HO, // Hiri Motu
            HR, // Croatian
            HT, // Haitian; Haitian Creole
            HU, // Hungarian
            HY, // Armenian
            HZ, // Herero
            IA, // Interlingua (International Auxiliary com.github.mlposey.Language Association)"
            ID, // Indonesian
            IE, // Interlingue; Occidental
            IG, // Igbo
            II, // Sichuan Yi; Nuosu
            IK, // Inupiaq
            IO, // Ido
            IS, // Icelandic
            IT, // Italian
            IU, // Inuktitut
            JA, // Japanese
            JV, // Javanese
            KA, // Georgian
            KG, // Kongo
            KI, // Kikuyu; Gikuyu
            KJ, // Kuanyama; Kwanyama
            KK, // Kazakh
            KL, // Kalaallisut; Greenlandic
            KM, // Central Khmer
            KN, // Kannada
            KO, // Korean
            KR, // Kanuri
            KS, // Kashmiri
            KU, // Kurdish
            KV, // Komi
            KW, // Cornish
            KY, // Kirghiz; Kyrgyz
            LA, // Latin
            LB, // Luxembourgish; Letzeburgesch
            LG, // Ganda
            LI, // Limburgan; Limburger; Limburgish
            LN, // Lingala
            LO, // Lao
            LT, // Lithuanian
            LU, // Luba-Katanga
            LV, // Latvian
            MG, // Malagasy
            MH, // Marshallese
            MI, // Maori
            MK, // Macedonian
            ML, // Malayalam
            MN, // Mongolian
            MR, // Marathi
            MS, // Malay
            MT, // Maltese
            MY, // Burmese
            NA, // Nauru
            NB, // Bokmål, Norwegian; Norwegian Bokmål
            ND, // Ndebele, North; North Ndebele
            NE, // Nepali
            NG, // Ndonga
            NL, // Dutch; Flemish
            NN, // Norwegian Nynorsk; Nynorsk, Norwegian
            NO, // Norwegian
            NR, // Ndebele, South; South Ndebele
            NV, // Navajo; Navaho
            NY, // Chichewa; Chewa; Nyanja
            OC, // Occitan (post 1500); Provençal
            OJ, // Ojibwa
            OM, // Oromo
            OR, // Oriya
            OS, // Ossetian; Ossetic
            PA, // Panjabi; Punjabi
            PI, // Pali
            PL, // Polish
            PS, // Pushto; Pashto
            PT, // Portuguese
            QU, // Quechua
            RM, // Romansh
            RN, // Rundi
            RO, // Romanian; Moldavian; Moldovan
            RU, // Russian
            RW, // Kinyarwanda
            SA, // Sanskrit
            SC, // Sardinian
            SD, // Sindhi
            SE, // Northern Sami
            SG, // Sango
            SI, // Sinhala; Sinhalese
            SK, // Slovak
            SL, // Slovenian
            SM, // Samoan
            SN, // Shona
            SO, // Somali
            SQ, // Albanian
            SR, // Serbian
            SS, // Swati
            ST, // Sotho, Southern
            SU, // Sundanese
            SV, // Swedish
            SW, // Swahili
            TA, // Tamil
            TE, // Telugu
            TG, // Tajik
            TH, // Thai
            TI, // Tigrinya
            TK, // Turkmen
            TL, // Tagalog
            TN, // Tswana
            TO, // Tonga (Tonga Islands)
            TR, // Turkish
            TS, // Tsonga
            TT, // Tatar
            TW, // Twi
            TY, // Tahitian
            UG, // Uighur; Uyghur
            UK, // Ukrainian
            UR, // Urdu
            UZ, // Uzbek
            VE, // Venda
            VI, // Vietnamese
            VO, // Volapük
            WA, // Walloon
            WO, // Wolof
            XH, // Xhosa
            YI, // Yiddish
            YO, // Yoruba
            ZA, // Zhuang; Chuang
            ZH, // Chinese
            ZU, // Zulu
            TEST; // For testing

    /** Returns a true, lowercase code for the language. */
    public String toString() {
        return this.name().toLowerCase();
    }

    /** Returns a concatenation of this code followed by a hyphen and the code of 'lang'. */
    public String concat(final Language lang) {
        return this + "-" + lang;
    }
}
