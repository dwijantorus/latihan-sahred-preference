package com.example.latihansharedpreference;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseApi> login(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register-user")
    Call<ResponseApi> registerUser(
            @Field("username") String username,
            @Field("full_name") String fullName,
            @Field("email") String email,
            @Field("password") String password);

    @GET("get-profile")
    Call<ResponseApi> getDetailMovie(@Query("token") String token);
}