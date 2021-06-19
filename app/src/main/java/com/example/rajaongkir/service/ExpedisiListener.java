package com.example.rajaongkir.service;

public interface ExpedisiListener<T>{
    void onFailure(String message);
    void onResponse(T items);
}
