package com.example.droptobin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivShape, ivTrash;
    MainActivityListener mainActivityListener;
    ConstraintLayout clLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivShape = findViewById(R.id.ivShape);
        ivTrash = findViewById(R.id.ivTrash);
        clLayout = findViewById(R.id.clLayout);

        mainActivityListener = new MainActivityListener(this);

        clLayout.setOnDragListener(mainActivityListener);
        ivTrash.setOnDragListener(mainActivityListener);
        ivShape.setOnTouchListener(mainActivityListener);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return mainActivityListener.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }
}