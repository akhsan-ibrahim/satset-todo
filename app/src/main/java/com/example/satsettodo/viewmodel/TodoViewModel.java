package com.example.satsettodo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.satsettodo.database.AppDatabase;
import com.example.satsettodo.database.Repository;
import com.example.satsettodo.model.Todo;

import java.util.Date;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private LiveData<List<Todo>> todoList,todolistAll;
    Repository repository;
    public TodoViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        repository = new Repository(appDatabase);
    }

    public void saveTodo(Todo todo) {
        repository.insertTodo(todo);
    }

    public void  removeCompletedTodo(){
        repository.deleteCompletedTodo();
    }

    public void  removeAllTodo(){
        repository.deleteAllTodo();
    }


    public LiveData<List<Todo>> getTodoList(int categoryId) {
        todoList = repository.loadAllTodo(categoryId);
        return todoList;
    }

    public void ChangeTodo(int todoId, String title, String description, Date date, boolean completed,Date createdOn) {
        repository.updateAllTodo(todoId,title,description,date,completed,createdOn);
    }
    public void updateOnCompleteTodo(int todo) {
        repository.updateIsCompleteTodo(todo);
    }

    public void  removeTodo(Todo todo){
        repository.deleteTodo(todo);
    }
    public LiveData<List<Todo>> getAllTodoList() {
        todolistAll = repository.loadAllTodo();
        return todolistAll;
    }
}
