package com.easylang;

class DictionaryItem {
    private String langFrom;
    private String langTo;
    private String textFrom;
    private String textTo;

    public DictionaryItem(String langFrom, String langTo, String textFrom, String textTo) {
        this.langFrom = langFrom;
        this.langTo = langTo;
        this.textFrom = textFrom;
        this.textTo = textTo;
    }

    public String getLangFrom() {
        return langFrom;
    }

    public String getLangTo() {
        return langTo;
    }

    public String getTextFrom() {
        return textFrom;
    }

    public String getTextTo() {
        return textTo;
    }
}
