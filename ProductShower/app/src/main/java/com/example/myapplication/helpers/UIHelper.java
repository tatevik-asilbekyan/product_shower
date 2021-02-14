package com.example.myapplication.helpers;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.Snackbar.LENGTH_SHORT;

public final class UIHelper {

    private UIHelper() {
    }

    public static void showSnackBar(@NonNull View view, @StringRes int resId) {
        Snackbar.make(view, resId, LENGTH_SHORT).show();
    }

    public static void asyncLoadImage(@NonNull ImageView target, @Nullable String url) {
        Glide.with(target.getContext())
                .load(url)
                .into(target);
    }

    public static void asyncLoadImageCircled(@NonNull ImageView target, @Nullable String url) {
        Glide.with(target.getContext())
                .load(url)
                .circleCrop()
                .into(target);
    }
}
