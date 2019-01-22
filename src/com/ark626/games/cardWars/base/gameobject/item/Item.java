package com.ark626.games.cardWars.base.gameobject.item;

import com.ark626.games.cardWars.base.engine.Main;
import com.ark626.games.cardWars.base.engine.drawing.Sprite;
import com.ark626.games.cardWars.base.game.Game;
import com.ark626.games.cardWars.base.game.Physics;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.base.gameobject.GameObjectTypes;
import com.ark626.games.cardWars.base.gameobject.Player;

public class Item extends GameObject {
    
    protected String name;
    protected Player player;
    
    public Item(Player player) {
        this.player = player;
    }
    
    public void pickUp() {
        
        System.out.println("You picked up "+this.name);
        player.addItem(this);
        this.remove();
        
        
    }
    
    public void update() {
        if(Physics.checkCollision(this, player)!=null) {
            pickUp();
        }
    }
    
     


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void init(float x, float y, float r, float g, float b, float sx, float sy, String name, Game game, String spriteFilePath) {
        
        this.type = GameObjectTypes.Item;
        this.name = name;
        this.init(x, y, r, g, b, sx, sy, game, type, spriteFilePath);

        
    }

}
