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
import org.joml.Vector4f;

public class StoryScene extends Scene {
    private final Story story;
    private Thing w1, w2, w3, w4;


    public StoryScene(Story story) {
        this.story = story;
    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(0, 0));



        /*
                   Red
            Blue        Green
                 Purple
         */

        int width = 25;
        int height = 25;
        int winW = Window.getWidth() - width;
        int winH = Window.getHeight() - height;
        int boxW = winW - width;
        int boxH = winH - height;


        w1 = new Thing(new Transform(new Vector2f(width, 0), new Vector2f(boxW, height)), 1);
//        w1.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        w1.addComponent(new PictureRenderer(new Vector4f(1, 0, 0, 0)));
        this.addGameObjectToScene(w1);

        w2 = new Thing(new Transform(new Vector2f(winW, height), new Vector2f(width, boxH)), 1);
//        w2.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        w2.addComponent(new PictureRenderer(new Vector4f(0, 1, 0, 0)));
        this.addGameObjectToScene(w2);

        w3 = new Thing(new Transform(new Vector2f(0, height), new Vector2f(width, boxH)), 1);
//        w3.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        w3.addComponent(new PictureRenderer(new Vector4f(0, 0, 1, 0)));
        this.addGameObjectToScene(w3);

        w4 = new Thing(new Transform(new Vector2f(width, winH), new Vector2f(boxW, height)), 1);
//        w4.addComponent(new PictureRenderer(new Picture(AssetPool.getTexture(story.getImageLocation()))));
        w4.addComponent(new PictureRenderer(new Vector4f(1, 0, 1, 0)));
        this.addGameObjectToScene(w4);
    }


    int prevWidth = Window.getWidth();
    int prevHeight = Window.getHeight();

    @Override
    public void update(float dt) {
        for(Thing go : this.things) {
            go.update(dt);
        }

        if(prevWidth != Window.getWidth() || prevHeight != Window.getHeight()) {
            removeGameObjectFromScene(w1);
            removeGameObjectFromScene(w2);
            removeGameObjectFromScene(w3);
            removeGameObjectFromScene(w4);
            System.out.println("Resetting objects");
//            int width = 25;
//            int height = 25;
//            int winW = Window.getWidth() - width;
//            int winH = Window.getHeight() - height;
//            int boxW = winW - width;
//            int boxH = winH - height;
//            w1 = new Thing(new Transform(new Vector2f(width, 0), new Vector2f(boxW, height)), 1);
//            w1.addComponent(new PictureRenderer(new Vector4f(1, 0, 0, 0)));
//            this.addGameObjectToScene(w1);
//
//            w2 = new Thing(new Transform(new Vector2f(winW, height), new Vector2f(width, boxH)), 1);
//            w2.addComponent(new PictureRenderer(new Vector4f(0, 1, 0, 0)));
//            this.addGameObjectToScene(w2);
//
//            w3 = new Thing(new Transform(new Vector2f(0, height), new Vector2f(width, boxH)), 1);
//            w3.addComponent(new PictureRenderer(new Vector4f(0, 0, 1, 0)));
//            this.addGameObjectToScene(w3);
//
//            w4 = new Thing(new Transform(new Vector2f(width, winH), new Vector2f(boxW, height)), 1);
//            w4.addComponent(new PictureRenderer(new Vector4f(1, 0, 1, 0)));
//            this.addGameObjectToScene(w4);
        }

        this.renderer.render();
    }
}
