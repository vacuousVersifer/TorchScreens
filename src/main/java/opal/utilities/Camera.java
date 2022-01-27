package opal.utilities;

import opal.Window;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;

    public Camera(Vector2f position) {
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection() {
        projectionMatrix.identity();
        float left = 0f;
        float right = Window.getWidth();
        float bottom = 0f;
        float top = Window.getHeight();
        float near = 0f;
        float far = 100f;
        projectionMatrix.ortho(left, right, top, bottom, near, far);
//        System.out.println("Adjusting - " + right + " " + top);
    }

    public Matrix4f getViewMatrix() {
        adjustProjection();
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0f, 1f, 0f);

        /*
                   Red
            Blue        Green
                 Purple
         */
        this.viewMatrix.identity();

        Vector3f eye = new Vector3f(position.x, position.y, 20.0f);
        Vector3f center = cameraFront.add(position.x, position.y, 0.0f);

        this.viewMatrix = viewMatrix.lookAt(eye, center, cameraUp);

        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public Vector2f getPosition() {
        return this.position;
    }
}










