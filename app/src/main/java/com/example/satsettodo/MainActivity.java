package com.example.satsettodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.satsettodo.CategoryFragment;
import com.example.satsettodo.LoginActivity;
import com.example.satsettodo.adaptor.CategoryAdaptor;
import com.example.satsettodo.viewmodel.CategoryViewModel;
import com.example.satsettodo.CategoryListFragment;
import com.example.satsettodo.R;
import com.example.satsettodo.adaptor.CategoryAdaptor;
import com.example.satsettodo.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    CategoryViewModel categoryViewModel;
    RecyclerView categoryRecyclerView;
    CategoryAdaptor categoryAdaptor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryViewModel  = new ViewModelProvider(this).get(CategoryViewModel.class);

        FloatingActionButton fabCat = findViewById(R.id.fab_category);
        fabCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new CategoryFragment();
                FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
                transition.replace(R.id.activity_main_fragment_container, frag).commit();
            }
        });

    }
    public void replaceFragmentCategoryList(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment_container, CategoryListFragment.class, null)
                .addToBackStack("CategoryList")
                .setReorderingAllowed(true)
                .commit();
    }

    public void replaceFragmentCategory(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment_container, CategoryFragment.class, null)
                .addToBackStack("Category")
                .setReorderingAllowed(true)
                .commit();
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_newcategory:
                replaceFragmentCategory();
                return true;
            case R.id.main_menu_clear_category:
                categoryViewModel.deleteAllCategory();
                replaceFragmentCategory();

                return true;
            case R.id.main_menu_logout:
                SharedPreferences sharedPreferences =  getSharedPreferences("login", MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return  true;
        }
        return true;
    }


}