package com.edward.myapplication.API;

import com.edward.myapplication.model.modelrequest.ClothesReq;
import com.edward.myapplication.model.modelrequest.FavoriteReq;
import com.edward.myapplication.model.modelrespon.ResGetClothes;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;
import com.edward.myapplication.model.modelrespon.ResGetPerson;
import com.edward.myapplication.model.modelrespon.Respon;
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

    @GET("GetAllClothes")
    Observable<ResGetListClothes> GetAllClothes();

    @GET("GetClothesWhere")
    Observable<ResGetClothes> GetClothesWhere(@Query("id") int id);

    @POST("UpdateClothes")
    Observable<Respon> UpdateClothes(@Body ClothesReq clothesReq);

    @POST("CreateClothes")
    Observable<Respon> CreateClothes(@Body ClothesReq clothesReq); // id truyen mac dinh la 1

    @POST("DeleteClothes")
    Observable<Respon> DeleteClothes(@Query("id") int id);

    @POST("GetClothesProperties")
    Observable<ResGetClothes> GetClothesProperties(@Query("id") int id);

    @GET("Login")
    Observable<ResGetPerson> Login(@Query(("_email")) String _email, @Query(("_psw")) String _psw);

    @GET("GetAllFavoritesOf")
    Observable<ResGetListClothes> GetAllFavoritesOf(@Query(("userID")) int userID);

    @POST("AddToFavorite")
    Observable<Respon> AddToFavorite(@Body FavoriteReq favoriteReq);

    @POST("RemoveFromFavorite")
    Observable<Respon> RemoveFromFavorite(@Body FavoriteReq favoriteReq);

    @GET("GetClothesFromSellerAndCategory")
    Observable<ResGetClothes> GetClothesFromSellerAndCategory(@Query("idSellerReq") int idSellerReq, @Query("idCategoryReq") int idCategoryReq);

    @GET("GetClothesWhereCategory")
    Observable<ResGetClothes> GetClothesWhereCategory(@Query("idCategoryReq") int idCategoryReq);
}
