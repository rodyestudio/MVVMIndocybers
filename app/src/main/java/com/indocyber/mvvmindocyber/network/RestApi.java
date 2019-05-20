package com.indocyber.mvvmindocyber.network;

import com.indocyber.mvvmindocyber.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}
