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
import com.example.myapplication.data.entity.Category;
import com.example.myapplication.features.part2.adapter.ProductsAdapter;
import com.example.myapplication.features.part2.adapter.SubCategoriesAdapter;
import com.google.android.material.tabs.TabLayout;

public class SubCategoriesFragment extends BaseCategorizedFragment implements View.OnClickListener {

    private static final String ARG_CATEGORY = "category";

    private Category category;

    public SubCategoriesFragment() {
    }

    static SubCategoriesFragment newInstance(Category category) {
        SubCategoriesFragment fragment = new SubCategoriesFragment();
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
        return inflater.inflate(R.layout.fragment_subcategories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SubCategoriesAdapter categoriesAdapter = new SubCategoriesAdapter(category.subCategories, this);
        final ProductsAdapter productsAdapter = new ProductsAdapter(category.products);

        final RecyclerView rv = view.findViewById(R.id.subcategoriesRV);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(categoriesAdapter);
        listener.onEmpty(category.subCategories.isEmpty(), R.string.no_subcategories);

        final TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tabs.getSelectedTabPosition()) {
                    case 0:
                        rv.setAdapter(categoriesAdapter);
                        listener.onEmpty(category.subCategories.isEmpty(), R.string.no_subcategories);
                        break;
                    case 1:
                        rv.setAdapter(productsAdapter);
                        listener.onEmpty(category.products.isEmpty(), R.string.no_products);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    @Override
    public void onClick(View view) {
        final int position = (int) view.getTag();
        listener.onSubCategoryClicked(category.subCategories.get(position));
    }
}
