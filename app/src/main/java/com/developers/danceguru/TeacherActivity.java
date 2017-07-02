package com.developers.danceguru;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.developers.danceguru.Utils.Data;
import com.developers.danceguru.Utils.TeacherModel;
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent;
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter;
import com.github.pwittchen.reactivesensors.library.ReactiveSensors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TeacherActivity extends AppCompatActivity {

    private Button leftHand, rightHand, leftLeg, rightLeg;
    private FancyButton sumbitGyro;
    private ArrayList<Double> xTeacher;
    private ArrayList<Double> yTeacher;
    private ArrayList<Double> zTeacher;
    private int flag = 0;
    private int rightHandTrigger = 0;
    private int leftHandTrigger = 0;
    private int rightLegTrigger = 0;
    private int leftLegTrigger = 0;
    private boolean saveBoolean = true;
    List<TeacherModel> TeacherlistModels;
    List<String> stepsList;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES="MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sumbitGyro = (FancyButton) findViewById(R.id.submit_button);
        xTeacher = new ArrayList<>();
        TeacherlistModels=new ArrayList<>();
        yTeacher = new ArrayList<>();
        zTeacher = new ArrayList<>();
        stepsList=new ArrayList<>();
        leftHand = (Button) findViewById(R.id.left_hand_button);
        rightHand = (Button) findViewById(R.id.right_hand_button);
        leftLeg = (Button) findViewById(R.id.left_leg_button);
        rightLeg = (Button) findViewById(R.id.right_leg_button);
        rightHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setColorRight(Color.RED);
                rightHandTrigger = 1;
            }
        });
        leftHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setColorLeft(Color.RED);
                leftHandTrigger = 1;
            }
        });
        rightLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setColorRightLeg(Color.RED);
                rightLegTrigger = 1;
            }
        });
        leftLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setColorLeftLeg(Color.RED);
                leftLegTrigger = 1;
            }
        });
        sumbitGyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
            }
        });
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new ReactiveSensors(getApplicationContext()).observeSensor(Sensor.TYPE_ACCELEROMETER)
                        .subscribeOn(Schedulers.computation())
                        .filter(ReactiveSensorFilter.filterSensorChanged())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ReactiveSensorEvent>() {
                            @Override
                            public void call(ReactiveSensorEvent reactiveSensorEvent) {
                                SensorEvent event = reactiveSensorEvent.getSensorEvent();

                                float x = event.values[0];
                                float y = event.values[1];
                                float z = event.values[2];


                                String message = String.format("x = %f, y = %f, z = %f", x, y, z);

                                if (leftHandTrigger == 1) {
                                    if (flag != 1) {
                                        xTeacher.add((double) x);
                                        yTeacher.add((double) y);
                                        zTeacher.add((double) z);
                                        saveBoolean=false;
                                        Log.d("TeacherActivity","adding for left");
                                    } else {
                                        if(!saveBoolean){
                                            Log.d("TeacherActivity", xTeacher.size() + " x Size leftHand");
                                            Log.d("TeacherActivity", yTeacher.size() + " y Size leftHand");
                                            Log.d("TeacherActivity", zTeacher.size() + " z Size leftHand");
                                            TeacherModel teacherModel = new TeacherModel(xTeacher, yTeacher, zTeacher);
                                            Data.setTeacherModel(teacherModel);
                                            Data.setStepName(leftHand.getText().toString());
                                            TeacherlistModels.add(teacherModel);
                                            stepsList.add(leftHand.getText().toString());
                                            Data.setTeacherModelList(TeacherlistModels);
                                            Data.setStepList(stepsList);
                                            flag=1;
                                            saveBoolean=true;
                                        }
                                    }
                                }
                                if (rightHandTrigger == 1) {
                                    if (flag != 1) {
                                        xTeacher.add((double) x);
                                        yTeacher.add((double) y);
                                        zTeacher.add((double) z);
                                        saveBoolean=false;
                                    } else {
                                        if(!saveBoolean){
                                            Log.d("TeacherActivity", xTeacher.size() + " x Size RightHand");
                                            Log.d("TeacherActivity", yTeacher.size() + " y Size RightHand");
                                            Log.d("TeacherActivity", zTeacher.size() + " z Size RightHand");
                                            TeacherModel teacherModel = new TeacherModel(xTeacher, yTeacher, zTeacher);
                                            Data.setTeacherModel(teacherModel);
                                            Data.setStepName(rightHand.getText().toString());
                                            TeacherlistModels.add(teacherModel);
                                            stepsList.add(rightHand.getText().toString());
                                            Data.setTeacherModelList(TeacherlistModels);
                                            Data.setStepList(stepsList);
                                            flag=1;
                                            saveBoolean=true;
                                        }
                                    }

                                }
                                if (leftLegTrigger == 1) {
                                    if (flag != 1) {
                                        xTeacher.add((double) x);
                                        yTeacher.add((double) y);
                                        zTeacher.add((double) z);
                                        saveBoolean=false;
                                    } else {
                                        if(!saveBoolean){
                                            Log.d("TeacherActivity", xTeacher.size() + " x Size LeftLeg");
                                            Log.d("TeacherActivity", yTeacher.size() + " y Size LeftLeg");
                                            Log.d("TeacherActivity", zTeacher.size() + " z Size LeftLeg");
                                            TeacherModel teacherModel = new TeacherModel(xTeacher, yTeacher, zTeacher);
                                            TeacherlistModels.add(teacherModel);
                                            Data.setTeacherModel(teacherModel);
                                            Data.setStepName(leftLeg.getText().toString());
                                            stepsList.add(leftLeg.getText().toString());
                                            Data.setTeacherModelList(TeacherlistModels);
                                            Data.setStepList(stepsList);
                                            flag=1;
                                            saveBoolean=true;
                                        }
                                    }
                                }
                                if (rightLegTrigger == 1) {
                                    if (flag != 1) {
                                        xTeacher.add((double) x);
                                        yTeacher.add((double) y);
                                        zTeacher.add((double) z);
                                        saveBoolean=false;
                                    } else {
                                        if(!saveBoolean){
                                            Log.d("TeacherActivity", xTeacher.size() + " x Size leftLeg");
                                            Log.d("TeacherActivity", yTeacher.size() + " y Size leftLeg");
                                            Log.d("TeacherActivity", zTeacher.size() + " z Size leftLeg");
                                            TeacherModel teacherModel = new TeacherModel(xTeacher, yTeacher, zTeacher);
                                            TeacherlistModels.add(teacherModel);
                                            Data.setTeacherModel(teacherModel);
                                            Data.setStepName(rightLeg.getText().toString());
                                            stepsList.add(rightLeg.getText().toString());
                                            Data.setTeacherModelList(TeacherlistModels);
                                            Data.setStepList(stepsList);
                                            flag=1;
                                            saveBoolean=true;
                                        }
                                    }
                                }
                            }
                        });
            }
        };
        runnable.run();
    }

}
