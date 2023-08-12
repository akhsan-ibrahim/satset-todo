package com.example.satsettodo.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "categoryId", childColumns = "categoryId"))
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int todoId;

    private String title;

    private String description;

    private Date todoDate;

    private boolean isCompleted;

    private int priority;
    // 0 = high, 1=medium, 2= low
    private int categoryId;

    private Date createdOn;

    public Todo() {
    }

    @Ignore
    public Todo(String title, String description, Date todoDate, boolean isCompleted, int priority, int categoryId, Date createdOn) {
        this.title = title;
        this.description = description;
        this.todoDate = todoDate;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.categoryId = categoryId;
        this.createdOn = createdOn;
    }
    public Todo(String title, String description, Date todoDate, boolean isCompleted, int priority, Date createdOn) {
        this.title = title;
        this.description = description;
        this.todoDate = todoDate;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.createdOn = createdOn;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
