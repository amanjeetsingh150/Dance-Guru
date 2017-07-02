package com.developers.danceguru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.developers.danceguru.Utils.Data;
import com.developers.danceguru.Utils.TeacherModel;
import com.developers.danceguru.adapters.RecyclerViewAdapter;

public class StudentActivity extends AppCompatActivity {

    public static final String TAG=StudentActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        if(Data.getTeacherModel()!=null){
            Log.d("StudentActivity",Data.getTeacherModel().getxVal()+"");
        }
        if(Data.getTeacherModelList().size()>0){
            Log.d(TAG," Techer list: "+Data.getTeacherModelList().size());
            Log.d(TAG," Step name: "+Data.getStepList().get(0));
        }
        recyclerViewAdapter=new RecyclerViewAdapter(StudentActivity.this,Data.getTeacherModelList(),Data.getStepList());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(StudentActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
