package com.example.satsettodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.satsettodo.model.User;
import com.example.satsettodo.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    UserViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new UserViewModel(getApplication());
        EditText txtUserName = findViewById(R.id.login_activity_username);
        EditText txtPassword = findViewById(R.id.login_activity_password);
        Button btnLogin = findViewById(R.id.login_activity_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =txtUserName.getText().toString().trim();
                String password =txtPassword.getText().toString().trim();

                viewModel.getUserByName(username).observe(LoginActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user != null) {
                            if(user.getPassword().equals(password)){
                                SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor spEditor = sharedPreferences.edit();
                                spEditor.putString("loginToken", "Loggedin");
                                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                spEditor.commit();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


        Button btnRegister = findViewById(R.id.login_activity_register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}