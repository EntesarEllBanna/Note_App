package com.example.todolistapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.Models.Tasks;
import com.example.todolistapp.R;

import java.util.ArrayList;
import java.util.List;

public class MeetAdapter extends RecyclerView.Adapter<MeetAdapter.MeetViewHolder> {

    List<Tasks> results = new ArrayList<>();
    OnTaskClickListener listener ;
    Context context ;
    RecyclerView mRecyclrerView ;

    public MeetAdapter(ArrayList<Tasks> results, OnTaskClickListener listener, RecyclerView mRecyclrerView) {
        this.results = results;
        this.listener = listener;
        this.mRecyclrerView = mRecyclrerView;
    }

    @NonNull
    @Override
    public MeetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meetrow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetViewHolder holder, int position) {
        holder.taskName.setText(results.get(position).getTastName());

    }
    public void setTasks(List<Tasks> tasks){
        results =tasks;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MeetViewHolder extends RecyclerView.ViewHolder {
        TextView taskName ;

        public MeetViewHolder(@NonNull View itemView)
        {
            super(itemView);
            taskName = itemView.findViewById(R.id.meet_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = mRecyclrerView.getChildAdapterPosition(view);
                    listener.onClickLisener(results.get(pos));

                }
            });

        }


    }
}
