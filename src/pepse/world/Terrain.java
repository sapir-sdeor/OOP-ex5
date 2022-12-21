package pepse.world;

import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;

import java.awt.*;
import java.util.Random;

public class Terrain {
    private static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    private static final float MIN_GROUND_Y = 30;
    private static final float MAX_GROUND_Y = 400;

    public static final int BLOCK_SIZE = 30;
    private final Vector2 windowDimensions;
    private final int seed;
    private float groundHeightAtX0 =90;
    private GameObjectCollection gameObjects;
    private int groundLayer;
    public Terrain(GameObjectCollection gameObjects, int groundLayer, Vector2 windowDimensions, int seed){
        this.gameObjects = gameObjects;
        this.groundLayer = groundLayer;
        this.windowDimensions = windowDimensions;
        this.seed = seed;
    }

    public float groundHeightAt(float x) {
        Random random = new Random(seed);
        float sinHeight = (float)(((Math.cos(0.00001*Math.pow(x, 1.9)) + 2 * Math.sin(0.001*x)) )  * 2 *
                (random.nextDouble())) * groundHeightAtX0;
        if (sinHeight < MIN_GROUND_Y){
            return MIN_GROUND_Y;
        }
        return Math.min(sinHeight, MAX_GROUND_Y);
    }

    public void createInRange(int minX, int maxX){
        RectangleRenderable rectangleRenderable =new RectangleRenderable(ColorSupplier.approximateColor(BASE_GROUND_COLOR));
        Random random = new Random(seed); //TODO create a random object outside
        float lastHeight= groundHeightAtX0;
        for (int i =  minX; i < maxX; i+=Block.SIZE) {
//            if (lastHeight >= MAX_GROUND_Y){lastHeight -= BLOCK_SIZE;}
//            else if (lastHeight <= MIN_GROUND_Y){lastHeight += BLOCK_SIZE;}
//            else {
//                lastHeight += (random.nextInt(3) - 1) * BLOCK_SIZE;
//            }
            float y = (int) (Math.floor(windowDimensions.y()/Block.SIZE) * Block.SIZE);
            int currentHeight = (int) (Math.floor(groundHeightAt(i)/Block.SIZE) * Block.SIZE);
            for (int j = (int) (y - currentHeight) ; j <= (int) (y);
                 j+=Block.SIZE) {
                Block block = new Block(new Vector2(i, j), rectangleRenderable);
                block.setTag("ground");
                gameObjects.addGameObject(block);
            } //TODO: add random above
        }
    }


}
