package gui;

import resources.Loader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marko on 08-Apr-15.
 */
public class Author extends JFrame {
    public Author() throws HeadlessException {
        super("About author");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setSize(275, 250);
        setLayout(null);

        JLabel faculty = new JLabel("Racunarski fakultet");
        faculty.setFont(new Font("Times New Roman", Font.BOLD, 20));
        faculty.setBounds(20, 10, 200, 30);
        add(faculty);

        ImageIcon author = Loader.getInstance().authorImage();
        JLabel picture = new JLabel(author);
        picture.setBounds(150, 60, author.getIconWidth(), author.getIconHeight());
        add(picture);

        JLabel name = new JLabel("Marko Bakovic");
        name.setBounds(20, 70, 130, 20);
        add(name);

        JLabel index = new JLabel("RN 12/13");
        index.setBounds(20, 100, 130, 20);
        add(index);

        JLabel email = new JLabel("mbakovic13@raf.edu.rs");
        email.setBounds(20, 130, 130, 20);
        add(email);

        setVisible(true);
    }
}
