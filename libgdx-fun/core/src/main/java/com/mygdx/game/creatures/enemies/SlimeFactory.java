package com.mygdx.game.creatures.enemies;

public class SlimeFactory {

    public static Slime createSlime() {
        float initialX = new Float(Math.random() * 200);
        float initialY = new Float(Math.random() * 200);
        return new Slime(initialX, initialY);
    }
}
