package com.mygdx.game.temp;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.CharacterTiles;
import com.mygdx.game.interfaces.Drawable;
import com.mygdx.game.interfaces.domain.Positionable;

public class TestDrawable implements Drawable, Positionable {

    private Vector2 position;

    public TestDrawable() {
        this.position = new Vector2();
        this.position.x = 30;
        this.position.y = 40;
    }

    @Override
    public TextureRegion region() {
        return CharacterTiles.HERO_FACE_DOWN.getTextureRegion();
    }

    @Override
    public void position(Vector2 position) {

    }

    @Override
    public float positionX() {
        return this.position.x;
    }

    @Override
    public float positionY() {
        return this.position.y;
    }
}
