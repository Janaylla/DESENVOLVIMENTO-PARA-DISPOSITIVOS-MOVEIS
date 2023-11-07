package com.example.fragmetos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentA fragmentA = new FragmentA();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, fragmentA)
                    .commit();
        }
    }
}
