package com.edward.adminapp.API;

import com.edward.adminapp.model.modelrequest.CategoryReq;
import com.edward.adminapp.model.modelrespon.ResGetCategory;
import com.edward.adminapp.model.modelrespon.ResGetListCategory;
import com.edward.adminapp.model.modelrespon.ResGetListPerson;
import com.edward.adminapp.model.modelrespon.ResGetPerson;
import com.edward.adminapp.model.modelrespon.Respon;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_SERVICE = "https://edward.kynalab.com/api/";

    ServiceAPI serviceApi = new Retrofit.Builder().baseUrl(BASE_SERVICE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServiceAPI.class);

    @GET("GetAllPerson")
    Observable<ResGetListPerson> GetAllPerson();

    @GET("Login")
    Observable<ResGetPerson> Login(@Query(("_email")) String _email, @Query(("_psw")) String _psw);

    @GET("GetAllPersonByType")
    Observable<ResGetListPerson> GetAllPerson(int type);

    @GET("GetPersonWhere")
    Observable<ResGetPerson> GetPersonWhere(int id);

    @GET("GetAllCategory")
    Observable<ResGetListCategory> GetAllCategory();

    @GET("GetCategoryWhere")
    Observable<ResGetCategory> GetCategoryWhere(int id);

    @POST("UpdateCategory")
    Observable<Respon> UpdateCategory(@Body CategoryReq categoryReq);

    @POST("CreateCategory")
    Observable<Respon> CreateCategory(@Body CategoryReq categoryReq); // id tu tanwg neen la cu set mac dinh la 1

    @POST("DeleteCategory")
    Observable<Respon> DeleteCategory(@Body int id);

}