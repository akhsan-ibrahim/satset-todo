package com.example.satsettodo.database;

import androidx.lifecycle.LiveData;

import com.example.satsettodo.model.Category;
import com.example.satsettodo.model.Todo;
import com.example.satsettodo.model.User;

import java.util.Date;
import java.util.List;

public class Repository {
    private final CategoryDao categoryRepostiroy;

    private final TodoDao todoRepository;
    private final UserDao userDao;

    public Repository(AppDatabase appDatabase) {
        this.categoryRepostiroy = appDatabase.categoryDao();
        this.todoRepository = appDatabase.todoDao();
        this.userDao=appDatabase.userDao();
    }

    public void insertCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.insertCategory(category);
        });
    }

    public void updateCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.updateCategory(category);
        });
    }


    public void deleteCategory(Category category){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.deleteTodoById(category.getCategoryId());
            categoryRepostiroy.deleteCategory(category);
        });
    }

    public LiveData<List<Category>> loadAllCategory(){
        return categoryRepostiroy.loadAllCategory();
    }

    public LiveData<List<Category>> loadCategoryById(int categoryId) {
        return categoryRepostiroy.loadCategoryById(categoryId);
    }

    public void insertTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.insert(todo);
        });
    }

    public void deleteAllTodo() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.deleteAll();
        });
    }
    public void deleteCompletedTodo() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.deleteAllCompleted();
        });
    }

    public void updateAllTodo(int todoId, String title, String description, Date date, boolean completed,Date createdOn) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.updateTodo(todoId,title,description,date,completed,createdOn);
        });
    }

    public void updateAllCategory(int category_id, String name) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.updateAllcategory(category_id,name);
        });
    }


    public void updateIsCompleteTodo(int todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.completeTask(todo);
        });
    }

    public void deleteTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoRepository.delete(todo);
        });
    }
    public LiveData<List<Todo>> loadAllTodo(int categoryId){
        return todoRepository.loadTodoByCategoryId(categoryId);
    }

    public void deleteAllCategory(){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryRepostiroy.deleteAllTodo();
            categoryRepostiroy.deleteAllCategory();
        });
    }
    public LiveData<List<Todo>> loadAllTodo(){
        return todoRepository.TodoloadAll();
    }


    public LiveData<User> loadUserByUserName(String userName){
        return userDao.loadUserByUserName(userName);
    }

    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }
}
