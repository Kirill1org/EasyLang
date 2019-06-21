package com.easylang;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateViewModel extends ViewModel {
    private static final String TAG = TranslateViewModel.class.getSimpleName();

    private static final String[] LANGUAGES = {"en", "ru"};

    private final MutableLiveData<String> translatedText = new MutableLiveData<>();

    public static String[] getLANGUAGES() {
        return LANGUAGES;
    }

    public MutableLiveData<String> getTranslatedText() {
        return translatedText;
    }

    public void translate(String text, String lang) {
        Log.d(TAG, "translate() called with: text = [" + text + "], lang = [" + lang + "]");

        NetworkService.getInstance()
                .getJSONApi()
                .getTranslate(BuildConfig.YandexTranslateAPIKEY, text, lang)
                .enqueue(new Callback<TranslateResponse>() {
                    @Override
                    public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                        if (!response.isSuccessful()) {
                            Log.d(TAG, "Something went wrong! Code: " + response.code());
                            return;
                        }
                        TranslateResponse translateResponse = response.body();

                        translatedText.setValue(translateResponse.getText().get(0));
                    }

                    @Override
                    public void onFailure(Call<TranslateResponse> call, Throwable t) {
                        Log.d(TAG, "Network failure!", t);
                    }
                });
    }
}
