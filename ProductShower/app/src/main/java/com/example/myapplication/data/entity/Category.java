package com.example.myapplication.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Category implements Parcelable {

    public String name;
    public String icon;
    public List<Product> products;
    public List<SubCategory> subCategories;

    public Category(@NonNull String name, @NonNull String icon, @NonNull List<Product> products, @NonNull List<SubCategory> subCategories) {
        this.name = name;
        this.icon = icon;
        this.products = products;
        this.subCategories = subCategories;
    }

    protected Category(Parcel in) {
        name = in.readString();
        icon = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
        subCategories = in.createTypedArrayList(SubCategory.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeTypedList(products);
        dest.writeTypedList(subCategories);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
