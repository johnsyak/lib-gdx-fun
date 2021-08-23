package com.mygdx.game.creatures.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.CharacterTiles;
import com.mygdx.game.interfaces.domain.Creature;
import com.mygdx.game.movement.CollidableType;
import com.mygdx.game.movement.Collision;
import com.mygdx.game.movement.DefaultMove;
import com.mygdx.game.movement.Move;

import java.util.Collection;

public class Slime implements Creature {

    private Vector2 position;

    public Slime(float initialX, float initialY) {
        this.position = new Vector2();
        this.position.x = initialX;
        this.position.y = initialY;
    }

    @Override
    public TextureRegion region() {
        return CharacterTiles.SLIME_1.getTextureRegion();
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.ENEMY;
    }

    @Override
    public void receiveCollisions(Collection<Collision> collisions) {
        if(!collisions.isEmpty()) {
            Gdx.app.log("Collision detection:", "slime collided");
        }
    }

    @Override
    public int hitPoints() {
        return 10;
    }

    @Override
    public void hitPoints(int hitPoints) {

    }

    @Override
    public int damage() {
        return 1;
    }

    @Override
    public Move move() {
        return new DefaultMove(this, new Vector2());
    }

    @Override
    public void position(Vector2 position) {

    }

    @Override
    public float positionX() {
        return this.position.x;
    }

    @Override
    public float positionY() {
        return this.position.y;
    }

    @Override
    public void processMovement() {

    }
}
