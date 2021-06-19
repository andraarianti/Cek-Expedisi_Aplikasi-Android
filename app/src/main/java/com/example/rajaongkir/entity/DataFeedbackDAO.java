package com.example.rajaongkir.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface DataFeedbackDAO {
    @Insert
    Long insertData(DataFeedback dataFeedback);

    @Query("Select * from feedback_db")
    List<DataFeedback> getData();

    @Update
    int updateData(DataFeedback item);

    @Delete
    void deleteData(DataFeedback item);
}
