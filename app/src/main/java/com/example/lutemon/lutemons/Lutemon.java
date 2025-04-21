package com.example.lutemon.lutemons;

public abstract class Lutemon {
    private int id;
    private String name;
    private String color;
    private int attack;
    private int defense;
    private int experience;
    private int health;
    private int imageId;
    private int maxHealth;

    public Lutemon(int id, String name, String color, int attack, int defense, int maxHealth, int imageId) {
        this.id=id;
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = 0;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.imageId = imageId;
    }

    public int attack() {
        return this.attack + this.experience + (int) (Math.random() * 3);
    }

    public void defendAgainst(Lutemon attacker) {
        int damage = attacker.attack() - this.defense;
        this.health -= damage;
    }

    public void heal() {
        this.health = maxHealth;
    }

    public void train() {
        experience++;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }
}
