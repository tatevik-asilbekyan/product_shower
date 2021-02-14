package com.example.myapplication.features.part2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.Repository;
import com.example.myapplication.data.entity.Category;

import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CategoriesViewModel extends AndroidViewModel {

    private final Repository repository;
    private final CompositeDisposable disposable;
    private MutableLiveData<List<Category>> content;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application.getApplicationContext());
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public LiveData<List<Category>> getContent() {
        if (content == null) {
            content = new MutableLiveData<>();
            loadContent();
        }

        return content;
    }

    private void loadContent() {
        disposable.add(repository.getContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> Collections.emptyList())
                .subscribe(categories -> {
                    final List<Category> filtered = filter(categories);
                    content.setValue(filtered);
                }));
    }

    private List<Category> filter(List<Category> categories) {
        return Flowable.fromIterable(categories)
                .map(category -> new Category(category.name, category.icon,
                        category.products == null ? Collections.emptyList(): category.products,
                        category.subCategories == null ? Collections.emptyList() : category.subCategories))
                .filter(category ->  !category.products.isEmpty() || !category.subCategories.isEmpty())
                .toList()
                .blockingGet();
    }
}
