package com.vktech.bmicgpacalculatorapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BmiCalculatorActivity extends AppCompatActivity {

    EditText editTextWeight, editTextHeight;
    Button btnCalculateBMI;
    TextView textViewResultBMI, textViewCategoryBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        textViewResultBMI = findViewById(R.id.textViewResultBMI);
        textViewCategoryBMI = findViewById(R.id.textViewCategoryBMI);

        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            if (height == 0) {
                Toast.makeText(this, "Height cannot be zero", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (height * height);
            textViewResultBMI.setText(String.format("Your BMI: %.2f", bmi));
            displayBmiCategory(bmi);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayBmiCategory(float bmi) {
        String category;
        int color;

        if (bmi < 18.5) {
            category = "Underweight";
            color = Color.parseColor("#3B82F6"); // Blue
        } else if (bmi < 25) {
            category = "Normal weight";
            color = Color.parseColor("#10B981"); // Green
        } else if (bmi < 30) {
            category = "Overweight";
            color = Color.parseColor("#F59E0B"); // Amber
        } else {
            category = "Obese";
            color = Color.parseColor("#EF4444"); // Red
        }

        textViewCategoryBMI.setText(category);
        textViewCategoryBMI.setTextColor(color);
    }
}