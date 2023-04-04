package com.mygdx.frogjump;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rock extends Actor {
    Rectangle bounds;
    AssetManager manager;
    int num;
    public Rock()
    {
        int random = ((int) (Math.random()*4+1));
        num = random;
        if (random==1){
            setSize(154, 79);
        } else if (random==2){
            setSize(130, 89);
        } else if (random==2){
            setSize(128, 72);
        } else {
            setSize(164, 66);
        }

        bounds = new Rectangle();
        setVisible(false);

    }
    @Override
    public void act(float delta)
    {
        moveBy(-1200 * delta, 0);
        bounds.set(getX(), getY(), getWidth(), getHeight());
        if(!isVisible())
            setVisible(true);
        if (getX() < -100)
            remove();

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw( manager.get( num==1 ? "rock.png" : num==2 ? "rock2.png" : num==3 ? "rock3.png" : "rock4.png" , Texture.class), getX()-20, getY()+20);
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void setManager(AssetManager manager) {
        this.manager = manager;
    }
}
