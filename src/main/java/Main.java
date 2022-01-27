import opal.storyGetting.Scraper;
import opal.Window;
import opal.storyGetting.Parser;
import opal.storyGetting.Story;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("----- BEGINNING PROGRAM -----");
        Scraper scraper = new Scraper();
        String website = scraper.run();

        Parser parser = new Parser();
        ArrayList<Story> stories = parser.run(website);

        Window window = Window.get();
        window.run(stories);
    }
}
