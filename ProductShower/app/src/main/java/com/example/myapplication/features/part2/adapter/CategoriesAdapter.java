package com.example.myapplication.features.part2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.entity.Category;
import com.example.myapplication.helpers.UIHelper;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<Category> dataSource;
    private View.OnClickListener listener;

    public CategoriesAdapter(@NonNull List<Category> dataSource, @NonNull View.OnClickListener listener) {
        this.dataSource = dataSource;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Category content = getItem(position);

        holder.title.setText(content.name);
        UIHelper.asyncLoadImage(holder.icon, content.icon);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    private Category getItem(int position) {
        return dataSource.get(position);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView title;
        AppCompatImageView icon;

        CategoryViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.category_item_title);
            icon = itemView.findViewById(R.id.category_item_icon);
        }
    }
}