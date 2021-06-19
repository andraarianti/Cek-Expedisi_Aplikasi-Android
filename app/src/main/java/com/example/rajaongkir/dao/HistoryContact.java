package com.example.rajaongkir.dao;

import android.view.View;

import com.example.rajaongkir.entity.DataFeedback;
import com.example.rajaongkir.entity.FeedbackDatabase;

import java.util.List;

public interface HistoryContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataFeedback> list);
        void editData(DataFeedback item);
        void deleteData(DataFeedback item);
    }

    interface presenter{
        void insertData(String name, String expedisi, String feedback, FeedbackDatabase database);
        void readData(FeedbackDatabase database);
        void editData(String name, String expedisi, String feedback, int id, FeedbackDatabase database);
        void deleteData(DataFeedback dataFeedback, FeedbackDatabase appDatabase);
    }
}
