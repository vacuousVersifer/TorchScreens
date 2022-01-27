package opal.scenes;

import opal.utilities.Thing;

public class TitleScene extends Scene {
    public TitleScene() {}

    @Override
    public void init() {
//

//        SpriteSheet sprites = AssetPool.getSpriteSheet("assets/images/spritesheets/marioAndGoomba.png");
//
//        Thing obj1 = new Thing(new Transform(new Vector2f(200, 400), new Vector2f(125, 125)), 2);
//        obj1.addComponent(new PictureRenderer(
//                sprites.getSprite(0) //new Vector4f(1, 0, 0, 1)
//        ));
//        this.addGameObjectToScene(obj1);
//
//        Thing obj2 = new Thing(new Transform(new Vector2f(300, 400), new Vector2f(125, 125)), 1);
//        obj2.addComponent(new PictureRenderer(
//                sprites.getSprite(15)
//        ));
//        this.addGameObjectToScene(obj2);
    }

    @Override
    public void update(float dt) {
        for(Thing go : this.things) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
