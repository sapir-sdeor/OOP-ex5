package pepse.world.trees;

import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.world.Block;
import pepse.world.Terrain;

import java.awt.*;
import java.util.Random;
import java.util.function.Function;

public class Tree {

    private static final Color LEAF_COLOR = new Color(50,200,30);
    private static final Color TRUNK_COLOR = new Color(100, 50, 20);
    private final Function<Float, Float> callback;
    private final Vector2 windowDimensions;
    private final GameObjectCollection gameObjects;

    public Tree(Function<Float, Float> callback, Vector2 windowDimensions, GameObjectCollection gameObjects){
        this.callback = callback;
        this.windowDimensions = windowDimensions;
        this.gameObjects = gameObjects;
    }
    public void createInRange(int minX, int maxX){
        for (int i = minX; i < maxX; i+=(3* Block.SIZE)) {
            Random random = new Random(); //TODO add seed
            if (random.nextDouble() < 0.2){
                createTree(i);
            }
        }
    }

    private void createTree(int column){
        RectangleRenderable rectangleRenderable =new RectangleRenderable(TRUNK_COLOR);


        Random random = new Random(); // TODO ADD SEED

        float heightOfCurrentColumn = callback.apply((float)column);
        heightOfCurrentColumn = (int) (Math.floor(heightOfCurrentColumn/Block.SIZE) * Block.SIZE);
        int randomHeight = (random.nextInt(5) + 5) * Block.SIZE;

        float y = (int) (Math.floor(windowDimensions.y()/Block.SIZE) * Block.SIZE);
        for (int j = (int) (y - (randomHeight + heightOfCurrentColumn)) ;
             j < (int) (y - heightOfCurrentColumn);
             j+=Block.SIZE) {
            Trunk trunk = new Trunk(new Vector2(column, j), rectangleRenderable);
            trunk.setTag("trunk");
            gameObjects.addGameObject(trunk);
         //TODO: add random above


        }
    }
}
