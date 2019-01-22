package com.ark626.games.cardWars.base.game;

import java.util.ArrayList;
import java.util.Random;
import com.ark626.games.cardWars.swarm.Food;
import com.ark626.games.cardWars.swarm.Insect;
import hyper.Pool;

public class Level {
    
    public static final int MAXFOOD = 3;
    
    private Game game;
    public Pool pool;
    public ArrayList<Insect> insects;
    public ArrayList<Food> foods;
    public Random random;
    
    public void init() {
        Pool pool = new Pool(3,2);
        
    }
    
    public void update() {
        //TODO: Spawn food here spawn Insects here
        checkIfEnoughFood();
        
        
        
    }
    
    private void checkIfEnoughFood() {
        
        int count = 0;
        for(Food food:foods) {
            if(!food.isRemoved()) {
                count++;
            }
        }
        if(count<MAXFOOD) {
            spawnFood();
        }
        
    }
    
    private void spawnFood() {
        foods.add(new Food(random.nextInt(800),random.nextInt(600)));
    }

}
