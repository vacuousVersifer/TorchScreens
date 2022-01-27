package opal.scenes;

import opal.Window;
import opal.components.Picture;
import opal.components.PictureRenderer;
import opal.storyGetting.Story;
import opal.utilities.AssetPool;
import opal.utilities.Camera;
import opal.utilities.Thing;
import opal.utilities.Transform;
import org.joml.Vector2f;

public class StoryScene extends Scene {
    private final Story story;

    public StoryScene(Story story) {
        this.story = story;
    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(0, 0));

        int width = 100;
        int height = 100;
        int wOff = Window.getWidth() - width;
        int hOff = Window.getHeight() - height;

        Vector2f size = new Vector2f(width, height);

        Thing w1 = new Thing(new Transform(new Vector2f(0, 0), new Vector2f(Window.getWidth(), 100)), 1);
        w1.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        this.addGameObjectToScene(w1);

        Thing w2 = new Thing(new Transform(new Vector2f(wOff, 0), size), 1);
        w2.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        this.addGameObjectToScene(w2);

        Thing w3 = new Thing(new Transform(new Vector2f(0, hOff), size), 1);
        w3.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        this.addGameObjectToScene(w3);

        Thing w4 = new Thing(new Transform(new Vector2f(wOff, hOff), size), 1);
        w4.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        this.addGameObjectToScene(w4);
    }

    @Override
    public void update(float dt) {
        for(Thing go : this.things) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
