package opal.storyGetting;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Parser {
    private final String baseUrl = "https://shsthetorch.com/";
    private final String charset = UTF_8.name();

    private final ArrayList<Story> stories = new ArrayList<>();

    public Parser() {}

    public ArrayList<Story> run(String input) throws IOException {
        System.out.println("--- Beginning Parser ---");

        Document doc = Jsoup.parse(input, "https://shsthetorch.com/");

        Elements articles = doc.select("article");
        for(int i = 0; i < 5; i++) {
            Element article = articles.get(i);
            Element post = article.child(0).child(0);
            Element caption = post.child(1);

            String id = article.id().substring(5);
            String imgUrl = post.child(0).child(0).child(0).child(0).child(0).attr("src");
            String title = caption.child(0).child(0).ownText();
            String author = caption.child(1).child(0).child(1).child(0).ownText();

            if(author.equals("adviser")) { author = "Mr. Welch"; }

            System.out.println("- Article #" + i + " -");
            System.out.println("ID - " + id);
            System.out.println("Title - " + title);
            System.out.println("Author - " + author);
            System.out.println("Feature Image URL - " + imgUrl);
            System.out.println("Slide ID - " + i);

            String imageLocation = PhotoDownloader.download(imgUrl, "assets/downloads/" + id + ".png");

            Story story = new Story(title, author, imageLocation, id, i);
            stories.add(story);


        }

        return stories;
    }
}
