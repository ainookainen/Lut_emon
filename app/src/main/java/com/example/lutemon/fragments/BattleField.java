package com.example.lutemon.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lutemon.BattleManager;
import com.example.lutemon.R;
import com.example.lutemon.databinding.FragmentBattleFieldBinding;
import com.example.lutemon.lutemons.Lutemon;
import com.example.lutemon.lutemons.LutemonStorage;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class BattleField extends Fragment {
    private FragmentBattleFieldBinding binding;
    private BattleViewModel viewModel;
    private BattleManager battleManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBattleFieldBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(BattleViewModel.class);
        battleManager = new BattleManager(this);

        viewModel.getFighters().observe(getViewLifecycleOwner(), fighters -> {
            if (fighters != null && fighters.size() == 2) {
                battleManager.setFighters(fighters);
                binding.Fighter1Image.setImageResource(fighters.get(0).getImageId());
                binding.Fighter2Image.setImageResource(fighters.get(1).getImageId());
                battleManager.startBattle();
            }
        });
    }

    public void flipSword(int attackerIndex) {
        ImageView sword = binding.SwordImage;
        sword.setRotation(20);
        sword.setImageResource(R.drawable.sword_pixel);
        sword.setScaleX(1);
        sword.setScaleY(1);
        if (attackerIndex == 0) {
            sword.setScaleX(-1);
        } else {
            sword.setScaleY(-1);
        }
    }

    public void logBattle(Lutemon attacker, Lutemon defender, int logNr) {
        binding.BattleLog.append(logNr + ": " + attacker.getName() + " (" + attacker.getColor() + ") att: " + attacker.getAttack() + "; def: " + attacker.getDefense() + "; exp: " + attacker.getExperience() + "; health: " + attacker.getHealth() + "/" + attacker.getMaxHealth() + "\n");
        binding.BattleLog.append((3 - logNr) + ": " + defender.getName() + " (" + defender.getColor() + ") att: " + defender.getAttack() + "; def: " + defender.getDefense() + "; exp: " + defender.getExperience() + "; health: " + defender.getHealth() + "/" + defender.getMaxHealth() + "\n");
    }

    public void logAttack(Lutemon attacker, Lutemon defender) {
        binding.BattleLog.append(attacker.getName() + " (" + attacker.getColor() + ") attacks " + defender.getName() + " (" + defender.getColor() + ")!\n");
    }

    public void logDefenderKilled(Lutemon defender) {
        binding.BattleLog.append(defender.getName() + " (" + defender.getColor() + ") gets killed.\nThe battle is over. :)\n");
    }

    public void logDefenderSurvived(Lutemon defender) {
        binding.BattleLog.append(defender.getName() + " (" + defender.getColor() + ") manages to escape death.\n\n");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (battleManager != null) {
            battleManager.stopBattle();
        }
    }
}

