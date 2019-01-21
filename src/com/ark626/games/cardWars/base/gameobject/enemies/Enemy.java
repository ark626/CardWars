package com.ark626.games.cardWars.base.gameobject.enemies;

import com.ark626.games.cardWars.base.game.Util;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.base.gameobject.Stats;

public class Enemy extends GameObject{
    
    protected Stats stats;
    private GameObject target;
    
    public Enemy(int level) {
       
        stats = new Stats();
        target = null;
        
    }
    
    
    public void update() {
        if(target == null) {
            Look();
        }
        else {
            Chase();
            if(Util.LineSight(this, target)) {
                Attack();
            }
        }
        if(stats.getCurrentHealth()<=0) {
            Die();
        }
    }
    
    public void Attack() {
        
    }
    
    public void Idle() {
        
    }
    
    public void Look() {
        
    }
    
    public void Chase() {
        
    }
    
    public void Die() {
        
    }
    
    public void setTarget(GameObject gameObject) {
        this.target = gameObject;
    }


    public GameObject getTarget() {
        return target;
    }

    
}
