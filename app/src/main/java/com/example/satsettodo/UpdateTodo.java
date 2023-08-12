package com.example.satsettodo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.satsettodo.adaptor.TodoAdaptor;
import com.example.satsettodo.viewmodel.TodoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateTodo extends AppCompatActivity {

    TodoViewModel todoViewModel;

    TodoAdaptor todoAdaptor;

    Context context;
    ScrollView scrollView;
    CheckBox fragment_todo_chk_complete;
    TextView fragment_todo_txt_title, fragment_todo_txtDescription;
    EditText fragment_todo_txt_date;
    Button update;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);
        context = getApplicationContext();

        todoAdaptor = new TodoAdaptor();

        scrollView = findViewById(R.id.scrollView2);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        fragment_todo_chk_complete = findViewById(R.id.fragment_todo_chk_complete);

        fragment_todo_txt_title = findViewById(R.id.fragment_todo_txt_title);
        fragment_todo_txtDescription = findViewById(R.id.fragment_todo_txtDescription);

        fragment_todo_txt_date = findViewById(R.id.fragment_todo_txt_date);


        update = findViewById(R.id.todo_btn_Update);
        intent = getIntent();


        fragment_todo_txt_title.setText(intent.getStringExtra("todotitle"));
        fragment_todo_txtDescription.setText(intent.getStringExtra("description"));
        fragment_todo_chk_complete.isChecked();


        fragment_todo_txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });
    }

    private void saveData() {

        int priority = intent.getIntExtra("priority", 1);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date todoDateOn = null;
        try {
            todoDateOn = formatter.parse(fragment_todo_txt_date.getText().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//                todo.setTodoDate(todoDateOn);
        Date UpdateOn = new Date();
//                todo.setCreatedOn(UpdateOn);

        boolean isComplete = fragment_todo_chk_complete.isChecked();

        todoViewModel.ChangeTodo(intent.getIntExtra("id", 1), fragment_todo_txt_title.getText().toString(), fragment_todo_txtDescription.getText().toString(), todoDateOn, isComplete,UpdateOn);

        Toast.makeText(UpdateTodo.this, "Updated", Toast.LENGTH_SHORT).show();

        scrollView.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.updateActivity, CategoryListFragment.class, null)
                .addToBackStack("CategoryList")
                .setReorderingAllowed(true)
                .commit();
    }


    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fragment_todo_txt_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day
        );
        datePickerDialog.show();
    }
}