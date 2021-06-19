package com.example.rajaongkir.service;

import android.util.Log;

import com.example.rajaongkir.data.city.CityResponse;
import com.example.rajaongkir.data.city.Results;
import com.example.rajaongkir.data.cost.CostResponse;
import com.example.rajaongkir.data.cost.CostsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpedisiService {
    private Retrofit retrofit = null;

    public ExpedisiAPI getAPI(){
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(ExpedisiAPI.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ExpedisiAPI.class);
    }

    public void getCityAPI(final ExpedisiListener<List<Results>> listener){
        getAPI().getKota().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                CityResponse data = response.body();
                if (data!=null && data.getRajaongkir()!=null){
                    listener.onResponse(data.getRajaongkir().getResults());
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void getCostAPI(final ExpedisiListener<List<CostsItem>> listener, String origin, String destination, int weight, String courier){
        getAPI().getHarga(origin,destination,weight,courier).enqueue(new Callback<CostResponse>() {
            @Override
            public void onResponse(Call<CostResponse> call, Response<CostResponse> response) {
                CostResponse data = response.body();
                Log.d("data", String.valueOf(data));
                if(data!=null && data.getRajaongkir()!=null){

                    listener.onResponse(data.getRajaongkir().getResults().get(0).getCosts());
                    Log.d("hasil2", String.valueOf(data.getRajaongkir().getResults().get(0).getCode()));
                }

            }

            @Override
            public void onFailure(Call<CostResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
                Log.d("pesan error",t.getMessage());
            }
        });
    }
}
