package com.ark626.games.cardWars.swarm;

public class Color {
    
    private float r;
    private float g;
    private float b;
    private int hash;
    
    
    
    
    public Color(float r, float g, float b, int hashCode) {
        super();
        this.r = r;
        this.g = g;
        this.b = b;
        this.hash = hashCode;
    }
    public float getR() {
        return r;
    }
    public void setR(float r) {
        this.r = r;
    }
    public float getG() {
        return g;
    }
    public void setG(float g) {
        this.g = g;
    }
    public float getB() {
        return b;
    }
    public void setB(float b) {
        this.b = b;
    }
    public int getHash() {
        return hash;
    }
    public void setHash(int hashCode) {
        this.hash = hashCode;
    }
    
    
    
}
