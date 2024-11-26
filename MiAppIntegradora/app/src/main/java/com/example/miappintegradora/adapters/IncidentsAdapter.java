package com.example.miappintegradora.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IncidentsAdapter extends RecyclerView.Adapter<IncidentsAdapter.IncidentsViewHolder> {

    private ArrayList<String> incidentsList;

    public IncidentsAdapter(ArrayList<String> incidentsList) {
        this.incidentsList = incidentsList;
    }

    @Override
    public IncidentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new IncidentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncidentsViewHolder holder, int position) {
        holder.incidentTextView.setText(incidentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return incidentsList.size();
    }

    public class IncidentsViewHolder extends RecyclerView.ViewHolder {
        public TextView incidentTextView;

        public IncidentsViewHolder(View view) {
            super(view);
            incidentTextView = view.findViewById(android.R.id.text1);
        }
    }
}

