package opal;

import opal.storyGetting.Story;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import opal.scenes.Scene;
import opal.scenes.StoryScene;
import opal.scenes.TitleScene;
import opal.utilities.Time;

import java.util.ArrayList;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final String title;
    private long glfwWindow;

    private final Vector4f color;
    private static Window window = null;
    private static Scene currentScene = null;

    private static int WIDTH = 1000;
    private static int HEIGHT = 562;

    private Window() {
        this.title = "The Torch";

        color = new Vector4f(0.18f, 0.18f, 0.18f, 1.0f);
    }

    private static int currentStory = 0;
    public static void changeScene(int newScene, ArrayList<Story> stories) {
        switch (newScene) {
            case 0:
                currentScene = new TitleScene();
                currentScene.init();
                currentScene.start();
                break;
            case 1:
                if(stories != null) {
                    currentScene = new StoryScene(stories.get(currentStory));
                    currentScene.init();
                    currentScene.start();
                    currentStory++;
                    if (currentStory > 4) {
                        currentStory = 0;
                    }
                } else {
                    throw new Error("Must give stories if running story scene");
                }
                break;
            default:
                assert false : "Unknown scene '" + newScene + "'";
                break;
        }
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run(ArrayList<Story> stories) {
        System.out.println("--- Beginning Window ---");

        init();
        loop(stories);

        // Free Memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW / Free Error Callback
        glfwTerminate();
        Objects.requireNonNull(Objects.requireNonNull(glfwSetErrorCallback(null))).free();
    }

    public void init() {
        System.out.println("- Init Window -");

        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Init GLFW
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to init GLFW");
        }

        // Config GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        // Create the window
        glfwWindow = glfwCreateWindow(WIDTH, HEIGHT, this.title, NULL, NULL);

        if(glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window. ");
        }

        glfwSetWindowSizeCallback(glfwWindow, (w, newWidth, newHeight) -> {
            Window.setWidth(newWidth);
            Window.setHeight(newHeight);

            glfwSetWindowSize(glfwWindow, newWidth, newHeight);
        });

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);


        // Make the window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

        Window.changeScene(0, null);
    }

    private int counter = 0;
    public void loop(ArrayList<Story> stories) {
        System.out.println("- Looping Window -");

        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while(!glfwWindowShouldClose(glfwWindow)) {
            counter++;
            if(counter > 75) {
                counter = 0;

                Window.changeScene(1, stories);
            }


            // Poll events
            glfwPollEvents();

            float r = color.x;
            float g = color.y;
            float b = color.z;
            float a = color.w;
            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if(dt >= 0) {
                currentScene.update(dt);
            }

            glfwSwapBuffers(glfwWindow);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    public static Scene getScene() {
        get();
        return currentScene;
    }

    public static int getWidth() {
        get();
        return WIDTH;
    }

    public static int getHeight() {
        get();
        return HEIGHT;
    }

    public static void setWidth(int newWidth) {
        get();
        WIDTH = newWidth;
    }

    public static void setHeight(int newHeight) {
        get();
        HEIGHT = newHeight;
    }
}
