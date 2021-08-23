package com.mygdx.game.interfaces.domain;

/**
 * For use with double-buffered objects, resets all to default state, as if creating with blank constructor.
 * All subfields which implement {@link Resettable} should also call reset.
 *
 * In combination with a double buffer, this is a more efficient alternative to the builder pattern.
 */
public interface Resettable {
    void reset();
}
