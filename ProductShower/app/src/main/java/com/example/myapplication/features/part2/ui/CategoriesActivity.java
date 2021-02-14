package com.example.myapplication.features.part2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.data.entity.Category;
import com.example.myapplication.data.entity.SubCategory;
import com.example.myapplication.features.part1.ProfileActivity;
import com.example.myapplication.features.part2.viewmodel.CategoriesViewModel;
import com.example.myapplication.helpers.UIHelper;

import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements OnCategoriesInteractionListener {

    private LinearLayout emptyContainer;
    private AppCompatTextView emptyTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        setSupportActionBar(findViewById(R.id.toolbar));
        emptyContainer = findViewById(R.id.empty);
        emptyTitle = findViewById(R.id.empty_text);

        setupViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.user_profile)
            startActivity(new Intent(CategoriesActivity.this, ProfileActivity.class));
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        super.onBackPressed();
    }

    @Override
    public void onCategoryClicked(Category category) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, SubCategoriesFragment.newInstance(category))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSubCategoryClicked(SubCategory category) {
        if (category.subCategories == null || category.subCategories.isEmpty()) {
            UIHelper.showSnackBar(findViewById(R.id.content_frame), R.string.no_elements);
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, SubSubCategoriesFragment.newInstance(category))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onEmpty(boolean isEmpty, int resId) {
        int visibility = View.GONE;
        if (isEmpty) {
            visibility = View.VISIBLE;
            emptyTitle.setText(resId);
        }
        emptyContainer.setVisibility(visibility);
    }

    private void setupViewModel() {
        CategoriesViewModel model = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        model.getContent().observe(this, this::showCategories);
    }

    private void showCategories(List<Category> categories) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, CategoriesFragment.newInstance(categories))
                .commit();
    }
}
