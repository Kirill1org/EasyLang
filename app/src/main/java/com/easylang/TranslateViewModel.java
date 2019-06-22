package com.easylang;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateViewModel extends AndroidViewModel {
    private static final String TAG = TranslateViewModel.class.getSimpleName();

    private final MutableLiveData<String> liveDataTranslatedText = new MutableLiveData<>();

    public TranslateViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getLiveDataTranslatedText() {
        return liveDataTranslatedText;
    }

    public void translate(String text, String from, String to) {
        String lang = from + "-" + to;

        NetworkService.getInstance()
                .getJSONApi()
                .getTranslate(BuildConfig.YandexTranslateAPIKEY, text, lang)
                .enqueue(new Callback<TranslateResponse>() {
                    @Override
                    public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplication(), "Something went wrong! Code: " + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        TranslateResponse translateResponse = response.body();

                        String translation = translateResponse.getText().get(0);
                        liveDataTranslatedText.setValue(translation);

                        DictionaryDAO dictionaryDAO = AppDatabase.getInstance(getApplication()).dictionaryDAO();
                        if ((dictionaryDAO.getByText(text, translation) == 0)) {
                            dictionaryDAO.insert(new Dictionary(from, to, text, translation));
                        } else {
                            Toast.makeText(getApplication(), "Word is already added", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TranslateResponse> call, Throwable t) {
                        Log.d(TAG, "Network failure!", t);
                    }
                });
    }
}
