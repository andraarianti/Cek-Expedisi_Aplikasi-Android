package com.example.rajaongkir.data.city;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Rajaongkir{

	@SerializedName("query")
	private List<Object> query;

	@SerializedName("results")
	private List<Results> results;

	@SerializedName("status")
	private Status status;

	public void setQuery(List<Object> query){
		this.query = query;
	}

	public List<Object> getQuery(){
		return query;
	}

	public void setResults(List<Results> results){
		this.results = results;
	}

	public List<Results> getResults(){
		return results;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}

	@Override
	public String toString(){
		return
				"Rajaongkir{" +
						"query = '" + query + '\'' +
						",results = '" + results + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}