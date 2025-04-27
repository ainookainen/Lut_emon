package com.example.lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.lutemons.Lutemon;

import java.util.ArrayList;
import java.util.HashMap;

public class RVAdapterTraining extends RecyclerView.Adapter<RVAdapterTraining.ViewHolder> {

    private HashMap<Integer, Lutemon> map;
    private ArrayList<Lutemon> lutemons;
    private Lutemon choice = null;
    private int lastSelectedPosition = -1;

    public RVAdapterTraining(HashMap<Integer, Lutemon> map) {
        this.map = map;
        this.lutemons = new ArrayList<>(map.values());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radioButton;

        public RadioButton getRadioButton() {
            return radioButton;
        }

        public ViewHolder(View view) {
            super(view);
            radioButton = view.findViewById(R.id.LutemonRadioButton);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.choice_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Lutemon lutemon = lutemons.get(position);
        RadioButton radioButton = viewHolder.getRadioButton();
        radioButton.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
        radioButton.setChecked(position == lastSelectedPosition);
        View.OnClickListener clickListener = v -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;
            int previousSelected = lastSelectedPosition;
            lastSelectedPosition = adapterPosition;
            choice = lutemons.get(adapterPosition);
            if (previousSelected != -1) {
                notifyItemChanged(previousSelected);
            }
            notifyItemChanged(lastSelectedPosition);
        };
        radioButton.setOnClickListener(clickListener);
    }

    public Lutemon getChoice() {
        return choice;
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}