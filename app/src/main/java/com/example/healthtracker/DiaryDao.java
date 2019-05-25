package com.example.healthtracker;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DiaryDao {

    @Query("SELECT * FROM Diary")
    List<Diary> getAll();

    @Query("SELECT * FROM Diary WHERE title = :title")
    Diary findByTitle(String title);

    @Insert
    void add(Diary diary);

    @Update
    void update(Diary diary);

    @Delete
    void delete(Diary diary);
}
