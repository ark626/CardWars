package com.ark626.games.cardWars.base.gameobject;

import org.lwjgl.input.Keyboard;
import com.ark626.games.cardWars.base.game.Game;
import com.ark626.games.cardWars.base.gameobject.item.Inventory;
import com.ark626.games.cardWars.base.gameobject.item.Item;

public class Player extends StatObject{
    
    public static final float SIZE = 32;
    
    
    public Player(float x, float y, Game game) {
        
        super(32,0,true);
        init(x,y,0.1f, 1f, 0.25f, SIZE,SIZE,game,GameObjectTypes.Player,"res/png/test2.png");
        
        
    }
    
    @Override
    public void update() {
        //System.out.println("Stats: Level: "+getLevel()+" MaxHP: " + getMaxHealth()+" HP: "+getCurrentHealth() );
    }
    
    public void getInput() {
        
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) 
            move(0,1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) 
            move(0,-1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) 
            move(-1,0);
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) 
            move(1,0);
        
    }


    private void move(int magX, int magY) {
        float speedX = getStats().getSpeed()* magX;
        float speedY = getStats().getSpeed()* magY;
        
        if((speedX >= 0.1 || speedX <=-0.1) && (speedY >= 0.1 || speedY <=-0.1)) {
            speedX = speedX*0.75f;
            speedY = speedY*0.75f;
        }
        
        x += speedX;
        y += speedY;
                
    }
    


    
    public void addItem(Item item) {
        
    }
    
  
}
