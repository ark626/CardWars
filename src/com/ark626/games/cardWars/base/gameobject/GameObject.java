package com.ark626.games.cardWars.base.gameobject;

import static org.lwjgl.opengl.GL11.*;
import com.ark626.games.cardWars.base.engine.drawing.Sprite;
import com.ark626.games.cardWars.base.game.Game;

public abstract class GameObject {
    
    protected float x;
    protected float y;
    protected Game game;
    protected boolean[] flags = new boolean[1];

    protected Sprite sprite;
    protected GameObjectTypes type ;
    //Shall be used for Order of Printing Objects
    protected float z;
    
    public void update() {
        
     
    }
    
    public void render() {
        
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            //glViewport((int)x,(int)y,(int)sprite.getSx(),(int)sprite.getSy());
            sprite.render();
        }
        glPopMatrix();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSx() {
        return sprite.getSx();
    }

    public float getSy() {
        return sprite.getSy();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public GameObjectTypes getType() {
        return type;
    }

    public void setType(GameObjectTypes type) {
        this.type = type;
    }
    
    public void remove() {
        //game.removeObect(this);
        this.flags[GameObjectFlagTypes.Remove.ordinal()] = true;
    }
    
    public boolean isRemoved() {
        return this.flags[GameObjectFlagTypes.Remove.ordinal()];
    }
    

    protected void init(float x, float y, float r, float g, float b, float sx, float sy,Game game ,GameObjectTypes type, String spriteFilePath) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.sprite = new Sprite(r, g, b, sx, sy,spriteFilePath);
        this.sprite.init();
    }
    

}
