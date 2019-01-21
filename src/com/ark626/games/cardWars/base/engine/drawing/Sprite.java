package com.ark626.games.cardWars.base.engine.drawing;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sprite {

    private float r;
    private float g;
    private float b;

    private float sx;
    private float sy;
    private Texture texture;
    private String textureFilePath;



    public Sprite(float r, float g, float b, float sx, float sy, String spriteFilePath) {
        super();
        this.r = r;
        this.g = g;
        this.b = b;
        this.sx = sx;
        this.sy = sy;
        this.textureFilePath = spriteFilePath;
    }

    public void init() {


        try {
            // load texture from PNG file
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(textureFilePath), false);
            
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);


            System.out.println("Texture loaded: " + texture);
            System.out.println(">> Image width: " + texture.getImageWidth());
            System.out.println(">> Image height: " + texture.getImageHeight());
            System.out.println(">> Texture width: " + texture.getTextureWidth());
            System.out.println(">> Texture height: " + texture.getTextureHeight());
            System.out.println(">> Texture ID: " + texture.getTextureID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render() {


        glEnable(GL_TEXTURE_2D);
        //glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);

        float x = 1;
        float y = 1;
        if (texture != null) {
            texture.bind();
            y = texture.getHeight();//texture.getWidth();
            x = texture.getWidth();
            // glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
        }
        //glViewport(0, 0, (int)sx, (int)sy);
        //this.reshape((int)sx, (int)sy);
        glBegin(GL_QUADS);
        {
            glColor3f(r, g, b);
            glTexCoord2f(0, y);
            glVertex2f(0, 0);
            glTexCoord2f(x, y);
            glVertex2f(sx, 0);
            glTexCoord2f(x, 0);
            glVertex2f(sx, sy);
            glTexCoord2f(0, 0);
            glVertex2f(0, sy);
            



        }
        glEnd();

    }

    void reshape(int w, int h) {
        glViewport(0, 0, w, h);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
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

    public float getSx() {
        return sx;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public float getSy() {
        return sy;
    }

    public void setSy(float sy) {
        this.sy = sy;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        this.init();
    }

    public String getTextureFilePath() {
        return textureFilePath;
    }

    public void setTextureFilePath(String textureFilePath) {
        this.textureFilePath = textureFilePath;
        this.init();
    }

    

}
