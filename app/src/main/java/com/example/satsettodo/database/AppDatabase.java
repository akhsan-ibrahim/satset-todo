package com.example.satsettodo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.satsettodo.model.Category;
import com.example.satsettodo.model.Todo;
import com.example.satsettodo.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(exportSchema = false, version = 3, entities = {Category.class, Todo.class, User.class})
@TypeConverters(DateConvertor.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String dbName ="TodoApp";

    public static AppDatabase sharedInstance;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);

    public static synchronized AppDatabase getInstance(Context context) {
        if(sharedInstance == null) {
            sharedInstance = Room.databaseBuilder(context, AppDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sharedInstance;
    }

    public abstract CategoryDao categoryDao();

    public abstract TodoDao todoDao();

    public abstract UserDao userDao();
}
