package me.felix.proxygetter;

import com.formdev.flatlaf.FlatLightLaf;
import me.felix.proxygetter.gui.WindowRenderer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        WindowRenderer windowRenderer;
        Project project = new Project();
        project.initProject();
        FlatLightLaf.setup();
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        windowRenderer = new WindowRenderer();
        windowRenderer.render();
    }

}
