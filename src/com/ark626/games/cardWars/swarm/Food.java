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
        init(x, y, 255f, 255f , 0, 32, 32, "The Cube",game,null);
        feedHeal = 2000;
        feedAmount = 1;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        ArrayList<GameObject> gameObjects = Main.sphereCollide(x, y, 30f);
        if(!gameObjects.isEmpty()) {
            pickUp(closestOneThatCanEat(gameObjects));
        }
    }
    
    public void pickUp(GameObject gameObject) {
        
        if(gameObject != null) {
        //TODO: Implement feed etc.
        feedAmount -= 1;
        //System.out.println("Picked Up from "+gameObject);
        if(feedAmount<0) {
        this.remove();
        }
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
            ((Insect)result).feed(feedHeal);
        }
        return result;
    }


}
