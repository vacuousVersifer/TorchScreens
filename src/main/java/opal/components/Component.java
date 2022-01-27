package opal.components;

import opal.utilities.Thing;

public abstract class Component {
    protected Thing thing = null;

    public void update(float dt) {

    };

    public void start() {

    }

    public Thing getGameObject() {
        return this.thing;
    }

    public void setGameObject(Thing thing) {
        this.thing = thing;
    }
}
