package com.example.rajaongkir.data.cost;

import com.google.gson.annotations.SerializedName;

public class CostResponse{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}

	@Override
 	public String toString(){
		return 
			"CostResponse{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}