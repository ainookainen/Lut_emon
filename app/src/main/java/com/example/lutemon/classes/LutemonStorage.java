package com.example.lutemon.classes;

import java.util.HashMap;

public class LutemonStorage {
    private static LutemonStorage instance;
    private HashMap<Integer, Lutemon> lutemons = new HashMap<>();
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


    public Lutemon getLutemon(int id) {
        return lutemons.get(id);
    }

    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }

    public Lutemon createLutemon(String name, String color) {
        int id = idCounter++;
        Lutemon lutemon;
        switch (color) {
            case "White":
                lutemon = new White(id, name);
                break;
            case "Green":
                lutemon = new Green(id, name);
                break;
            case "Pink":
                lutemon = new Pink(id, name);
                break;
            case "Orange":
                lutemon = new Orange(id, name);
                break;
            case "Black":
                lutemon = new Black(id, name);
                break;
            default:
                throw new IllegalArgumentException("Invalid Lutemon color");
        }
        lutemons.put(id, lutemon);
        return lutemon;
    }
    public void kill(Lutemon lutemon){
        if (lutemon.getHealth()<=0){
            lutemons.remove(lutemon.getId());
        }
    }
}
