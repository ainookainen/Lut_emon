package com.example.lutemon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lutemon.R;
import com.example.lutemon.RVAdapterHome;
import com.example.lutemon.databinding.FragmentHomeBinding;
import com.example.lutemon.lutemons.Lutemon;
import com.example.lutemon.lutemons.LutemonStorage;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Home extends Fragment {
    private FragmentHomeBinding binding;
    private RVAdapterHome adapter;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RVAdapterHome(LutemonStorage.getInstance().getLutemons());
        binding.CreateNewLutemon.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_home_to_createNewLutemon));
        HashMap<Integer, Lutemon> map = LutemonStorage.getInstance().getLutemons();
        adapter = new RVAdapterHome(map);
        binding.RVLutemons.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.RVLutemons.setAdapter(adapter);
    }
}
