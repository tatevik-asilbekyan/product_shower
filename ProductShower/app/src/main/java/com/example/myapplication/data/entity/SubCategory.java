package com.example.myapplication.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.List;

public class SubCategory implements Parcelable {

    public String name;
    public String icon;

    @Nullable
    public List<SubCategory> subCategories;

    protected SubCategory(Parcel in) {
        name = in.readString();
        icon = in.readString();
        subCategories = in.createTypedArrayList(SubCategory.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeTypedList(subCategories);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
        @Override
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        @Override
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
        }
    };
}
