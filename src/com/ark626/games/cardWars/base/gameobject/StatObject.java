package com.ark626.games.cardWars.base.gameobject;

import com.ark626.games.cardWars.base.gameobject.item.Inventory;

public class StatObject extends GameObject{
    private Stats stats;
    private Inventory inventory;
    
    public StatObject(int inventorySize,int exp, boolean levelable) {
        inventory = new Inventory(inventorySize);
        this.stats = new Stats();
        stats.init(exp, levelable);
    }
    
    public void damage(int amount) {
        this.stats.damage(amount);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    
    

}
