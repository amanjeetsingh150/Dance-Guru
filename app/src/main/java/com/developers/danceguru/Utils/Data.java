package com.developers.danceguru.Utils;

import java.util.List;

/**
 * Created by Amanjeet Singh on 02-Jul-17.
 */

public class Data {

    public static int color;
    public static int colorLeft;
    public static int colorLeftLeg;
    public static int colorRightLeg;
    public static TeacherModel teacherModel;
    public static String stepName;
    public static List<TeacherModel> teacherModelList;

    public static TeacherModel getTeacherModelBetween() {
        return teacherModelBetween;
    }

    public static void setTeacherModelBetween(TeacherModel teacherModelBetween) {
        Data.teacherModelBetween = teacherModelBetween;
    }

    public static TeacherModel teacherModelBetween;

    public static List<TeacherModel> getTeacherModelList() {
        return teacherModelList;
    }

    public static void setTeacherModelList(List<TeacherModel> teacherModelList) {
        Data.teacherModelList = teacherModelList;
    }

    public static List<String> getStepList() {
        return stepList;
    }

    public static void setStepList(List<String> stepList) {
        Data.stepList = stepList;
    }

    public static List<String> stepList;

    public static void setColorRight(int color) {
        Data.color = color;
    }

    public static int getColorRight() {
        return color;
    }


    public static int getColorLeft() {
        return colorLeft;
    }

    public static void setColorLeft(int colorLeft) {
        Data.colorLeft = colorLeft;
    }

    public static void setColorLeftLeg(int colorLeftLeg) {
        Data.colorLeftLeg = colorLeftLeg;
    }

    public static int getColorLeftLeg() {
        return colorLeftLeg;
    }

    public static int getColorRightLeg() {
        return colorRightLeg;
    }

    public static void setColorRightLeg(int colorRightLeg) {
        Data.colorRightLeg = colorRightLeg;
    }

    public static void setTeacherModel(TeacherModel teacherModel) {
        Data.teacherModel = teacherModel;
    }

    public static TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public static void setStepName(String stepName) {
        Data.stepName = stepName;
    }

    public static String getStepName() {
        return stepName;
    }

}
