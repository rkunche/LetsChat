package com.techsol.letschat.network;

import com.techsol.letschat.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {
    @GET("/api")
    Call<User> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);
}
