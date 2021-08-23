package com.mygdx.game.interfaces.domain;

import com.badlogic.gdx.math.Vector2;

public interface Positionable {
    void position(Vector2 position);

    float positionX();

    float positionY();
}
