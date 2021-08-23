package com.mygdx.game.assets;

import com.badlogic.gdx.graphics.Texture;

public enum Textures {
    BACKGROUND("basic-tiles.png"),
    CHARACTER("char-tiles.png");

    private final Texture texture;

    Textures(String filename) {
        this.texture = new Texture(filename);
    }

    public Texture getTexture() {
        return texture;
    }
}
