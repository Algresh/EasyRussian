package ru.tulupov.alex.easyrussian.models.api;


import java.util.Map;

import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface WordAPI {

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Observable<Response<Object>> translateWord(@FieldMap Map<String, String> map);
}
