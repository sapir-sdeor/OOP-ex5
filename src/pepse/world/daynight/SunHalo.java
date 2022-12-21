package pepse.world.daynight;


import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import java.awt.*;

public class SunHalo {
    private static final Vector2 HALO_VECTOR_SIZE = new Vector2(300,300);
    public static GameObject create(GameObjectCollection gameObjects,
                                    int layer, GameObject sun, Color color){
        GameObject sunHalo = new GameObject(Vector2.ZERO,
                HALO_VECTOR_SIZE, new OvalRenderable(color));
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag("sunHalo");
        gameObjects.addGameObject(sunHalo, layer);
        sunHalo.addComponent((x)->sunHalo.setCenter(sun.getCenter()));
        return sun;

    }
}
