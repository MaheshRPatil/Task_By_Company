package com.tcg.task.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.tcg.task.Model.Model;
import com.tcg.task.R;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {
    private Context context;
    private List<Model> modelList;

    public ModelAdapter(Observer<List<Model>> context, List<Model> modelList) {
        this.context = (Context) context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ModelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.each_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        Model model=modelList.get(position);
        holder.id.setText("Id "+model.getId());
        holder.title.setText("Title "+model.getTitle());
        holder.body.setText("Body "+model.getBody());

    }

    public void getAllModel(List<Model> modelList){
        this.modelList=modelList;
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ModelViewHolder extends RecyclerView.ViewHolder{
        public TextView id,title,body;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            title=itemView.findViewById(R.id.title);
            body= itemView.findViewById(R.id.body);
        }
    }
}
