package com.ark626.games.cardWars.base.engine.drawing;

public class Frame {
    
    private int length;
    private Sprite sprite;
    private int numDisplayed;
    
    public Frame(Sprite sprite, int length) {
        this.sprite=sprite;
        this.length = length;
        this.numDisplayed = 0;
    }
    
    public boolean render() {
        
        sprite.render();
        numDisplayed++;
        if(numDisplayed>= length) {
            numDisplayed = 0;
            return true;
        }
        return false;
    }

}
