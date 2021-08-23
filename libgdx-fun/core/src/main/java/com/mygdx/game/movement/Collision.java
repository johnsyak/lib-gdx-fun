package com.mygdx.game.movement;

import com.badlogic.gdx.math.Vector2;

public class Collision {

    private final Vector2 contactPoint;
    private final CollisionEffect collisionEffect;
    private final int damage;
    private final Vector2 adjustPosition;

    public Collision(Vector2 contactPoint, CollisionEffect collisionEffect, int damage, Vector2 adjustPosition) {
        this.contactPoint = contactPoint;
        this.collisionEffect = collisionEffect;
        this.damage = damage;
        this.adjustPosition = adjustPosition;
    }

    public Vector2 getContactPoint() {
        return contactPoint;
    }

    public CollisionEffect getCollisionEffect() {
        return collisionEffect;
    }

    public int getDamage() {
        return damage;
    }

    public Vector2 getAdjustPosition() {
        return adjustPosition;
    }
}
