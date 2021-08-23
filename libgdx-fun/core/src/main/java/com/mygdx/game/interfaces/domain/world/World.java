package com.mygdx.game.interfaces.domain.world;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.domain.Creature;

import java.util.Collection;

public interface World {
    void hero(Creature hero);
    Creature hero();
    void enemies(Collection<Creature> enemies);
    Collection<Creature> enemies();
    Collection<Vector2> walls();
}
