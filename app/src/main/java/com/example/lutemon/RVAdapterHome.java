package com.example.lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.lutemons.Lutemon;

import java.util.ArrayList;
import java.util.HashMap;

public class RVAdapterHome extends RecyclerView.Adapter<RVAdapterHome.ViewHolder> {

    private HashMap<Integer, Lutemon> map;
    private ArrayList<Lutemon> lutemons;

    public RVAdapterHome(HashMap<Integer, Lutemon> map) {
        this.map = map;
        lutemons = new ArrayList<>(map.values());
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView LutemonImage;
        private final TextView LutemonName;
        private final TextView LutemonStats;

        public ImageView getLutemonImage() {
            return LutemonImage;
        }

        public TextView getLutemonName() {
            return LutemonName;
        }

        public TextView getLutemonStats() {
            return LutemonStats;
        }

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            LutemonImage = (ImageView) view.findViewById(R.id.LutemonImage);
            LutemonName = (TextView) view.findViewById(R.id.LutemonName);
            LutemonStats = (TextView) view.findViewById(R.id.LutemonStats);
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lutemon_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Lutemon lutemon = lutemons.get(position);
        viewHolder.getLutemonName().setText(lutemon.getName()+" ("+lutemon.getColor()+")");
        viewHolder.getLutemonStats().setText("Attack: " + lutemon.getAttack() + "\nDefense: " + lutemon.getDefense() + "\nLife: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() + "\nExperience: " + lutemon.getExperience());
        viewHolder.getLutemonImage().setImageResource(lutemon.getImageId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}