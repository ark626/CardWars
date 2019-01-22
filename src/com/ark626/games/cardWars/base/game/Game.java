package com.ark626.games.cardWars.base.game;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.Display;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.base.gameobject.Player;
import com.ark626.games.cardWars.base.gameobject.enemies.CookieMonster;
import com.ark626.games.cardWars.base.gameobject.item.Cube;

public class Game {
    
    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> remove;
    private Level level;
    //private ArrayList<TextureHelper> textures;
    private Player player;
    
    public Game() {
        objects =  new ArrayList<GameObject>();
        remove = new ArrayList<GameObject>();
        //textures = new ArrayList<TextureHelper>();
        level = new Level();
        player = new Player(Display.getWidth()/2-Player.SIZE/2,
                Display.getHeight()/2-Player.SIZE/2,this);
        objects.add(player);
        objects.add(new Cube(32, 32,this,player));
        objects.add(new CookieMonster(100, 100, 1));
    }
    
    public void getInput() {
        player.getInput();
    }
    
    public void update() {
        
        
        objects.forEach( gameObject->{
            if(!gameObject.isRemoved()) {
                gameObject.update();  
            }
            else {
                remove.add(gameObject);
            }        
        });
        clean();
    }
    
 public  ArrayList<GameObject> sphereCollide(float x, float y,float radius) {
        
        ArrayList<GameObject> res = new ArrayList<GameObject>();
        
        for(GameObject go:objects) {
            
            if(Util.dist(go.getX(), go.getY(), x, y)< radius) {
                res.add(go);
            }
        }
        return res;
        
    }
    
    private void clean() {
        remove.forEach(gameObject->{
            if(gameObject.getSprite().getTexture()!= null){
                gameObject.getSprite().getTexture().release();
            }
            objects.remove(gameObject);
        });
        remove.clear();
    }
    
    public void render() {
        objects.forEach( gameObject->{
            gameObject.render();
        });
    }
    
    private void removeObjectFromList(GameObject go, List<Object> from) {
        from.forEach( gameObject->{        
            if(go==gameObject) {
                objects.remove(go);
            }
            
    });
    }
    
    public void removeObect(GameObject go) {
        if(level != null) {
             if(level.insects!= null){
                 level.insects.remove(go);
            }
             if(level.foods!= null){
                 level.foods.remove(go);
            }
        }
        objects.forEach( gameObject->{
            if(go==gameObject) {
                objects.remove(go);
            }
        });
        
    }
    
    private boolean isGameObjectInList(GameObject go) {
        
        for(GameObject gameObject:objects) {
            if(go==gameObject) {
                return true;
            }
        }
        return false;
    }
    
    public void addGameObject(GameObject go) {
        this.objects.add(go);
    }

}
