package com.mygdx.game.assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum BackgroundTiles {
    WALL_1(Textures.BACKGROUND, 0, 0, 32, 32),
    WALL_2(Textures.BACKGROUND, 32, 0, 32, 32),
    GRASS(Textures.BACKGROUND, 64, 0, 32, 32),
    WATER(Textures.BACKGROUND, 96, 0, 32, 32);

    private final TextureRegion textureRegion;

    BackgroundTiles(Textures texture, int ox, int oy, int w, int h) {
        this.textureRegion = new TextureRegion(texture.getTexture(), ox, oy, w, h);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
