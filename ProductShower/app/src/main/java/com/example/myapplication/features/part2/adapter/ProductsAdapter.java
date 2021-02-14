package com.example.myapplication.features.part2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.entity.Product;
import com.example.myapplication.helpers.UIHelper;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> dataSource;

    public ProductsAdapter(@NonNull List<Product> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product content = getItem(position);

        holder.title.setText(content.name);
        UIHelper.asyncLoadImage(holder.icon, content.icon);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    private Product getItem(int position) {
        return dataSource.get(position);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView title;
        AppCompatImageView icon;

        ProductViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.category_item_title);
            icon = itemView.findViewById(R.id.category_item_icon);
        }
    }
}