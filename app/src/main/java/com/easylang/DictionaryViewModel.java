package com.easylang;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DictionaryViewModel extends AndroidViewModel {

    private DictionaryDAO dictionaryDAO;

    public DictionaryViewModel(@NonNull Application application) {
        super(application);
        dictionaryDAO = AppDatabase.getInstance(getApplication()).dictionaryDAO();
    }

    public LiveData<List<Dictionary>> getAll() {
        return dictionaryDAO.getAll();
    }

    public LiveData<List<Dictionary>> getTranslateByLang(String langFrom, String langTo) {
        return dictionaryDAO.getTranslateByLang(langFrom, langTo);
    }

    public void delete(Dictionary dictionary) {
        dictionaryDAO.delete(dictionary);
    }
}
