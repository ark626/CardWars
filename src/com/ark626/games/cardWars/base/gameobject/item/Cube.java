package com.ark626.games.cardWars.base.gameobject.item;

import com.ark626.games.cardWars.base.game.Game;
import com.ark626.games.cardWars.base.gameobject.Player;

public class Cube extends Item {

    public static final float SIZE = 32;
    
    public Cube(float x, float y,Game game,Player player) {
        super(player);
        init(x, y, 1.0f, 0.5f , 0, SIZE, SIZE, "The Cube",game,"res/png/test.png");
        //this.sprite.setTexture();
    }
    

}
