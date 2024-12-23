package com.example.graoco;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://10.0.2.2/grao-co/api/";
    @GET("products/list")
    Call<List<Coffe>> getCoffe();
    @GET("products/list/{id}")
    Call<List<Coffe>> getCoffeDescription(@Path("id") int id);
    @POST("users/login")
    Call<User> loginUser(@Body RequestBody requestBody);
    @POST("users/")
    Call<User> createUser(@Body RequestBody requestBody);
    @DELETE("products/delete-product/{id}")
    Call<Coffe> deleteCoffe(@Path("id") int id);
}

