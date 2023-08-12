package com.example.satsettodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.satsettodo.viewmodel.CategoryViewModel;

public class UpdateCategoryFragment extends Fragment {

    EditText txtCategory;
    CategoryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_update_category, container, false);
        viewModel = new CategoryViewModel(getActivity().getApplication());

        Button btnSave = view.findViewById(R.id.fragment_Category_btn_save);
        txtCategory = view.findViewById(R.id.fragment_category_txt_category_update);

        Bundle bundle= getArguments();
        if(bundle !=null){
            String name= bundle.getString("name");
            int Id=bundle.getInt("id");

            txtCategory.setText(name);



            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String categoryname =txtCategory.getText().toString();
                    if(!categoryname.isEmpty()){
                    viewModel.ChangeCategory(Id,categoryname );

                        Toast.makeText(getActivity(), "Category Saved", Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).replaceFragmentCategoryList();
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), "category value cannot be inserted null", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Bundle not found", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}