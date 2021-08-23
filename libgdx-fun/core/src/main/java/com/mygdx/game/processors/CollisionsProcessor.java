package com.mygdx.game.processors;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.AssetConstants;
import com.mygdx.game.interfaces.domain.Collidable;
import com.mygdx.game.interfaces.domain.Creature;
import com.mygdx.game.interfaces.domain.world.World;
import com.mygdx.game.movement.CollidableType;
import com.mygdx.game.movement.Collision;
import com.mygdx.game.movement.CollisionEffect;
import com.mygdx.game.movement.Move;
import com.mygdx.game.util.VectorUtil;

import java.util.*;
import java.util.stream.Collectors;

public class CollisionsProcessor {

    private static final Vector2 NO_MOVEMENT = new Vector2();

    public void processCollisions(World world, Collection<Move> moves) {
        var enemyMoves = this.getOnlyType(moves, CollidableType.ENEMY);
        this.getOnlyType(moves, CollidableType.HERO)
                .stream()
                .findFirst().ifPresent(heroMove -> {
                    this.collideHeroWithEnemies(world, enemyMoves, heroMove);
                });
        //TODO: var heroWeaponMoves = this.getOnlyType(moves, CollidableType.HERO_WEAPON); for weapon collision bounding box
        this.collideHeroAndEnemiesWithWalls(world, moves);
    }

    private void collideHeroAndEnemiesWithWalls(World world, Collection<Move> moves) {
        var allMovingThings = new ArrayList<>(world.enemies());
        allMovingThings.add(world.hero());

        var movingThingsAndMoves = new HashMap<Collidable, Vector2>();
        allMovingThings.forEach(thing -> movingThingsAndMoves.put(thing, NO_MOVEMENT));
        moves.forEach(move -> movingThingsAndMoves.put(move.getCollidable(), move.getMove()));

        var movableAndWallCollisions = new HashMap<Collidable, Collection<Collision>>();
        for (var wallCenter : world.walls()) {
            var wallBB = BoundingBox.getFromPositionAndMove(wallCenter, NO_MOVEMENT);
            for (var movingThingAndMove : movingThingsAndMoves.entrySet()) {
                var movingThing = movingThingAndMove.getKey();
                var move = movingThingAndMove.getValue();
                var thingPosition = VectorUtil.getVectorFromPositionable(movingThing);
                var thingBB = BoundingBox.getFromPositionAndMove(thingPosition, move);
                thingBB.overlaps(wallBB).ifPresent(contactPoint -> {
                    movableAndWallCollisions.computeIfAbsent(movingThing, k -> new ArrayList<>())
                            .add(new Collision(contactPoint, CollisionEffect.ADJUST_POSITION, 0, NO_MOVEMENT));
                });
            }
        }

        for (var movable : movableAndWallCollisions.keySet()) {
            movable.receiveCollisions(movableAndWallCollisions.get(movable));
        }
    }

    private void collideHeroWithEnemies(World world, Collection<Move> enemyMoves, Move heroMove) {
        var currentPosition = VectorUtil.getVectorFromPositionable(world.hero());
        var heroBB = BoundingBox.getFromPositionAndMove(currentPosition, heroMove.getMove());

        var enemiesAndEnemyMoves = new HashMap<Collidable, Vector2>();
        world.enemies().forEach(enemy -> enemiesAndEnemyMoves.put(enemy, NO_MOVEMENT));
        enemyMoves.forEach(move -> enemiesAndEnemyMoves.put(move.getCollidable(), move.getMove()));

        ///*PLACEHOLDER*/
        var slimeDamage = 1; // TODO: make this originate from enemy itself, hardcoded as PLACEHOLDER

        var enemyAndHeroCollisions = new ArrayList<Collision>();
        for (var enemyAndMove : enemiesAndEnemyMoves.entrySet()) {
            var position = VectorUtil.getVectorFromPositionable(enemyAndMove.getKey());
            var move = enemyAndMove.getValue();
            var enemyBB = BoundingBox.getFromPositionAndMove(position, move);

            heroBB.overlaps(enemyBB).ifPresent(contactPoint -> {
                enemyAndHeroCollisions.add(new Collision(contactPoint, CollisionEffect.DAMAGE, slimeDamage, NO_MOVEMENT));
            });
        }
        world.hero().receiveCollisions(enemyAndHeroCollisions);
        for(Creature creature : world.enemies()){
            creature.receiveCollisions(enemyAndHeroCollisions);
        }
    }

    private static class BoundingBox {
        private static final int HALF_TILE_SIZE = AssetConstants.TILE_SIZE / 2;

        final float boxTop;
        final float boxBottom;
        final float boxLeft;
        final float boxRight;

        private BoundingBox(float boxTop, float boxBottom, float boxLeft, float boxRight) {
            this.boxTop = boxTop;
            this.boxBottom = boxBottom;
            this.boxLeft = boxLeft;
            this.boxRight = boxRight;
        }

        /**
         * The contact point represents the first pixel where "this" is overlapping "otherBoundingBox".
         * @param otherBoundingBox
         * @return
         */
        private Optional<Vector2> overlaps(BoundingBox otherBoundingBox) {
            boolean bottomOverlapsOther = this.boxBottom <= otherBoundingBox.boxTop && this.boxBottom >= otherBoundingBox.boxBottom;
            boolean topOverlapsOther = this.boxTop <= otherBoundingBox.boxTop && this.boxTop >= otherBoundingBox.boxBottom;
            boolean horiz = bottomOverlapsOther || topOverlapsOther;

            boolean leftOverlapsOther = this.boxLeft >= otherBoundingBox.boxLeft && this.boxLeft <= otherBoundingBox.boxRight;
            boolean rightOverlapsOther = this.boxRight >= otherBoundingBox.boxLeft && this.boxRight <= otherBoundingBox.boxRight;
            boolean vert = leftOverlapsOther || rightOverlapsOther;

            if (horiz && vert) {
                var xPointOfContact = leftOverlapsOther
                        ? otherBoundingBox.boxRight
                        : otherBoundingBox.boxLeft;
                var yPointOfContact = topOverlapsOther
                        ? otherBoundingBox.boxBottom
                        : otherBoundingBox.boxTop;
                var pointOfContact = new Vector2();
                pointOfContact.x = xPointOfContact;
                pointOfContact.y = yPointOfContact;
                return Optional.of(pointOfContact);
            }
            return Optional.empty();
        }

        /**
         *
         * @param position : the center
         * @param move
         * @return
         */
        private static BoundingBox getFromPositionAndMove(Vector2 position, Vector2 move) {
            return new BoundingBox(
                    position.y + HALF_TILE_SIZE + move.y, // TODO: top & bottom may be flipped here
                    position.y - HALF_TILE_SIZE + move.y,
                    position.x - HALF_TILE_SIZE + move.x,
                    position.x + HALF_TILE_SIZE + move.x
            );
        }
    }

    private Collection<Move> getOnlyType(Collection<Move> moves, CollidableType collidableType) {
        return moves.stream()
                .filter(move -> collidableType == move.getCollidable().getCollidableType())
                .collect(Collectors.toList());
    }
}
