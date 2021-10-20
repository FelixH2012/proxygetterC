package me.felix.proxygetter.gui;

import me.felix.proxygetter.interfaces.WindowConfigurer;

import javax.swing.*;

public class WindowRenderer {

    public void render() {
            UIRenderer something = new UIRenderer();
            something.invalidate();
            something.validate();
            something.repaint();
        }
}
