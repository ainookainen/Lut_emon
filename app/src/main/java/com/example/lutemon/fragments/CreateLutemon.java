package com.example.lutemon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.lutemon.R;
import com.example.lutemon.databinding.FragmentCreateLutemonBinding;
import com.example.lutemon.lutemons.LutemonStorage;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.Nullable;

public class CreateLutemon extends Fragment {
    private FragmentCreateLutemonBinding binding;
    private String color;

    public CreateLutemon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateLutemonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.CreateLutemonButton.setOnClickListener(this::createLutemon);
        setUpRadioButtons();
    }

    private void setUpRadioButtons() {
        binding.WhiteRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "White";
                binding.CreateLutemonImage.setImageResource(R.drawable.white_lutemon);
            }
        });
        binding.GreenRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "Green";
                binding.CreateLutemonImage.setImageResource(R.drawable.green_lutemon);
            }
        });
        binding.PinkRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "Pink";
                binding.CreateLutemonImage.setImageResource(R.drawable.pink_lutemon);
            }
        });
        binding.OrangeRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "Orange";
                binding.CreateLutemonImage.setImageResource(R.drawable.orange_lutemon);
            }
        });
        binding.BlackRadioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                color = "Black";
                binding.CreateLutemonImage.setImageResource(R.drawable.black_lutemon);
            }
        });
    }

    private void createLutemon(View view) {
        String name = binding.LutemonName.getText().toString();
        if (name.isEmpty()) {
            Snackbar.make(requireContext(), view, "Please enter a name for your Lutemon.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        try {
            LutemonStorage.getInstance().createLutemon(name, color);
            Snackbar.make(requireContext(), view, "Created " + color + " Lutemon: " + name, Snackbar.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigateUp();
        } catch (Exception e) {
            Snackbar.make(requireContext(), view, "Error creating your Lutemon. Please select a color and try again.", Snackbar.LENGTH_SHORT).show();
        }
    }
}