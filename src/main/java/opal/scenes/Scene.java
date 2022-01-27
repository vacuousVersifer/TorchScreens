package opal.scenes;

import opal.utilities.Camera;
import opal.utilities.Thing;
import opal.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<Thing> things = new ArrayList<>();

    public Scene() {

    }

    public void init() {

    }

    public void start() {
        for(Thing go : things) {
            go.start();
            this.renderer.add(go);
        }
        isRunning = true;
    }

    public void addGameObjectToScene(Thing go) {
        things.add(go);

        if(isRunning) {
            go.start();
            this.renderer.add(go);
        }
    }

    public abstract void update(float dt);

    public Camera camera() {
        return this.camera;
    }
}
