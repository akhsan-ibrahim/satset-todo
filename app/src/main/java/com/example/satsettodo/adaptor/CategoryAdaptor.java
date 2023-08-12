package com.example.satsettodo.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.satsettodo.R;
import com.example.satsettodo.TodoListActivity;
import com.example.satsettodo.model.Category;
import com.example.satsettodo.TodoListActivity;
import com.example.satsettodo.model.Category;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.CategoryView> {

    private List<Category> categoryList;

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    private final OnTaskClickListner onTaskClickListner;

    public CategoryAdaptor(OnTaskClickListner onTaskClickListner){
        this.onTaskClickListner = onTaskClickListner;
    }



    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryitemlayout, parent, false);
        CategoryView categoryView = new CategoryView(view, onTaskClickListner);
        return categoryView;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {
        Category category = categoryList.get(position);
        holder.tvCategoryName.setText(category.getCategory());
        Context context = holder.itemView.getContext();
        holder.tvCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, TodoListActivity.class);
                intent.putExtra("categoryId",category.getCategoryId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }


    public Category getCategoryNote(int position){
        return categoryList.get(position);
    }

    public int getUpdateCategoryNote(int position){

        return categoryList.get(position).getCategoryId();
    }
    public class CategoryView extends  RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView tvCategoryName;
        OnTaskClickListner onTaskClickListner;
        public CategoryView(@NonNull View itemView, OnTaskClickListner onTaskClickListner) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.category_item_layout_txt_category);
            this.onTaskClickListner = onTaskClickListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskClickListner.onItemClick(getAdapterPosition());
        }
    }

    public interface  OnTaskClickListner{
        void onItemClick(int position);
    }

    public Category getCategoryPosition(int position){
        return categoryList.get(position);
    }
}
