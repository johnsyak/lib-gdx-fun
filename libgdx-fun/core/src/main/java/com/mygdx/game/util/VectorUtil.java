package com.mygdx.game.util;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.domain.Positionable;

public class VectorUtil {

    public static Vector2 getVectorFromPositionable(Positionable positionable) {
        Vector2 vector = new Vector2();
        vector.x = positionable.positionX();
        vector.y = positionable.positionY();
        return vector;
    }
}
