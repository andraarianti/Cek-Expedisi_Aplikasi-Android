package com.example.rajaongkir.service;

import com.example.rajaongkir.data.city.CityResponse;
import com.example.rajaongkir.data.cost.CostResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExpedisiAPI {
    String URL_BASE = "https://api.rajaongkir.com";

    @Headers({
            "key: cd082aa17946b65d6293d3b2692cfebd",
            "content-type: application/x-www-form-urlencoded"
    })
    @GET("/starter/city")
    Call<CityResponse> getKota();

    @Headers({
            "key: cd082aa17946b65d6293d3b2692cfebd",
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/starter/cost")
    Call<CostResponse> getHarga(@Field("origin") String origin,
                                @Field("destination") String destination,
                                @Field("weight") int weight,
                                @Field("courier") String courier);
}
