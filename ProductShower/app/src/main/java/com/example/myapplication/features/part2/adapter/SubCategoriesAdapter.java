package com.example.myapplication.features.part2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.entity.SubCategory;
import com.example.myapplication.helpers.UIHelper;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoryViewHolder> {

    private List<SubCategory> dataSource;
    private View.OnClickListener listener;

    public SubCategoriesAdapter(@NonNull List<SubCategory> dataSource, @Nullable View.OnClickListener listener) {
        this.dataSource = dataSource;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        final SubCategory content = getItem(position);

        holder.title.setText(content.name);
        UIHelper.asyncLoadImage(holder.icon, content.icon);

        if (listener != null) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    private SubCategory getItem(int position) {
        return dataSource.get(position);
    }

    static class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView title;
        AppCompatImageView icon;

        SubCategoryViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.category_item_title);
            icon = itemView.findViewById(R.id.category_item_icon);
        }
    }
}