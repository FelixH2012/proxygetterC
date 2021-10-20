package me.felix.proxygetter.gui;

import me.felix.proxygetter.interfaces.WindowConfigurer;

import javax.swing.*;
import java.awt.*;

public class DefaultWindowConfigurer implements WindowConfigurer {


    private Font font = new Font("arial", Font.PLAIN, 20);

    public void configure(JFrame window) {
        window.setFont(font);
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
