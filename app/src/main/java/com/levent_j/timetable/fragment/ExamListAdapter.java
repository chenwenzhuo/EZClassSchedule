package com.levent_j.timetable.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.levent_j.timetable.R;

import java.util.List;

/**
 * Created by --C-W-Z-- on 2016/11/13 0013.
 */

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ExamListViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<DataOfExam> examDataList;

    public ExamListAdapter(Context context, List<DataOfExam> examDataList) {
        this.context = context;
        this.examDataList = examDataList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ExamListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_exam_item, parent, false);
        return new ExamListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExamListViewHolder holder, int position) {
        DataOfExam examData = examDataList.get(position);
        holder.courseView.setText(examData.courseName);
        holder.timeView.setText(examData.examDateAndTime);
        holder.placeView.setText(examData.examPlace);
        holder.seatView.setText(examData.seatNumber);
    }

    @Override
    public int getItemCount() {
        return examDataList.size();
    }

    class ExamListViewHolder extends RecyclerView.ViewHolder {
        TextView courseView,timeView,placeView, seatView;
        //@Bind(R.id.courseName) TextView courseView;
        //@Bind(R.id.examDateAndTime) TextView timeView;
        //@Bind(R.id.examPlace) TextView placeView;
        //@Bind(R.id.seatNumber) TextView seatView;
        public ExamListViewHolder(View itemView) {
            super(itemView);
            courseView = (TextView) itemView.findViewById(R.id.courseName);
            timeView = (TextView) itemView.findViewById(R.id.examDateAndTime);
            placeView = (TextView) itemView.findViewById(R.id.examPlace);
            seatView = (TextView) itemView.findViewById(R.id.seatNumber);
        }
    }

}
