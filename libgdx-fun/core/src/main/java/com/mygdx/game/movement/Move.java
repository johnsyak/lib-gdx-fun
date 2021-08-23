package com.mygdx.game.movement;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.domain.Collidable;

public interface Move {

    Collidable getCollidable();

    Vector2 getMove();
}
