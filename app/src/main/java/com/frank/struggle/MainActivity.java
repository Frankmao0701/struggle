package com.frank.struggle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClassLoader classLoader = this.getClass().getClassLoader();
        while (classLoader != null) {
            Log.d(TAG, "ClassLoader == " + classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
