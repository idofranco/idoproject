package com.example.myapplication14;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.PersonViewHolder>{
    private Context context;
    private ArrayList<Person> valuesList;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Person> getValuesList() {
        return valuesList;
    }

    public void setValuesList(ArrayList<Person> valuesList) {
        this.valuesList = valuesList;
    }

    public MyViewAdapter(Context _context, ArrayList<Person> _valuesList) {
        this.context = _context;
        this.valuesList = _valuesList;
    }
    @NonNull
    @Override
    public MyViewAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter.PersonViewHolder holder, int position) {
        holder.firstNaem.setText(valuesList.get(position).getFirstName());
        holder.lastNeam.setText(valuesList.get(position).getLastName());
        Gender g = valuesList.get(position).getGender();
        holder.pfp.setImageResource(GenderUtils.setImage(g));
    }

    @Override
    public int getItemCount() {
        return valuesList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{



        private TextView firstNaem, lastNeam , phoneNum;
        private ImageView pfp;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNaem = itemView.findViewById(R.id.tvFirstName);
            lastNeam = itemView.findViewById(R.id.tvLastName);
            phoneNum = itemView.findViewById(R.id.tvPhone);
            pfp = itemView.findViewById(R.id.imgIcon);
        }
    }

}
