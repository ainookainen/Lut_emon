package com.example.lutemon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lutemon.R;
import com.example.lutemon.RVAdapterBattle;
import com.example.lutemon.classes.Lutemon;
import com.example.lutemon.classes.LutemonStorage;
import com.example.lutemon.databinding.FragmentBattleBinding;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Battle extends Fragment {

    private FragmentBattleBinding binding;
    private RVAdapterBattle adapter;
    private BattleViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBattleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(BattleViewModel.class);
        HashMap<Integer, Lutemon> map = LutemonStorage.getInstance().getLutemons();
        adapter = new RVAdapterBattle(map);
        binding.RVBattle.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.RVBattle.setAdapter(adapter);
        binding.BattleButton.setOnClickListener(v -> {
            ArrayList<Lutemon> fighters = adapter.getFighters();
            if (fighters.size() == 2) {
                // TODO: 19.4.2025 Battle :)
                Snackbar.make(view, "Ready to battle!", Snackbar.LENGTH_SHORT).show();
                viewModel.setFighters(fighters);
                Navigation.findNavController(v).navigate(R.id.action_battle_to_battleField);
            } else {
                Snackbar.make(view, "Select exactly two Lutemons!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}