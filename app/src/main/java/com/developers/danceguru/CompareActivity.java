package com.developers.danceguru;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.developers.danceguru.Utils.Data;
import com.developers.danceguru.Utils.TeacherModel;
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent;
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter;
import com.github.pwittchen.reactivesensors.library.ReactiveSensors;

import java.util.ArrayList;
import java.util.Collections;

import mehdi.sakout.fancybuttons.FancyButton;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class CompareActivity extends AppCompatActivity {

    public static final String TAG = CompareActivity.class.getSimpleName();
    //private TeacherModel teacherModelValues;
    private FancyButton play;
    private FancyButton stop;
    private ArrayList<Double> xStudentVal;
    private ArrayList<Double> yStudentVal;
    private ArrayList<Double> zStudentVal;
    private int flag=0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        play = (FancyButton) findViewById(R.id.play_button);
        stop = (FancyButton) findViewById(R.id.stop_button);
        textView= (TextView) findViewById(R.id.score_textView);
        Log.d(TAG, "" + Data.getTeacherModelBetween().getyVal());
        xStudentVal = new ArrayList<>();
        yStudentVal = new ArrayList<>();
        zStudentVal = new ArrayList<>();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=2;
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

                                if (flag == 1) {
                                    xStudentVal.add((double) x);
                                    yStudentVal.add((double) y);
                                    zStudentVal.add((double) z);
                                    Log.d(TAG,xStudentVal+" xStudent");
                                    Log.d(TAG,yStudentVal+" yStudent");
                                    Log.d(TAG,zStudentVal+" zStudent");
                                }
                                else if(flag==2){
                                    flag=0;
                                    double score1 = Xcorr(Data.getTeacherModelBetween().getxVal(), xStudentVal);
                                    double score2 = Xcorr(Data.getTeacherModelBetween().getyVal(), yStudentVal);
                                    double score3 = Xcorr(Data.getTeacherModelBetween().getzVal(), zStudentVal);
                                    double score = (score1 + score2 + score3) / 3;
                                    score *= 100;
                                    Log.i(TAG, "SCORE: " + score);
                                    textView.setText("Score: "+score);
                                }
                            }
                        });
            }
        };
        runnable.run();

    }

    public static double Xcorr(ArrayList<Double> X1 , ArrayList<Double> X2 ) {
        if(X1.size() > X2.size()) {
            while(X2.size() != X1.size())
                X2.add(0.0);
        }
        if(X1.size() < X2.size()) {
            int i=X2.size() - 1;
            while(X1.size() != X2.size()) {
                X2.remove(i);
                i--;
            }
        }
        return Collections.max(CrossCorrelation(ZeroMeanNormalizedTransform(X1),ZeroMeanNormalizedTransform(X2)));
    }

    private static double calculateAverage(ArrayList<Double> x) {
        double sum = 0;
        if(!x.isEmpty()) {
//            sum = x.stream().map((xi) -> xi).reduce(sum, (accumulator, _item) -> accumulator + _item);
            for(double xi: x) {
                sum += xi;
            }
            return sum / x.size();
        }
        return sum;
    }

    private static double AbsMean(ArrayList<Double> x) {
        double sum = 0;
        if(!x.isEmpty()) {
//            sum = x.stream().map((xi) -> xi*xi).reduce(sum, (accumulator, _item) -> accumulator + _item);
            for(double xi: x) {
                sum += xi;
            }
            return (sum / x.size());
        }
        return sum;
    }

    private static ArrayList<Double> ZeroMeanNormalizedTransform(ArrayList<Double> x) {
        double norm = 0;
        double x_mean = calculateAverage(x);
        if(!x.isEmpty()) {
//            norm = x.stream().map((xi) -> (xi-x_mean)*(xi-x_mean)).reduce(norm, (accumulator, _item) -> accumulator + _item);
            for(double xi: x) {
                norm += (xi-x_mean)*(xi-x_mean);
            }
            norm = Math.sqrt(norm / x.size());
        }
        ArrayList<Double> y = new ArrayList<>();
        if(norm != 0) {
            for (double xi : x) {
                y.add((xi-x_mean) / norm);
            }
        }
        return y;
    }

    private static double SumMult(ArrayList<Double> x, ArrayList<Double> y) {
        double sum=0;
        for (int i=0; i<(x.size()); i++) {
            sum += x.get(i)*y.get(i);
        }
        return sum;
    }

    private static ArrayList<Double> CrossCorrelation(ArrayList<Double> x, ArrayList<Double> y) {

        ArrayList<Double> Xc = new ArrayList<> ();
        for (int i=0; i<(x.size()-1); i++) {
            Xc.add(SumMult(new ArrayList<>(x.subList(0, i+1)),new ArrayList<>(y.subList(y.size()-1-i,y.size())))  / x.size());
        }

        Xc.add(SumMult(x,y) / x.size());

        for (int i=0; i<(x.size()-1); i++) {
            Xc.add(SumMult(new ArrayList<>(x.subList(1+i, x.size())),new ArrayList<>(y.subList(0,y.size()-i))) / x.size());
        }

        return Xc;
    }


}
