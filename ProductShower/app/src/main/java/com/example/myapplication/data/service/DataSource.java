package com.example.myapplication.data.service;

import com.example.myapplication.data.entity.Category;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {
    Single<List<Category>> getContent();
}
