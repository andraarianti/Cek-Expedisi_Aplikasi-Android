package com.example.rajaongkir.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "feedback_db")

public class DataFeedback {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "expedisi")
    private String expedisi;
    @ColumnInfo(name = "feedback")
    private String feedback;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpedisi() {
        return expedisi;
    }

    public void setExpedisi(String expedisi) {
        this.expedisi = expedisi;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
