package com.easylang;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        NetworkService.getInstance()
                .getJSONApi()
                .getTranslate(BuildConfig.YandexTranslateAPIKEY, "car", "en-ru")
                .enqueue(new Callback<TranslateResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TranslateResponse> call, @NonNull Response<TranslateResponse> response) {
                        System.out.println(response.body().getText());
                        Toast.makeText(getApplicationContext(), response.body().getText().get(0), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<TranslateResponse> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network error!", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
