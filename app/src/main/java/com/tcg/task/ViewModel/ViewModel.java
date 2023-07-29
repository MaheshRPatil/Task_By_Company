package com.tcg.task.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tcg.task.Model.Model;
import com.tcg.task.Repository.ModelRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private ModelRepository modelRepository;
    private LiveData<List<Model>>getAllModel;

    public ViewModel(@NonNull Application application) {
        super(application);
        modelRepository = new ModelRepository((application));
        getAllModel = modelRepository.getGetAllModel();
    }

    public void insert(List<Model> list){
        modelRepository.insert(list);
    }

    public LiveData<List<Model>>getGetAllModel(){
        return getAllModel;
    }
}
