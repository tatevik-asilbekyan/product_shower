package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.data.entity.Category;
import com.example.myapplication.data.entity.ContentResponse;
import com.example.myapplication.data.service.DataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Single;

public final class Repository implements DataSource {

    private final Context context;
    private final Gson gson;

    public Repository(Context context) {
        this.context = context;
        gson = new GsonBuilder().create();
    }

    @Override
    public Single<List<Category>> getContent() {
        return Single.fromCallable(() -> {
            final InputStream inputStream = context.getAssets().open("content.json");
            final InputStreamReader reader = new InputStreamReader(inputStream);
            final ContentResponse response = gson.fromJson(reader, ContentResponse.class);
            return response.content;
        });
    }
}
