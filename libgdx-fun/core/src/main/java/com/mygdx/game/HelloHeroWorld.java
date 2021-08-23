package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.creatures.enemies.Slime;
import com.mygdx.game.creatures.enemies.SlimeFactory;
import com.mygdx.game.creatures.hero.Hero;
import com.mygdx.game.interfaces.domain.Creature;
import com.mygdx.game.movement.Move;
import com.mygdx.game.processors.CollisionsProcessor;
import com.mygdx.game.processors.Renderer;
import com.mygdx.game.world.DoubleBufferedWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HelloHeroWorld extends ApplicationAdapter {

    private DoubleBufferedWorld world;
    private Renderer renderer;
    private CollisionsProcessor collisionsProcessor;
    private Collection<Slime> slimes;

    @Override
    public void create() {
        this.world = new DoubleBufferedWorld();
        world.hero(new Hero());
        this.renderer = new Renderer();
        this.collisionsProcessor = new CollisionsProcessor();
        world.enemies(Collections.singleton(new Slime(120,120)));

    }

    @Override
    public void render() {
        Collection<Move> moves = new ArrayList<>();
        moves.add(world.hero().move());
        this.collisionsProcessor.processCollisions(this.world, moves);

        var entities = new ArrayList<Creature>();
        entities.addAll(world.enemies());
        entities.add(world.hero());

        entities.forEach(Creature::processMovement);

        // TODO: combat phase processing -john

        // TODO: animation phase processing -dave

        this.world.recycleOldState();
        // TODO: move all this: -dave

        this.renderer.draw(entities);
    }

    @Override
    public void dispose() {
//		this.batch.dispose();
//		this.img.dispose();
    }


}
