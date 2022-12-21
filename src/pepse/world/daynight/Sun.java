package pepse.world.daynight;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import java.awt.*;

public class Sun {
    private static final Vector2 SUN_VECTOR_SIZE = new Vector2(100,100);
    private static final Float FINAL_POSITION = (float) Math.toRadians(-30);
    private static final Float INITIAL_POSITION = (float) Math.toRadians(330);
    public static GameObject create(GameObjectCollection gameObjects,
                                    int layer, Vector2 windowDimensions, float cycleLength){
        Vector2 sunPos = new Vector2(windowDimensions.x()*(float) 3/4,
                windowDimensions.y()*(float) 3/4);
        GameObject sun = new GameObject(sunPos,SUN_VECTOR_SIZE , new OvalRenderable(Color.YELLOW));
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects.addGameObject(sun, layer);
        new Transition<Float>(sun,(x)->sun.setCenter(new Vector2(
                ((float) Math.cos(x)*450f + windowDimensions.x()/2),
                ((float)Math.sin(x)*300f + windowDimensions.y()* (float)3/4))),INITIAL_POSITION ,
                FINAL_POSITION, Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength, Transition.TransitionType.TRANSITION_LOOP,
                null);
        return sun;
    }
}
