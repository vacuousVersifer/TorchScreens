package opal.components;

import org.joml.Vector2f;
import opal.rendering.Texture;

public class Picture {
    private Texture texture;
    private Vector2f[] texCoords;

    public Picture(Texture texture) {
        this.texture = texture;
        this.texCoords = new Vector2f[]{
                new Vector2f(1, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 1)
        };
    }

    public Picture(Texture texture, Vector2f[] texCoords) {
        this.texture = texture;
        this.texCoords = texCoords;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTexCoords() {
        return this.texCoords;
    }
}
