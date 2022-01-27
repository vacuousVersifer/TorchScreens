package opal.components;

import opal.utilities.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import opal.rendering.Texture;

public class PictureRenderer extends Component {
    private final Vector4f color;
    private Picture picture;
    private Transform lastTransform;
    private boolean dirty = false;

    public PictureRenderer(Vector4f color) {
        this.color = color;
        this.picture = new Picture(null);
        this.dirty = true;
    }

    public PictureRenderer(Picture picture) {
        this.picture = picture;
        this.color = new Vector4f(1, 1, 1, 1);
        this.dirty = true;
    }

    @Override
    public void start() {
        this.lastTransform = thing.getTransform().copy();
    }

    @Override
    public void update(float dt) {
        if(!this.lastTransform.equals(this.thing.getTransform())) {
            this.thing.getTransform().copy(this.lastTransform);
            this.dirty = true;
        }
    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return picture.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return picture.getTexCoords();
    }

    public void setColor(Vector4f color) {
        if(!this.color.equals(color)) {
            this.color.set(color);
            this.dirty = true;
        }
    }

    public void setSprite(Picture picture) {
        this.picture = picture;
        this.dirty = true;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public void setClean() {
        this.dirty = false;
    }
}
