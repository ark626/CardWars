package com.ark626.games.cardWars.base.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import com.ark626.games.cardWars.base.gameobject.GameObject;

public class Physics {

    
    public static GameObject checkCollision(GameObject go1, GameObject go2) {
       
        Rectangle r1 = new Rectangle((int)go1.getX(), (int)go1.getY(), (int)go1.getSx(), (int)go1.getSy());
        Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getY(), (int)go2.getSx(), (int)go2.getSy());
        
        boolean result =  r1.intersects(r2);
        
        if(result) {
            return go2;
        }
        return null;
    }
    
   
}
