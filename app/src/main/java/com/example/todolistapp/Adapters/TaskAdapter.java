package com.example.todolistapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.Models.Tasks;
import com.example.todolistapp.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Tasks> mtasks = new ArrayList<>();
    OnTaskClickListener listener ;
    Context context ;
    RecyclerView mRecyclrerView ;

    public TaskAdapter(ArrayList<Tasks> tasks, OnTaskClickListener listener, RecyclerView mRecyclerView) {
        this.mtasks = tasks;
        this.listener = listener;
        this.mRecyclrerView = mRecyclerView;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.taskrow, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
//        holder.taskname.setText(tasks.get(position).getTastName());
////        holder.select.setImageResource(tasks.get(position).getSelect());
        Tasks currentlist =  mtasks.get(position);
        holder.taskname.setText(currentlist.getTastName());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "loooong", Toast.LENGTH_SHORT).show();
                holder.select.setVisibility(View.VISIBLE);
                holder.view.setVisibility(View.VISIBLE);
               notifyDataSetChanged();
                return false;
            }
        });



    }
    public void setTasks(List<Tasks> tasks){
        mtasks =tasks;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mtasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskname ;
        ImageView select ;
        View view ;
        ConstraintLayout layout ;

        public TaskViewHolder(@NonNull View itemView) {

            super(itemView);
            taskname=itemView.findViewById(R.id.tast_name);
            select = itemView.findViewById(R.id.select);
            layout = itemView.findViewById(R.id.layout);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = mRecyclrerView.getChildAdapterPosition(view);
                    listener.onClickLisener(mtasks.get(pos));

                }
            });

        }
    }
}
