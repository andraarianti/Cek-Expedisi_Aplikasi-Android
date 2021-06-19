package com.example.rajaongkir.data.city;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("province")
	private String province;

	@SerializedName("id")
	private String id;

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"province = '" + province + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}