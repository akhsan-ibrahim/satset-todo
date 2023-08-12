package com.example.satsettodo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.satsettodo.database.AppDatabase;
import com.example.satsettodo.database.Repository;
import com.example.satsettodo.model.Category;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    Repository repository;
    private final LiveData<List<Category>> categoryList;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);

        repository = new Repository(appDatabase);
        categoryList = repository.loadAllCategory();
    }

    public void  saveCategory(Category category){
        repository.insertCategory(category);
    }

    public void  updateCategory(Category category){
        repository.updateCategory(category);
    }

    public void  deleteCategory(Category category){
        repository.deleteCategory(category);
    }


    public void ChangeCategory(int todoId, String title) {
        repository.updateAllCategory(todoId,title);
    }
    public void  deleteAllCategory(){
        repository.deleteAllCategory();
    }
    public LiveData<List<Category>> getCategoryList() {
        return categoryList;
    }
}
