package com.ark626.games.cardWars.swarm;

import java.util.ArrayList;
import com.ark626.games.cardWars.base.engine.Main;
import com.ark626.games.cardWars.base.game.Util;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.base.gameobject.GameObjectTypes;
import com.ark626.games.cardWars.base.gameobject.Player;
import com.ark626.games.cardWars.base.gameobject.item.Item;

public class Food extends Item {


    public int feedHeal;
    public int feedAmount;

    public Food(int x, int y) {
        super(null);
        init(x, y, 1.0f, 0.5f , 0, 32, 32, "The Cube",game,"res/png/test.png");
        // TODO Auto-generated constructor stub
    }

    public void update() {
        ArrayList<GameObject> gameObjects = Main.sphereCollide(x, y, 20f);
        if(!gameObjects.isEmpty()) {
            pickUp(closestOneThatCanEat(gameObjects));
        }
    }
    
    public void pickUp(GameObject gameObject) {
        
        //TODO: Implement feed etc.
        feedAmount -= 1;
        if(feedAmount<0) {
        this.remove();
        }
        
        
    }

    public GameObject closestOneThatCanEat(ArrayList<GameObject> objects) {

        float minDist = Float.MAX_VALUE;
        GameObject result = null;
        for (GameObject gameObject : objects) {
            if (minDist > Util.dist(x, y, gameObject.getX(), gameObject.getY())&&gameObject.getType().equals(GameObjectTypes.Insect)) {
                if(((Insect)gameObject).canFeed()){
                minDist = Util.dist(x, y, gameObject.getX(), gameObject.getY());
                result = gameObject;
                }
            }
        }
        if(result != null) {
            ((Insect)result).feed();
        }
        return result;
    }


}
