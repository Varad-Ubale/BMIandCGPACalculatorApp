package com.vktech.bmicgpacalculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGoToBMI, btnGoToCGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToBMI = findViewById(R.id.btnGoToBMI);
        btnGoToCGPA = findViewById(R.id.btnGoToCGPA);

        btnGoToBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BmiCalculatorActivity.class);
                startActivity(intent);
            }
        });

        btnGoToCGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CgpaCalculatorActivity.class);
                startActivity(intent);
            }
        });
    }
}