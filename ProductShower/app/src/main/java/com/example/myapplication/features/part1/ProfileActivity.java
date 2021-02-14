package com.example.myapplication.features.part1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.myapplication.R;
import com.example.myapplication.helpers.UIHelper;

public class ProfileActivity extends AppCompatActivity {

    private static final String COVER_URL = "http://www.baltana.com/files/wallpapers-15/John-Wick-Chapter-3-Parabellum-HD-Background-Wallpaper-39454.jpg";
    private static final String AVATAR_URL = "https://images5.alphacoders.com/792/792435.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setup();
    }

    private void setup() {
        final AppCompatImageView cover = findViewById(R.id.cover);
        final AppCompatImageView avatar = findViewById(R.id.avatar);
        final AppCompatTextView name = findViewById(R.id.name);

        name.setText(R.string.simple_name);
        UIHelper.asyncLoadImage(cover, COVER_URL);
        UIHelper.asyncLoadImageCircled(avatar, AVATAR_URL);
    }
}
