package com.developers.danceguru.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developers.danceguru.CompareActivity;
import com.developers.danceguru.R;
import com.developers.danceguru.Utils.Data;
import com.developers.danceguru.Utils.TeacherModel;

import java.util.List;

/**
 * Created by Amanjeet Singh on 02-Jul-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    List<TeacherModel> teacherModelList;
    List<String> stepList;
    TeacherModel teacherModel;

    public RecyclerViewAdapter (Context context, List<TeacherModel> teacherModelList,List<String> stepList){
        this.context=context;
        this.teacherModelList=teacherModelList;
        this.stepList=stepList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_skill,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        teacherModel=teacherModelList.get(position);
        final String steps=stepList.get(position);
        holder.stepName.setText(steps);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CompareActivity.class);
                Data.setTeacherModelBetween(teacherModel);
                intent.putExtra("stepVal",steps);
                Log.d("Tag",teacherModel.getxVal()+"");
                context.startActivity(intent);
            }
        });
        Log.d("Tag",steps);
    }

    @Override
    public int getItemCount() {
        return teacherModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView stepName;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            stepName= (TextView) itemView.findViewById(R.id.steps_textView);
            cardView= (CardView) itemView.findViewById(R.id.cards);
        }
    }
}
