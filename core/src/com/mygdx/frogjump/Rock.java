package com.mygdx.frogjump;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rock extends Actor {
    Rectangle bounds;
    AssetManager manager;

    public Rock()
    {
        setSize(154, 79);
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
        batch.draw( manager.get( "rock.png", Texture.class), getX()-20, getY() );
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void setManager(AssetManager manager) {
        this.manager = manager;
    }
}
