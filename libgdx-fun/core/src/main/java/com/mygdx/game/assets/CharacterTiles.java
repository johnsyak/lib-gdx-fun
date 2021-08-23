package com.mygdx.game.assets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum CharacterTiles {
    HERO_FACE_DOWN(Textures.CHARACTER, 0, 0, 32, 32),
    HERO_FACE_UP(Textures.CHARACTER, 32, 0, 32, 32),
    HERO_FACE_RIGHT(Textures.CHARACTER, 64, 0, 32, 32),
    HERO_FACE_LEFT(Textures.CHARACTER, 96, 0, 32, 32),
    HERO_WALK_DOWN(Textures.CHARACTER, 0, 32, 32, 32),
    HERO_WALK_UP(Textures.CHARACTER, 32, 32, 32, 32),
    HERO_WALK_RIGHT(Textures.CHARACTER, 64, 32, 32, 32),
    HERO_WALK_LEFT(Textures.CHARACTER, 96, 32, 32, 32),
    SLIME_1(Textures.CHARACTER, 0, 64, 32, 32),
    SLIME_2(Textures.CHARACTER, 32, 64, 32, 32),
    STAFF_HORIZ(Textures.CHARACTER, 64, 64, 32, 32),
    STAFF_VERT(Textures.CHARACTER, 96, 64, 32, 32);

    private final TextureRegion textureRegion;

    CharacterTiles(Textures texture, int ox, int oy, int w, int h) {
        this.textureRegion = new TextureRegion(texture.getTexture(), ox, oy, w, h);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
