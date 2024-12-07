package com.example.graoco;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://10.0.2.2/grao-co/api/products/list";
    @GET("list")
    Call<List<Coffe>> getCoffe();
}

