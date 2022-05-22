package com.example.blogapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blogapp.Model.PostModel;
import com.example.blogapp.R;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder>  {

    Context context;
    List<PostModel> postModelList;

    public PostAdapter(ValueEventListener context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_home, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String tittle = postModelList.get(position).getpTitle();
        String desc = postModelList.get(position).getpDesc();
        String image = postModelList.get(position).getpImage();
        holder.postTitle.setText(tittle);
        holder.postDesc.setText(desc);
        Glide.with(context).load(image).into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

            ImageView postImage;
            TextView postTitle, postDesc;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.postImage);
            postTitle = itemView.findViewById(R.id.postTitle);
            postDesc = itemView.findViewById(R.id.postDesc);

        }
    }
}
