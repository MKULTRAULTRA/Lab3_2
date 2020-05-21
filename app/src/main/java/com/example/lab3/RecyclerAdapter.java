package com.example.lab3;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter. RecyclerviewViewHolder>{
    ArrayList <Element> content;

    public RecyclerAdapter(ArrayList<Element> content){
        this.content=content;
    }
    public class  RecyclerviewViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView second_name;
        TextView first_name;
        TextView patronymic;
        TextView time;
        public RecyclerviewViewHolder(View view){
            super(view);
            id = (TextView) view.findViewById(R.id._id);
            second_name = (TextView) view.findViewById(R.id.second_name);
            first_name = (TextView) view.findViewById(R.id.first_name);
            patronymic = (TextView) view.findViewById(R.id.patronymic);
            time = (TextView) view.findViewById(R.id.time);
        }
    }
    @Override
    public  RecyclerviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent,false);
        return new RecyclerviewViewHolder(view);

    }
    @Override
    public void onBindViewHolder(RecyclerviewViewHolder holder, int position){
        holder.itemView.setBackgroundColor(Color.WHITE);
        holder.id.setText(content.get(position).getId());
        holder.second_name.setText(content.get(position).getSecondName());
        holder.first_name.setText(content.get(position).getFirstName());
        holder.patronymic.setText(content.get(position).getPatronymic());
        holder.time.setText(content.get(position).getTime());

    }
    @Override
    public int getItemCount(){
        return content.size();
    }


}