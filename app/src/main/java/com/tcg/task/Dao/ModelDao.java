package com.tcg.task.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tcg.task.Model.Model;

import java.util.List;

@Dao
public interface ModelDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Model> modelList);

    @Query("SELECT * FROM room")
    LiveData<List<Model>> getAllModel();

    @Query("DELETE FROM room")
    void deleteAll();

}
