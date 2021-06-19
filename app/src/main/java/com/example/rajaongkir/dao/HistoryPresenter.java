package com.example.rajaongkir.dao;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rajaongkir.entity.DataFeedback;
import com.example.rajaongkir.entity.FeedbackDatabase;
import com.example.rajaongkir.ui.HistoryActivity;

import java.util.List;

public class HistoryPresenter implements HistoryContact.presenter {
    private HistoryContact.view view;

    public HistoryPresenter(HistoryContact.view view){
        this.view = view;
    }

    public void insertData(String name, String expedisi, String feedback, FeedbackDatabase feedbackDatabase) {
        final DataFeedback dataFeedback =  new DataFeedback();
        dataFeedback.setName(name);
        dataFeedback.setExpedisi(expedisi);
        dataFeedback.setFeedback(feedback);
        new InsertData(feedbackDatabase,dataFeedback).execute();
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private FeedbackDatabase feedbackDatabase;
        private DataFeedback dataFeedback;
        public InsertData(FeedbackDatabase feedbackDatabase, DataFeedback dataFeedback) {
            this.feedbackDatabase = feedbackDatabase;
            this.dataFeedback = dataFeedback;
        }

        protected Long doInBackground(Void... voids){
            return feedbackDatabase.dao().insertData(dataFeedback);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void readData(FeedbackDatabase database) {
        List<DataFeedback> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private FeedbackDatabase feedbackDatabase;
        private DataFeedback dataFeedback;

        public EditData(FeedbackDatabase feedbackDatabase, DataFeedback dataFeedback){
            this.feedbackDatabase = feedbackDatabase;
            this.dataFeedback = dataFeedback;
        }

        protected Integer doInBackground(Void... voids){
            return  feedbackDatabase.dao().updateData(dataFeedback);
        }

        protected void  onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String name, String expedisi, String feedback, int id, FeedbackDatabase feedbackDatabase) {
        final DataFeedback dataFeedback = new DataFeedback();
        dataFeedback.setName(name);
        dataFeedback.setExpedisi(expedisi);
        dataFeedback.setFeedback(feedback);
        /*dataMovie.setId(id);*/
        new EditData(feedbackDatabase,dataFeedback).execute();
    }

    class  DeleteData extends  AsyncTask<Void,Void,Long>{
        private FeedbackDatabase feedbackDatabase;
        private DataFeedback dataFeedback;

        public DeleteData(FeedbackDatabase feedbackDatabase, DataFeedback dataFeedback){
            this.feedbackDatabase = feedbackDatabase;
            this.dataFeedback = dataFeedback;
        }

        protected Long doInBackground(Void... voids){
            feedbackDatabase.dao().deleteData(dataFeedback);
            return null;
        }

        protected void  onPostExecute(Long along) {
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataFeedback dataFeedback, FeedbackDatabase feedbackDatabase) {
        new DeleteData(feedbackDatabase, dataFeedback).execute();
    }
}
