package com.developers.danceguru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    //2027

    private FancyButton teacher, student;
    private Intent teacherActivity;
    private Intent studentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teacher = (FancyButton) findViewById(R.id.teacher_button);
        student = (FancyButton) findViewById(R.id.student_button);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherActivity = new Intent(MainActivity.this, TeacherActivity.class);
                startActivity(teacherActivity);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentActivity = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(studentActivity);
            }
        });
    }
}
