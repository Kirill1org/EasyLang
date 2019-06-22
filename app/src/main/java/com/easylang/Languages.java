package com.easylang;

public class Languages {
    private static final String[] LANG_CODES = {"af", "am", "ar", "az", "ba", "be", "bg", "bn", "bs", "ca", "ceb", "cs", "cy", "da", "de", "el", "en", "eo", "es", "et", "eu", "fa", "fi", "fr", "ga", "gd", "gl", "gu", "he", "hi", "hr", "ht", "hu", "hy", "id", "is", "it", "ja", "jv", "ka", "kk", "km", "kn", "ko", "ky", "la", "lb", "lo", "lt", "lv", "mg", "mhr", "mi", "mk", "ml", "mn", "mr", "mrj", "ms", "mt", "my", "ne", "nl", "no", "pa", "pap", "pl", "pt", "ro", "ru", "si", "sk", "sl", "sq", "sr", "su", "sv", "sw", "ta", "te", "tg", "th", "tl", "tr", "tt", "udm", "uk", "ur", "uz", "vi", "xh", "yi", "zh"};
    private static final String[] LANG_NAMES = {"Afrikaans", "Amharic", "Arabic", "Azerbaijani", "Bashkir", "Belarusian", "Bulgarian", "Bengali", "Bosnian", "Catalan", "Cebuano", "Czech", "Welsh", "Danish", "German", "Greek", "English", "Esperanto", "Spanish", "Estonian", "Basque", "Persian", "Finnish", "French", "Irish", "Scottish Gaelic", "Galician", "Gujarati", "Hebrew", "Hindi", "Croatian", "Haitian", "Hungarian", "Armenian", "Indonesian", "Icelandic", "Italian", "Japanese", "Javanese", "Georgian", "Kazakh", "Khmer", "Kannada", "Korean", "Kyrgyz", "Latin", "Luxembourgish", "Lao", "Lithuanian", "Latvian", "Malagasy", "Mari", "Maori", "Macedonian", "Malayalam", "Mongolian", "Marathi", "Hill Mari", "Malay", "Maltese", "Burmese", "Nepali", "Dutch", "Norwegian", "Punjabi", "Papiamento", "Polish", "Portuguese", "Romanian", "Russian", "Sinhalese", "Slovak", "Slovenian", "Albanian", "Serbian", "Sundanese", "Swedish", "Swahili", "Tamil", "Telugu", "Tajik", "Thai", "Tagalog", "Turkish", "Tatar", "Udmurt", "Ukrainian", "Urdu", "Uzbek", "Vietnamese", "Xhosa", "Yiddish", "Chinese"};

    public static String getLangCode(int i) {
        return LANG_CODES[i];
    }

    public static String getLangName(int i) {
        return LANG_NAMES[i];
    }
}
