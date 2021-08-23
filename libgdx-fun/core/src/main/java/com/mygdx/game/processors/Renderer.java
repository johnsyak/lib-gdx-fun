package com.mygdx.game.processors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.interfaces.Drawable;
import com.mygdx.game.interfaces.domain.Positionable;

import java.util.Collection;

public class Renderer {

    private final SpriteBatch batch;

    public Renderer() {
        this.batch = new SpriteBatch();
    }

    public <D extends Drawable & Positionable> void draw(Collection<D> drawables) {
        ScreenUtils.clear(0, 0, 0, 1);
        this.batch.begin();
        for (var drawable : drawables) {
            this.batch.draw(drawable.region(), drawable.positionX(), drawable.positionY());
        }
        this.batch.end();
    }
}
