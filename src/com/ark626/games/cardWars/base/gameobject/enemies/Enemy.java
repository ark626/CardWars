package com.ark626.games.cardWars.base.gameobject.enemies;

import com.ark626.games.cardWars.base.game.Delay;
import com.ark626.games.cardWars.base.game.Util;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.base.gameobject.StatObject;
import com.ark626.games.cardWars.base.gameobject.Stats;

public class Enemy extends StatObject{
    
    private StatObject target;
    protected float damping = 0.5f;
    private float attackRange;
    private float sigthRange = 128f;
    private Delay attackDelay;
    
    public Enemy(int inventorySize,int level,boolean levelable) {
       
        super(inventorySize,level,levelable);
        target = null;
        attackDelay = new Delay(0);
        attackRange = 48f;
        attackDelay.end();
        
    }
    
    
    public void update() {
        if(target == null) {
            Look();
        }
        else {
            
            if(Util.LineSight(this, target)&&(Util.dist(x, y, getTarget().getX(), getTarget().getY()))<= attackRange) {
                if(attackDelay.over()) {
                Attack();
                }
            }
            else {
                Chase();
            }
        }
        if(this.getStats().getCurrentHealth()<=0) {
            Death();
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
    
    public void Death() {
        remove();
    }
    
    public void setTarget(StatObject gameObject) {
        this.target = gameObject;
    }

    

    public float getDamping() {
        return damping;
    }


    public void setDamping(float damping) {
        this.damping = damping;
    }


    public float getAttackRange() {
        return attackRange;
    }


    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }


    public float getSigthRange() {
        return sigthRange;
    }


    public void setSigthRange(float sigthRange) {
        this.sigthRange = sigthRange;
    }


    public Delay getAttackDelay() {
        return attackDelay;
    }

    public StatObject getTarget() {
        return target;
    }
    
    public void setAttackDelay(int time) {
        attackDelay = new Delay(time);
        attackDelay.end();
    }

    public void restartAttackDelay() {
        attackDelay.start();
    }
    
}
