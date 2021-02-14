package com.example.myapplication.features.part2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.entity.SubCategory;
import com.example.myapplication.features.part2.adapter.SubCategoriesAdapter;

public class SubSubCategoriesFragment extends BaseCategorizedFragment {

    private static final String ARG_CATEGORY = "category";

    private SubCategory category;

    public SubSubCategoriesFragment() {
    }

    static SubSubCategoriesFragment newInstance(SubCategory category) {
        SubSubCategoriesFragment fragment = new SubSubCategoriesFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getParcelable(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(category.name);
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (category.subCategories.isEmpty()) {
            listener.onEmpty(true, R.string.no_subcategories);
        } else {
            final SubCategoriesAdapter categoriesAdapter = new SubCategoriesAdapter(category.subCategories, null);
            final RecyclerView rv = (RecyclerView) view;
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(categoriesAdapter);
        }
    }
}
