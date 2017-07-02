package com.developers.danceguru.Utils;

import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Amanjeet Singh on 02-Jul-17.
 */

public class TeacherModel implements Parcelable {

    private ArrayList<Double> xVal;
    private ArrayList<Double> yVal;
    private ArrayList<Double> zVal;
    List<ArrayList<Double>> data;

    public TeacherModel(ArrayList<Double> xVal,ArrayList<Double> yVal,ArrayList<Double> zVal){
        this.xVal=xVal;
        this.yVal=yVal;
        this.zVal=zVal;
    }

    protected TeacherModel(Parcel in) {
        data=new ArrayList<>(3);
        in.readArrayList(ArrayList.class.getClassLoader());
        this.xVal=data.get(0);
        this.yVal=data.get(1);
        this.zVal=data.get(2);
    }

    public static final Creator<TeacherModel> CREATOR = new Creator<TeacherModel>() {
        @Override
        public TeacherModel createFromParcel(Parcel in) {
            return new TeacherModel(in);
        }

        @Override
        public TeacherModel[] newArray(int size) {
            return new TeacherModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(new ArrayList<ArrayList<Double>>(Arrays.asList(xVal,yVal,zVal)));
    }

    public ArrayList<Double> getxVal() {
        return xVal;
    }

    public ArrayList<Double> getyVal() {
        return yVal;
    }

    public ArrayList<Double> getzVal() {
        return zVal;
    }
}
