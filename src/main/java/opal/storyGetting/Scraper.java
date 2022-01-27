package opal.storyGetting;

import opal.utilities.Time;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Scraper {
    public String run() throws IOException {
        System.out.println("--- Beginning Scraper ---");

        int year = Time.getYear();
        int month = Time.getMonth();

        String url = "https://shsthetorch.com/date/" + year + "/" + month + "/";

        System.out.println("- Getting Website -");
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", UTF_8.name());

        InputStream response = connection.getInputStream();

        Scanner scanner = new Scanner(response);

        return scanner.useDelimiter("\\A").next();
    }
}
