package com.example.myapplication.features.part2.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

abstract class BaseCategorizedFragment extends Fragment {

    protected OnCategoriesInteractionListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCategoriesInteractionListener) {
            listener = (OnCategoriesInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCategoriesInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
