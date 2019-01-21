package com.ark626.games.cardWars.base.gameobject.enemies;

import java.util.ArrayList;
import com.ark626.games.cardWars.base.engine.Main;
import com.ark626.games.cardWars.base.game.Time;
import com.ark626.games.cardWars.base.game.Util;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.base.gameobject.GameObjectTypes;
import com.ark626.games.cardWars.base.gameobject.StatObject;

public class CookieMonster extends Enemy{
    
    private static int SIZE = 32;
    
    public CookieMonster(float x, float y, int level) {
        
        super(32,level, false);
        init(x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE, game, GameObjectTypes.Enemy,"res/png/test.png");
        setAttackDelay(200);
    }
    
    public void Look() {
        
        ArrayList<GameObject> targets = Main.sphereCollide(x, y, getSigthRange());
        if(targets != null) {
        targets.forEach(gameObject ->{
            if(gameObject.getType().equals(GameObjectTypes.Player)) {
                this.setTarget((StatObject)gameObject);
            }
        });
    }}

    @Override
    public void Chase() {
        
        float speedX = (getTarget().getX())-x;
        float speedY = (getTarget().getY())-y;
        
        float maxSpeed = getStats().getSpeed() * damping*Time.getDelta();
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        
        if((speedX >= 0.1 || speedX <=-0.1) && (speedY >= 0.1 || speedY <=-0.1)) {
            speedX = speedX*0.75f;
            speedY = speedY*0.75f;
        }
        x = x+speedX;
        y = y+speedY;
    }
    

    @Override
    public void Attack() {

        getTarget().getStats().damage((int)this.getStats().getStrength());
        restartAttackDelay();

}
    
    @Override
    public void Death() {

        remove();
    }
}
