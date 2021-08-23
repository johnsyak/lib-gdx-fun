package com.mygdx.game.movement;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.domain.Collidable;
import com.mygdx.game.interfaces.domain.Creature;

public class DefaultMove implements Move {
    private Vector2 vector2;
    private Creature creature;

    public DefaultMove(Creature creature, Vector2 vector2){
        this.vector2 = vector2;
        this.creature = creature;
    }
    @Override
    public Collidable getCollidable() {
        return this.creature;
    }

    @Override
    public Vector2 getMove() {
        return this.vector2;
    }
}
