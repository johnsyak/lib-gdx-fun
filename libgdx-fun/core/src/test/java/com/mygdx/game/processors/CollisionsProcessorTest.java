package com.mygdx.game.processors;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.assets.AssetConstants;
import com.mygdx.game.creatures.hero.Hero;
import com.mygdx.game.interfaces.domain.world.World;
import com.mygdx.game.movement.CollidableType;
import com.mygdx.game.movement.Collision;
import com.mygdx.game.movement.CollisionEffect;
import com.mygdx.game.movement.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CollisionsProcessorTest {

    @Captor
    ArgumentCaptor<Collection<Collision>> collisionCaptor;

    private CollisionsProcessor collisionsProcessor;

    @BeforeEach
    void setup() {
        this.collisionsProcessor = new CollisionsProcessor();
    }

    /**
     * Hero ix 32x32, starts at (50,50).
     * Wall is 32x32.
     */
    @Test
    void testWallCollision() {
        // move
        Move move = mock(Move.class);
        Vector2 moveVector = new Vector2();
        moveVector.y = 10f;
        when(move.getMove()).thenReturn(moveVector);
        // hero
        Hero hero = mock(Hero.class);
        when(hero.getCollidableType()).thenReturn(CollidableType.HERO);
        when(hero.positionX()).thenReturn(50f);
        when(hero.positionY()).thenReturn(50f);
        // walls
        Vector2 wall = new Vector2();
        wall.x = 50f;
        wall.y = 50f + AssetConstants.TILE_SIZE + 1f;
        // world
        World world = mock(World.class);
        when(world.hero()).thenReturn(hero);
        when(move.getCollidable()).thenReturn(hero);
        when(world.walls()).thenReturn(singleton(wall));
        
        collisionsProcessor.processCollisions(world, singleton(move));

        verify(hero, times(2)).receiveCollisions(this.collisionCaptor.capture());
        var actual = collisionCaptor.getAllValues().get(1).stream().findAny().get();
        assertEquals(new Vector2(66f, 67f), actual.getContactPoint());
        assertEquals(CollisionEffect.ADJUST_POSITION, actual.getCollisionEffect());
    }

}