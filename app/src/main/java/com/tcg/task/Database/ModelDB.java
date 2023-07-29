package com.tcg.task.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tcg.task.Dao.ModelDao;
import com.tcg.task.Model.Model;

@Database(entities = {Model.class},version = 1)
public abstract class ModelDB extends RoomDatabase {
    private static final String DATABASE_NAME = "ModelDB";

    public abstract ModelDao modelDao();

    private static volatile ModelDB INSTANCE;

    public static ModelDB getInstance(Context context){
        if(INSTANCE == null){
            synchronized (ModelDB.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context,ModelDB.class,DATABASE_NAME).addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }

        static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>{

        private ModelDao modelDao;
        PopulateAsynTask(ModelDB modelDB){
            modelDao = modelDB.modelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            modelDao.deleteAll();
            return null;
        }
    }
}
