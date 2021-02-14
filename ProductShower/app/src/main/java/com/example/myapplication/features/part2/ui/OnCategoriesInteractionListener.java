package com.example.myapplication.features.part2.ui;

import androidx.annotation.StringRes;

import com.example.myapplication.data.entity.Category;
import com.example.myapplication.data.entity.SubCategory;

public interface OnCategoriesInteractionListener {
    void onCategoryClicked(Category category);
    void onSubCategoryClicked(SubCategory category);
    void onEmpty(boolean isEmpty, @StringRes int resId);
}
