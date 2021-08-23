package com.mygdx.game.world;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.domain.Advancable;
import com.mygdx.game.interfaces.domain.Creature;
import com.mygdx.game.interfaces.domain.Resettable;
import com.mygdx.game.interfaces.domain.world.World;

import java.util.Collection;
import java.util.Collections;

/**
 * See javadoc on {@link com.mygdx.game.interfaces.domain.Resettable}.
 */
public class DoubleBufferedWorld implements World, Advancable, Resettable {

    private Creature hero;
    /**
     * TODO: may want to make this double buffered:
     */
    private Collection<Creature> enemies;

    public DoubleBufferedWorld() {
        this.enemies = Collections.emptySet();
    }

    @Override
    public void hero(Creature hero) {
        this.hero = hero;
        //assert hero != this.hero();
    }

    @Override
    public Creature hero() {
        return this.hero;
    }

    @Override
    public void enemies(Collection<Creature> enemies) {
        /*
         * Generally speaking, Object#deepEquals and Arrays#deepEquals are inadequate for anything except
         * primitives, but in this case, the default (identity comparison) in Object#deepEquals, is adequate,
         * since preventing shared state requires EITHER checking non-equality of identity on objects OR that
         * pooled & compared objects have ids and have correctly overridden Object#equals and Object#hashCode.
         */
//        assert !deepEquals(enemies, this.enemies());
        this.enemies = enemies;
    }

    @Override
    public Collection<Creature> enemies() {
        return this.enemies;
    }

    @Override
    public Collection<Vector2> walls() {
        // TODO: set this up via Tiled
        return Collections.emptySet();
    }

    /**
     * Call this immediately before drawing the screen. All world state for the next frame should
     * be considered final at this point. It's "previousWorld" which will be drawn to screen.
     */
    public void recycleOldState() {

    }

    @Override
    public void reset() {

    }
}
