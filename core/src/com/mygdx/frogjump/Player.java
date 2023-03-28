package com.mygdx.frogjump;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
    Rectangle bounds;
    AssetManager manager;
    boolean ground, comefromground, saltoDoble;
    float speedy, gravity;
    int saltos;
    public Player()
    {
        setX(200);
        setY(135);
        setSize(113,92);
        bounds = new Rectangle();
        speedy = 0;
        gravity = 2400f;
        ground=true;
        saltos=3;
        comefromground=true;
        saltoDoble=false;
    }
    @Override
    public void act(float delta){
        //Actualitza la posició del jugador amb la velocitat vertical
        moveBy(0, speedy * delta);
//Actualitza la velocitat vertical amb la gravetat
        speedy -= gravity * delta;
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw( manager.get( ground ? "frog.png" : saltoDoble ? "frog_jump1.png" : "frog_jump2.png", Texture.class), getX(), getY() );
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void setManager(AssetManager manager) {
        this.manager = manager;
    }
    public boolean impulso()
    {
        if (this.ground){
            speedy = 1000f;
            comefromground=true;
            return true;
        } else {
            if (saltos>0&&comefromground){
                speedy = 1000f;
                saltos--;
                saltoDoble=true;
                comefromground=false;
                return true;
            }
        }

        return false;
    }

}