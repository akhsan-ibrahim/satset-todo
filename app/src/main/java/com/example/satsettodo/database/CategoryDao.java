package com.example.satsettodo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.satsettodo.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Query("update category set category=:category where CategoryId = :CategoryId")
    void updateAllcategory(int CategoryId, String category);


    @Query("delete from todo where categoryId =:categoryId")
    void  deleteTodoById(int categoryId);
    @Delete
    void deleteCategory(Category category);

    @Query("select * from category order by category")
    LiveData<List<Category>> loadAllCategory();

    @Query("select * from category where categoryId =:categoryId")
    LiveData<List<Category>> loadCategoryById(int categoryId);


    @Query("delete from category")
    void  deleteAllCategory();

    @Query("delete from todo")
    void  deleteAllTodo();


}
