package opal.storyGetting;

import java.nio.file.Path;

public class Story {
    private final String title;
    private final String author;
    private final String imageLocation;
    private final String id;
    private final int slideNum;

    public Story(String title, String author, String imageLocation, String id, int slideNum) {
        this.title = title;
        this.author = author;
        this.imageLocation = imageLocation;
        this.id = id;
        this.slideNum = slideNum;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getId() {
        return id;
    }

    public int getSlideNum() {
        return slideNum;
    }
}
