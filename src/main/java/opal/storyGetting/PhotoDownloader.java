package opal.storyGetting;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PhotoDownloader {
    public static String download(String url, String destination) throws IOException {
        System.out.println("- Downloading Photo -");
        InputStream in = new URL(url).openStream();
        Path trueDestination = Paths.get(destination);
        boolean exists = new File(String.valueOf(trueDestination)).exists();

        if(exists) {
            System.out.println("Image already downloaded");
        } else {
            System.out.println("Image downloaded");
            Files.copy(in, trueDestination);
        }

        return trueDestination.toString();
    }
}
