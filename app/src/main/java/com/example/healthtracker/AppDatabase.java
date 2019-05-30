package com.example.healthtracker;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Diary.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();
}
