package com.vktech.bmicgpacalculatorapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CgpaCalculatorActivity extends AppCompatActivity {

    LinearLayout coursesContainer;
    Button btnAddCourse, btnCalculateCGPA;
    TextView textViewResultCGPA;

    private final List<View> courseViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_calculator);

        coursesContainer = findViewById(R.id.coursesContainer);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnCalculateCGPA = findViewById(R.id.btnCalculateCGPA);
        textViewResultCGPA = findViewById(R.id.textViewResultCGPA);

        addCourseView(); // Add the first course field by default

        btnAddCourse.setOnClickListener(v -> addCourseView());
        btnCalculateCGPA.setOnClickListener(v -> calculateCGPA());
    }

    private void addCourseView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View courseView = inflater.inflate(R.layout.course_input_item, coursesContainer, false);

        Button btnRemoveCourse = courseView.findViewById(R.id.btnRemoveCourse);
        btnRemoveCourse.setOnClickListener(v -> removeCourseView(courseView));

        coursesContainer.addView(courseView);
        courseViews.add(courseView);
    }

    private void removeCourseView(View courseView) {
        if (courseViews.size() > 1) {
            coursesContainer.removeView(courseView);
            courseViews.remove(courseView);
        } else {
            Toast.makeText(this, "You must have at least one course.", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateCGPA() {
        double totalGradePoints = 0;
        double totalCredits = 0;

        for (View courseView : courseViews) {
            EditText editTextGrade = courseView.findViewById(R.id.editTextGrade);
            EditText editTextCredits = courseView.findViewById(R.id.editTextCredits);

            String gradeStr = editTextGrade.getText().toString();
            String creditsStr = editTextCredits.getText().toString();

            if (gradeStr.isEmpty() || creditsStr.isEmpty()) {
                Toast.makeText(this, "Please fill all grade and credit fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double grade = Double.parseDouble(gradeStr);
                double credits = Double.parseDouble(creditsStr);

                if (grade < 0 || grade > 10 || credits <= 0) {
                    Toast.makeText(this, "Please enter valid grades (0-10) and credits (>0).", Toast.LENGTH_SHORT).show();
                    return;
                }

                totalGradePoints += grade * credits;
                totalCredits += credits;
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (totalCredits > 0) {
            double cgpa = totalGradePoints / totalCredits;
            textViewResultCGPA.setText(String.format("Your CGPA: %.2f", cgpa));
        } else {
            textViewResultCGPA.setText("");
            Toast.makeText(this, "Total credits cannot be zero.", Toast.LENGTH_SHORT).show();
        }
    }
}
