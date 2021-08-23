package com.mygdx.game.creatures.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.CharacterTiles;
import com.mygdx.game.interfaces.domain.Advancable;
import com.mygdx.game.interfaces.domain.Creature;
import com.mygdx.game.interfaces.domain.Resettable;
import com.mygdx.game.movement.CollidableType;
import com.mygdx.game.movement.Collision;
import com.mygdx.game.movement.DefaultMove;
import com.mygdx.game.movement.Move;

import java.util.Collection;
import java.util.Collections;

import static com.mygdx.game.creatures.hero.Direction.*;

public class Hero implements Creature, Advancable, Resettable {
    private DefaultMove defaultMove;
    private Collection<Collision> collisions;

    private int hitPoints;
    private int oldHitPoints;

    private Vector2 position;
    private Vector2 oldPosition;

    private Direction heroDirection = Direction.DOWN;

    public Hero() {
        this.position = new Vector2();
        this.collisions = Collections.emptySet();
    }

    @Override
    public void recycleOldState() {

    }

    @Override
    public TextureRegion region() {

        switch (heroDirection) {
            case DOWN:
                return CharacterTiles.HERO_FACE_DOWN.getTextureRegion();
            case UP:
                return CharacterTiles.HERO_FACE_UP.getTextureRegion();
            case RIGHT:
                return CharacterTiles.HERO_FACE_RIGHT.getTextureRegion();
            case LEFT:
                return CharacterTiles.HERO_FACE_LEFT.getTextureRegion();
            default:
                System.out.println("you broked it.");
        }
        return CharacterTiles.HERO_FACE_DOWN.getTextureRegion();
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HERO;
    }

    @Override
    public void receiveCollisions(Collection<Collision> collisions) {
        if(!collisions.isEmpty()) {
            Gdx.app.log("Collision detection:", "collide");
        }
        this.collisions = collisions;
    }

    @Override
    public int hitPoints() {
        return 0;
    }

    @Override
    public void hitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void oldHitPoints(int oldHitPoints) {
        this.oldHitPoints = oldHitPoints;
    }

    @Override
    public int damage() {
        return 0;
    }

    @Override
    public void position(Vector2 position) {
        this.position = position;
    }

    public void oldPosition(Vector2 oldPosition) {
        this.oldPosition = oldPosition;
    }

    @Override
    public float positionX() {
        return position.x;
    }

    @Override
    public float positionY() {
        return position.y;
    }

    @Override
    public void reset() {

    }

    public void defaultMove(DefaultMove move) {
        this.defaultMove = move;
    }

    public DefaultMove defaultMove() {
        return this.defaultMove;
    }

    public Move move() {
        Vector2 vector2 = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            heroDirection = RIGHT;
            vector2.x += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            heroDirection = LEFT;
            vector2.x += -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            heroDirection = UP;
            vector2.y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            heroDirection = DOWN;
            vector2.y += -1;
        }
        this.defaultMove = new DefaultMove(this, vector2);
        return defaultMove;
    }

    @Override
    public void processMovement() {
        position.x = position.x + defaultMove.getMove().x;
        position.y = position.y + defaultMove.getMove().y;
    }
}
