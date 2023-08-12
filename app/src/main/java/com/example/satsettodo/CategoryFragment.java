package com.example.satsettodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.satsettodo.model.Category;
import com.example.satsettodo.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryFragment extends Fragment {
    EditText txtCategory;
    CategoryViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity().getApplicationContext(), "Category Fragment Class", Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        viewModel = new CategoryViewModel(getActivity().getApplication());

        Button btnSave = view.findViewById(R.id.fragment_Category_btn_save);
        txtCategory = view.findViewById(R.id.fragment_category_txt_category);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category();
                String categoryname =txtCategory.getText().toString();
                if(!categoryname.isEmpty()){
                    category.setCategory(categoryname);
                    viewModel.saveCategory(category);
                    Toast.makeText(getActivity(), "Category Saved", Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).replaceFragmentCategoryList();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "category value cannot be inserted null", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}