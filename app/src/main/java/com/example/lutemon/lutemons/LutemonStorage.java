package com.example.lutemon.lutemons;

import java.util.HashMap;

public class LutemonStorage {
    private static volatile LutemonStorage instance;
    private final HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private int idCounter = 0;

    public static LutemonStorage getInstance() {
        if (instance == null) {
            synchronized (LutemonStorage.class) {
                if (instance == null) {
                    instance = new LutemonStorage();
                }
            }
        }
        return instance;
    }

    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }

    public void createLutemon(String name, String color) {
        int id = idCounter++;
        Lutemon lutemon;
        switch (color) {
            case "White":
                lutemon = new White(id, name, color);
                break;
            case "Green":
                lutemon = new Green(id, name, color);
                break;
            case "Pink":
                lutemon = new Pink(id, name, color);
                break;
            case "Orange":
                lutemon = new Orange(id, name, color);
                break;
            case "Black":
                lutemon = new Black(id, name, color);
                break;
            default:
                throw new IllegalArgumentException("Invalid Lutemon color");
        }
        lutemons.put(id, lutemon);
    }

    public void kill(Lutemon lutemon) {
        if (lutemon.getHealth() <= 0) {
            lutemons.remove(lutemon.getId());
        }
    }
}
