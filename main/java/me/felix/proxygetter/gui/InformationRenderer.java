package me.felix.proxygetter.gui;

import javax.swing.*;
import java.awt.*;

public class InformationRenderer extends JFrame {

    private static int width, height;

    InformationRenderer() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Informations");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        pack();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        width = 500;
        height = 500;
        setSize(width, height);
        setVisible(true);
        setResizable(false);
        render();
    }

    public void render() {

        JLabel proxyScrape = new JLabel();
        proxyScrape.setFont(new Font("Arial", Font.BOLD, 20));
        proxyScrape.setText("Socks by proxyscrape");
        proxyScrape.setBounds(132, -5, 300, 30);
        add(proxyScrape);
        getContentPane().add(proxyScrape);
        proxyScrape.setVisible(true);


        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setText("Used design api: formdev.flatlaf");
        label.setBounds(100, 20, 300, 30);
        add(label);
        getContentPane().add(label);
        label.setVisible(true);

        JLabel label2 = new JLabel();
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        label2.setText("Developed by Felix1337");
        label2.setBounds(135, 45, 300, 30);
        add(label2);
        getContentPane().add(label2);
        label2.setVisible(true);

    }

}
