package com.example.satsettodo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.satsettodo.adaptor.CategoryAdaptor;
import com.example.satsettodo.model.Category;
import com.example.satsettodo.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CategoryListFragment extends Fragment implements CategoryAdaptor.OnTaskClickListner {

    CategoryViewModel categoryViewModel;
    RecyclerView categoryRecyclerView;
    Category category;
    Context context;
    CategoryAdaptor categoryAdaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        category= new Category();
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        categoryViewModel  = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        categoryAdaptor = new CategoryAdaptor(this::onItemClick);
        categoryViewModel.getCategoryList().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdaptor.setCategoryList(categories);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
                categoryRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
                categoryRecyclerView.setAdapter(categoryAdaptor);

                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        if (direction == ItemTouchHelper.LEFT) {
                            categoryViewModel.deleteCategory(categoryAdaptor.getCategoryNote(viewHolder.getAdapterPosition()));
                            categoryRecyclerView.getRecycledViewPool().clear();
                            categoryAdaptor.notifyDataSetChanged();

                            Toast.makeText(getActivity().getApplicationContext(), "Category Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else if (direction == ItemTouchHelper.RIGHT) {
                            int position=viewHolder.getAdapterPosition();
                            UpdateCategoryFragment updateCategoryFragment=new UpdateCategoryFragment();
                            Bundle bundle= new Bundle();
                            bundle.putInt("id",categoryAdaptor.getCategoryPosition(position).getCategoryId());
                            bundle.putString("name",categoryAdaptor.getCategoryPosition(position).getCategory());
                            updateCategoryFragment.setArguments(bundle);
                            getParentFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_container,updateCategoryFragment).addToBackStack(null).commit();
                        }

                    }
                }).attachToRecyclerView(categoryRecyclerView);
            }
        },1000);
        return view;
    }



    @Override
    public void onItemClick(int position) {

    }

    private void DeleteAllCategory() {
        categoryViewModel.deleteCategory(category);
    }
}