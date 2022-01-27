package opal.rendering;

import opal.components.PictureRenderer;
import opal.utilities.Thing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private final List<RenderBatch> batches;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(Thing go) {
        PictureRenderer spr = go.getComponent(PictureRenderer.class);
        if(spr != null) {
            add(spr);
        }
    }

    public void add(PictureRenderer sprite) {
        boolean added = false;
        for(RenderBatch batch : batches) {
            if(batch.hasRoom() && batch.getzIndex() == sprite.getGameObject().getzIndex()) {
                Texture tex = sprite.getTexture();
                if(tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
                    batch.addSprite(sprite);
                    added = true;
                    break;
                }
            }
        }

        if(!added) {
          RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.getGameObject().getzIndex());
          newBatch.start();
          batches.add(newBatch);
          newBatch.addSprite(sprite);
          Collections.sort(batches);
        }
    }

    public void render() {
        for(RenderBatch batch : batches) {
            batch.render();
        }
    }
}













