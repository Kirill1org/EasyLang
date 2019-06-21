package com.easylang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private final String API_KEY ="trnsl.1.1.20190621T175118Z.a0c622c856958eaa.e154b31556ed94517d7c23482eacb0e450446195";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        NetworkService.getInstance()
                .getJSONApi()
                .getTranslate(API_KEY, "car", "en-ru")
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
