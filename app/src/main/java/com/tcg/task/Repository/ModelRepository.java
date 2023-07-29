package com.tcg.task.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tcg.task.Dao.ModelDao;
import com.tcg.task.Database.ModelDB;
import com.tcg.task.Model.Model;

import java.util.List;

public class ModelRepository {
    private ModelDB database;
    private LiveData<List<Model>>getAllModel;

    public ModelRepository(Application application){
        database = ModelDB.getInstance(application);
        getAllModel=database.modelDao().getAllModel();
    }
    public void insert(List<Model>modelList){
        new InsertAsynTask(database).execute(modelList);
    }

    public LiveData<List<Model>>getGetAllModel(){
        return getAllModel;
    }

    class InsertAsynTask extends AsyncTask<List<Model>,Void,Void>{

        private ModelDao modelDao;
        InsertAsynTask(ModelDB modelDB)
        {
            modelDao = modelDB.modelDao();
        }
        @Override
        protected Void doInBackground(List<Model>... lists) {
            modelDao.insert(lists[0]);
            return null;
        }
    }
}
