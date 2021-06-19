package com.example.rajaongkir.entity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = DataFeedback.class, version=1)
public abstract class FeedbackDatabase extends RoomDatabase {
    public abstract DataFeedbackDAO dao();
    private static FeedbackDatabase feedbackDatabase;

    public static FeedbackDatabase inidb(Context context){
        if (feedbackDatabase==null)
            feedbackDatabase = Room.databaseBuilder(context, FeedbackDatabase.class, "dbFeedback").allowMainThreadQueries().build();
        return feedbackDatabase;
    }
}
