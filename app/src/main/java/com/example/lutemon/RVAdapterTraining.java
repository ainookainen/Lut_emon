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

    public RVAdapterTraining(HashMap<Integer, Lutemon> map) {
        this.map = map;
        this.lutemons = new ArrayList<Lutemon>(map.values());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radioButton;

        public RadioButton getRadioButton() {
            return radioButton;
        }

        public ViewHolder(View view) {
            super(view);
            radioButton = (RadioButton) view.findViewById(R.id.LutemonRadioButton);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.choice_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Lutemon lutemon = lutemons.get(position);
        RadioButton radioButton = viewHolder.getRadioButton();
        radioButton.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
        radioButton.setChecked(lutemon.equals(choice));
        viewHolder.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                choice = lutemon;
                notifyDataSetChanged();
            }
        });
    }

    public Lutemon getChoice() {
        return choice;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}