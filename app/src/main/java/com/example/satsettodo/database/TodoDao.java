package com.example.satsettodo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.satsettodo.model.Todo;

import java.util.Date;
import java.util.List;

@Dao
public interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("delete from todo")
    void deleteAll();

    @Query("delete from todo where isCompleted=1")
    void deleteAllCompleted();

    @Query("delete from todo where categoryId = :categoryId")
    void deleteAllTodoWithCategory(int categoryId);

    @Query("update todo set title=:title,description=:description, todoDate=:date,isCompleted=:completed,createdOn=:createddate where todoId = :todoId")
    void updateTodo(int todoId, String title, String description, Date date, boolean completed,Date createddate);

    @Query("update todo set isCompleted=1 where todoId = :todoId")
    void completeTask(int todoId);

    @Query("select * from todo where todoId = :todoId")
    LiveData<Todo> loadTodoById(int todoId);

    @Query("select * from todo where categoryId = :categoryId")
    LiveData<List<Todo>> loadTodoByCategoryId(int categoryId );
    @Query("select * from todo")
    LiveData<List<Todo>> TodoloadAll();
}
