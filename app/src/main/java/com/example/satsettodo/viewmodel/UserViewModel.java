package com.example.satsettodo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.satsettodo.database.AppDatabase;
import com.example.satsettodo.database.Repository;
import com.example.satsettodo.model.User;

import org.jetbrains.annotations.NotNull;

public class UserViewModel extends AndroidViewModel {
    Repository repository;

    private LiveData<User> user;

    public UserViewModel(@NotNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        repository = new Repository(appDatabase);
    }

    public LiveData<User> getUserByName(String userName) {
        return repository.loadUserByUserName(userName);
    }

    public void saveUser(User user) {
        repository.insertUser(user);
    }
}