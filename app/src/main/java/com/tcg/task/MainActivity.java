package com.tcg.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.tcg.task.Adapter.ModelAdapter;
import com.tcg.task.Model.Model;
import com.tcg.task.Network.Api;
import com.tcg.task.Repository.ModelRepository;
import com.tcg.task.ViewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements Observer<List<Model>> {

    private ViewModel viewModel;
    private static final String URL_Data="https://jsonplaceholder.typicode.com/posts/";
    private RecyclerView recyclerView;
    private ModelAdapter modelAdapter;
    private List<Model> modelList;

    private ModelRepository modelRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        modelList=new ArrayList<>();
        modelRepository = new ModelRepository(getApplication());
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        modelAdapter = new ModelAdapter(this,modelList);


        viewModel.getGetAllModel().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> modelList) {

            }
        });
        networkRequest();
    }
    private void networkRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_Data)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Model>> call = api.getAllModel();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.isSuccessful()){
                    modelRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onChanged(List<Model> modelList) {
        modelAdapter.getAllModel(modelList);
        recyclerView.setAdapter(modelAdapter);
        Log.d("main","onChanged"+modelList);
    }
}