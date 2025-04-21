package com.example.lutemon.fragments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lutemon.lutemons.Lutemon;

import java.util.ArrayList;

public class BattleViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Lutemon>> fighters = new MutableLiveData<>();

    public void setFighters(ArrayList<Lutemon> fighters) {
        this.fighters.setValue(new ArrayList<>(fighters));
    }

    public MutableLiveData<ArrayList<Lutemon>> getFighters() {
        return fighters;
    }

}
