package resources;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.net.URL;

/**
 * Created by Marko on 01-Apr-15.
 *
 * Simple singleton class for loading resources.
 */
public class Loader {
    private static Loader ourInstance = new Loader();

    public static Loader getInstance() {
        return ourInstance;
    }

    private Loader() {
    }

    public Icon loadIcon(String name) {
        URL imageURL = getClass().getResource("/resources/icons/" + name + ".png");
        if (imageURL != null) return new ImageIcon(imageURL);
        else {
            System.out.println("Icon not found: " + name + ".png");
            return null;
        }
    }

    public ImageIcon authorImage() {
        URL imageURL = getClass().getResource("/resources/pictures/author.jpg");
        if (imageURL != null) return new ImageIcon(imageURL);
        else {
            System.out.println("Author picture not found");
            return null;
        }
    }
}
