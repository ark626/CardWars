package com.ark626.games.cardWars.base.gameobject;

public class Stats {
    
    public static final double LEVEL_CONST = 25*Math.pow(3,(3.0/2.0));
    
    private int level;
    private int health;
    private float xp;
    private boolean levelable;

    public void init(int xp, boolean levelable) {
        
        this.levelable = levelable;
        if(levelable) {
            this.xp = xp;
        }
        else {
            this.xp = -1;
            this.level = xp;
        }
        health = getMaxHealth();
    }
    

    public void setLevel(int level) {
        this.level = level;
    }
    
    public float getSpeed() {
        return 4f;
    }
    
    public int getLevel() {

        if(!levelable) {
            return level;
        }
        double exp = xp + 105;
        double a = Math.sqrt(243*(exp*exp)+4050*exp+17500);
        double c = (3*exp+25)/25;
        double d = Math.cbrt(a/LEVEL_CONST+c);
        return (int)(d-1.0/d*3);
    }
    
    public int getMaxHealth() {
        return getLevel() * 10;
    }
    
    public int getCurrentHealth() {
        
        if(health > getMaxHealth()) {
            health = getMaxHealth();
        }
        return health;
    }
    
    public void addXP(float amount) {
        xp += amount;
    }
    
    public float getStrength() {
        return getLevel() * 4;
    }

    public void damage(int amount) {
        this.health-=amount;
    }
    
}
