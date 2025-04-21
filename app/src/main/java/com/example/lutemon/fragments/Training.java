package com.example.lutemon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lutemon.RVAdapterTraining;
import com.example.lutemon.databinding.FragmentTrainingBinding;
import com.example.lutemon.lutemons.Lutemon;
import com.example.lutemon.lutemons.LutemonStorage;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Training extends Fragment {
    private FragmentTrainingBinding binding;
    private RVAdapterTraining adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrainingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HashMap<Integer, Lutemon> map = LutemonStorage.getInstance().getLutemons();

        adapter = new RVAdapterTraining(map);
        binding.RVTrainingChoice.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.RVTrainingChoice.setAdapter(adapter);
        binding.TrainingButton.setOnClickListener(v -> {
            Lutemon choice = adapter.getChoice();
            if (choice == null) {
                Snackbar.make(requireContext(), view, "Please select a Lutemon to train first.", Snackbar.LENGTH_SHORT).show();
                return;
            }
            choice.train();
            Snackbar.make(requireContext(), view, "Lutemon '" + choice.getName() + "' attack and experience has increased!", Snackbar.LENGTH_SHORT).show();
        });
    }
}