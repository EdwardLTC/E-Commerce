package com.edward.myapplication.api;

import com.edward.myapplication.model.modelrequest.ClothesReq;
import com.edward.myapplication.model.modelrequest.FavoriteReq;
import com.edward.myapplication.model.modelrequest.PersonReq;
import com.edward.myapplication.model.modelrequest.VoucherReq;
import com.edward.myapplication.model.modelrequest.BillReq;
import com.edward.myapplication.model.modelrespon.BillRes;
import com.edward.myapplication.model.modelrespon.ResBill;
import com.edward.myapplication.model.modelrespon.ResGetBillPayment;
import com.edward.myapplication.model.modelrespon.ResGetCategory;
import com.edward.myapplication.model.modelrespon.ResGetClothes;
import com.edward.myapplication.model.modelrespon.ResGetListBill;
import com.edward.myapplication.model.modelrespon.ResGetListCategory;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;
import com.edward.myapplication.model.modelrespon.ResGetListVoucher;
import com.edward.myapplication.model.modelrespon.ResGetPerson;
import com.edward.myapplication.model.modelrespon.ResGetStatistical;
import com.edward.myapplication.model.modelrespon.ResGetVoucher;
import com.edward.myapplication.model.modelrespon.ResSellerIncome;
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

    @GET("GetClothesProperties")
    Observable<ResGetListProperties> GetAllClothesProperties(@Query("idClothes") int id);

    @GET("Login")
    Observable<ResGetPerson> Login(@Query(("_email")) String _email, @Query(("_psw")) String _psw);

    @POST("UpdatePerson")
    Observable<Respon> UpdatePerson (@Body PersonReq personReq);

    @GET("GetAllFavoritesOf")
    Observable<ResGetListClothes> GetAllFavoritesOf(@Query(("userID")) int userID);

    @POST("AddToFavorite")
    Observable<Respon> AddToFavorite(@Body FavoriteReq favoriteReq);

    @POST("RemoveFromFavorite")
    Observable<Respon> RemoveFromFavorite(@Body FavoriteReq favoriteReq);

    @GET("GetClothesFromSellerAndCategory")
    Observable<ResGetListClothes> getAllClothesFromSellerAndCategory(@Query("idSellerReq") int idSellerReq, @Query("idCategoryReq") int idCategoryReq);

    @GET("GetClothesWhereCategory")
    Observable<ResGetListClothes> GetClothesWhereCategory(@Query("idCategoryReq") int idCategoryReq);

    @GET("GetAllCategory")
    Observable<ResGetListCategory> GetAllCategory();

    @GET("GetCategoryWhere")
    Observable<ResGetCategory> GetCategoryWhere(@Query("id") int id);

    @GET("GetAllVoucher")
    Observable<ResGetListVoucher> GetAllVoucher();

    @GET("GetAllVoucherOf")
    Observable<ResGetListVoucher> GetAllVoucherOf(@Query("idSeller") int idSeller);

    @GET("GetVoucherWhere")
    Observable<ResGetVoucher> GetVoucherWhere(@Query("id") int id);

    @POST("UpdateVoucher")
    Observable<Respon> UpdateVoucher(@Body VoucherReq voucherReq);

    @POST("CreateVoucher")
    Observable<Respon> CreateVoucher(@Body VoucherReq voucherReq); // id = 1

    @POST("DeleteVoucher")
    Observable<Respon> DeleteVoucher(@Query("id") int id);

    @GET("GetClothesFrom")
    Observable<ResGetListClothes> getAllClothesFromSeller(@Query("idSellerReq")int idSeller);

    @GET("GetPersonWhere")
    Observable<ResGetPerson> GetPersonWhere(@Query("Id") int id);

    @POST("CreatePerson")
    Observable<Respon> CreatePerson(@Body PersonReq personReq); // id tu tanwg neen la cu set mac dinh la 1

    @GET("UpdateStatusBill")
    Observable<Respon> UpdateStatusBill (@Query("status") String status, @Query("idBill") int idBill);

    @GET("GetBillOfUser")
    Observable<ResGetListBill> GetBillOfUser (@Query("idUser") int idUser);

    @GET("GetBillOfSeller")
    Observable<ResGetListBill> GetBillOfSeller (@Query("idSeller") int idSeller);

    @GET("MarkBillComplete")
    Observable<Respon> MarkBillComplete (@Query("idBill") int idBill);

    @POST("CreateBill")
    Observable<ResBill> CreateBill (@Body BillReq billReq);

    @GET("GetClothesFromFromBill")
    Observable<ResGetListClothes> GetClothesFromFromBill(@Query("billId") int billId);

    @GET("GetBillWhereCompleted")
    Observable<ResGetListBill> GetBillWhereCompleted();


    @GET("GetBillWhereNotCompleted")
    Observable<ResGetListBill> GetBillWhereNotCompleted();

    @GET("GetBillPayment")
    Observable<ResGetBillPayment> GetBillPayment(@Query("idBill") int idBill);

    @GET("GetStatistical")
    Observable<ResGetStatistical> GetStatistical();

    @GET("SellerIncome")
    Observable<ResSellerIncome> SellerIncome(@Query("idSeller") int idSeller);

    @GET("GetClothesFromBillCustomer")
    Observable<ResGetListClothes>GetClothesFromBillCustomer(@Query("idCus") int idCus, @Query("billId") int billId);
}