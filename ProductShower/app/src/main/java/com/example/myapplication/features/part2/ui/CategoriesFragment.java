package com.example.myapplication.features.part2.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.entity.Category;
import com.example.myapplication.features.part2.adapter.CategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends BaseCategorizedFragment implements View.OnClickListener {

    private static final String ARG_CATEGORIES = "categories";

    private List<Category> categories;

    public CategoriesFragment() {
    }

    static CategoriesFragment newInstance(List<Category> categories) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_CATEGORIES, (ArrayList<? extends Parcelable>) categories);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categories = getArguments().getParcelableArrayList(ARG_CATEGORIES);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.categories);
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoriesAdapter adapter = new CategoriesAdapter(categories, this);
        RecyclerView rv = (RecyclerView) view;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        listener.onEmpty(categories.isEmpty(), R.string.no_categories);
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        listener.onCategoryClicked(categories.get(position));
    }
}
