package com.example.kampusku.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kampusku.R;
import com.example.kampusku.ui.ui.maps.MapsFragment;

public class Maps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MapsFragment.newInstance())
                    .commitNow();
        }
    }
}
