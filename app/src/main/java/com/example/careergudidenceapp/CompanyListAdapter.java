package com.example.careergudidenceapp;

import android.app.ActionBar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyListAdapter  extends RecyclerView.Adapter<CompanyListAdapter.RecyclerViewHolder> {
    List<CompanyItem> list;
    Context context;

    public CompanyListAdapter(List<CompanyItem> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public CompanyListAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.company_item,parent,false);
        return new CompanyListAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyListAdapter.RecyclerViewHolder holder, int position) {
        holder.name.setText(list.get(position).getCompanyName());
        holder.salary.setText(list.get(position).getSalary());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams layoutParams = holder.salary.getLayoutParams();
                if(layoutParams.width == LinearLayout.LayoutParams.WRAP_CONTENT) {
                    layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                }
                else{
                    layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                }
                holder.salary.setLayoutParams(layoutParams);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView salary;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.company_name);
            salary = itemView.findViewById(R.id.company_salary);
        }
    }
}
