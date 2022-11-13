package com.edward.adminapp.API;

import com.edward.adminapp.model.modelrespon.ResGetListPerson;
import com.edward.adminapp.model.modelrespon.ResGetPerson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_SERVICE = "https://edward.kynalab.com/api/";

    ServiceAPI serviceApi = new Retrofit.Builder().baseUrl(BASE_SERVICE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServiceAPI.class);

    @GET("GetAllPerson")
    Observable<ResGetListPerson> GetAllPerson();

    @GET("/Login")
    Observable<ResGetPerson> Login(@Query(("_email")) String email, @Query(("_psw")) String psw);
}