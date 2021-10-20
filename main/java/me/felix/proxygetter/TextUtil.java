package me.felix.proxygetter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class TextUtil {

    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        String pageText;
        URLConnection conn = website.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            pageText = reader.lines().collect(Collectors.joining("\n"));
        }
        return pageText;
    }

}
