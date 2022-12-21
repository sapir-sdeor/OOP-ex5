package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.Transition;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.world.Block;
import pepse.world.Sky;
import pepse.world.Terrain;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.Tree;

import java.awt.*;
import java.util.function.Consumer;

public class PepseGameManager extends GameManager {

   // private GameObjectCollection gameObjects;


    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader, UserInputListener inputListener,
                               WindowController windowController){
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Vector2 windowDimensions = windowController.getWindowDimensions();
        Sky.create(gameObjects(), windowDimensions, Layer.BACKGROUND);
        Terrain terrain = new Terrain(gameObjects(), Layer.STATIC_OBJECTS, windowDimensions, 127);
        terrain.createInRange(0, (int)(windowDimensions.x()));
        Night.create(gameObjects(), Layer.BACKGROUND, windowDimensions, 30);
        GameObject sun = Sun.create(gameObjects(), Layer.BACKGROUND, windowDimensions, 30);
        SunHalo.create(gameObjects(), Layer.BACKGROUND+1, sun, new Color(255,255,0,20));
        Tree tree = new Tree(terrain::groundHeightAt, windowDimensions, gameObjects());
        tree.createInRange(0, (int)(windowDimensions.x()));
    }

    public static void main(String[] args) {
        new PepseGameManager().run();
    }
}
