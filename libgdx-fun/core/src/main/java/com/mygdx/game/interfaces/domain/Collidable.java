package com.mygdx.game.interfaces.domain;

import com.mygdx.game.movement.CollidableType;
import com.mygdx.game.movement.Collision;

import java.util.Collection;

public interface Collidable extends Positionable {

    CollidableType getCollidableType();

    void receiveCollisions(Collection<Collision> collisions);
}
