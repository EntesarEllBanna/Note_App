package com.example.todolistapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ListViewHolder> {

    List<Lists> mlists = new ArrayList<>();
    List<Lists> filteredUserData ;
    OnItemCilckListener listener ;
    RecyclerView mRecyclrerView;
    Context context;

    public ListsAdapter(ArrayList<Lists> lists, OnItemCilckListener listener, RecyclerView mRecyclrerView) {
        this.mlists = lists;
        this.listener = listener;
        this.mRecyclrerView = mRecyclrerView;
        this.filteredUserData = new ArrayList<>(lists) ;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false));
    }

    public void setLists(List<Lists> lists){
        mlists = lists ;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

//        holder.list_name.setText(lists.get(position).getListname());
//        holder.list_num.setText(lists.get(position).getListnum());

        Lists currentlist =  mlists.get(position);
        holder.list_name.setText(currentlist.getListname());

    }

    @Override
    public int getItemCount() {
        return mlists.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView list_name ;



        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = mRecyclrerView.getChildAdapterPosition(v);
                    listener.onItemClick(mlists.get(pos));


                }
            });
        }}
    public Filter getFilter(){
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if(key.isEmpty()){
                    filteredUserData = mlists ;
                }else {
                    List<Lists> lstFilter = new ArrayList<>();
                    for (Lists row : mlists){
                        if (row.getListname().toLowerCase().contains(key.toLowerCase())){
                            lstFilter.add(row);
                        }
                    }
                    filteredUserData = lstFilter ;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserData ;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredUserData=(List<Lists>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }
}
