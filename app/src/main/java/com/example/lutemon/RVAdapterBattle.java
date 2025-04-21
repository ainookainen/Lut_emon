package com.example.lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.lutemons.Lutemon;

import java.util.ArrayList;
import java.util.HashMap;

public class RVAdapterBattle extends RecyclerView.Adapter<RVAdapterBattle.ViewHolder> {

    private HashMap<Integer, Lutemon> map;
    private ArrayList<Lutemon> lutemons;
    private ArrayList<Lutemon> fighters = new ArrayList<>();

    public RVAdapterBattle(HashMap<Integer, Lutemon> map) {
        this.map = map;
        this.lutemons = new ArrayList<>(map.values());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public ViewHolder(View view) {
            super(view);
            checkBox = (CheckBox) view.findViewById(R.id.LutemonCheckBox);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkbox_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Lutemon lutemon = lutemons.get(position);
        CheckBox checkBox = viewHolder.getCheckBox();
        checkBox.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
        viewHolder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                fighters.add(lutemon);
            } else {
                fighters.remove(lutemon);
            }
        });
    }

    public ArrayList<Lutemon> getFighters() {
        return fighters;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}