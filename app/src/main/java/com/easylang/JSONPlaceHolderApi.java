package com.easylang;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/api/v1.5/tr.json/translate")
    public Call<TranslateResponse> getTranslate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);

}
