package com.tcg.task.Network;

import com.tcg.task.Model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("posts")
    Call<List<Model>> getAllModel();
}
