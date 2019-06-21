package com.easylang;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dictionary {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String langFrom;
    private String langTo;
    private String textFrom;
    private String textTo;

    public Dictionary(String langFrom, String langTo, String textFrom, String textTo) {
        this.langFrom = langFrom;
        this.langTo = langTo;
        this.textFrom = textFrom;
        this.textTo = textTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLangFrom() {
        return langFrom;
    }

    public void setLangFrom(String langFrom) {
        this.langFrom = langFrom;
    }

    public String getLangTo() {
        return langTo;
    }

    public void setLangTo(String langTo) {
        this.langTo = langTo;
    }

    public String getTextFrom() {
        return textFrom;
    }

    public void setTextFrom(String textFrom) {
        this.textFrom = textFrom;
    }

    public String getTextTo() {
        return textTo;
    }

    public void setTextTo(String textTo) {
        this.textTo = textTo;
    }
}
