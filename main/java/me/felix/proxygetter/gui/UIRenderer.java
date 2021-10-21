package me.felix.proxygetter.gui;

import me.felix.proxygetter.Project;
import me.felix.proxygetter.TextUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class UIRenderer extends JFrame {

    JButton button = new JButton();
    JButton infoButton = new JButton();
    public int width, height;

    public static boolean oldNotVisble;

    public String proxyScrape;

    public static String outputString = "";
    public static int listenTicks = 0;

    public static boolean gotOutput = false;
    static JComboBox<String> c1;

    public JTextArea textField = new JTextArea();
    public JLabel output = new JLabel();
    public JScrollPane scroll = new JScrollPane(textField);

    public enum SELECTED_TYPE {
        SOCKS4,
        SOCK5,
        HTTPS
    }

    public SELECTED_TYPE type;

    public UIRenderer() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        revalidate();
        setTitle("Proxygetter coded by Felix1337 v" + Project.VERSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        pack();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        width = 500;
        height = 500;
        setSize(width, height);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void revalidate() {
        listenButtons();
        render();
        super.revalidate();
    }

    public void render() {
        button.setFont(new Font("arial", Font.PLAIN, 12));
        button.setBounds(122, 22, 220, 30);
        button.setText("Get proxys from proxyscrape");
        add(button);
        getContentPane().add(button);

        infoButton.setFont(new Font("arial", Font.BOLD, 12));
        infoButton.setBounds(22, 22, 90, 30);
        infoButton.setText("about");
        add(infoButton);
        getContentPane().add(infoButton);




        add(output);
        textField.setEditable(false);

        String[] s1 = {"Socks4", "Socks5", "Http"};

        c1 = new JComboBox<>(s1);
        c1.setBounds(347, 22, 120, 30);
        assert outputString != null;
        if (!outputString.isEmpty()) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < 50; ++i)
                str.append(outputString).append("\n");
            textField.setText(str.toString());

            scroll.setBounds(10, 81, 455, 249);                     // <-- THIS

            getContentPane().add(scroll);
            setLocationRelativeTo(null);

            scroll.setVisible(true);
        }
        JLabel follow = new JLabel();
        c1.addActionListener(e -> {
            scroll.repaint();
            proxyScrape = "";
            String data = c1.getItemAt(c1.getSelectedIndex());
            switch (data) {
                case "Socks3" -> type = SELECTED_TYPE.SOCK5;
                case "Socks4" -> type = SELECTED_TYPE.SOCKS4;
                case "Http" -> type = SELECTED_TYPE.HTTPS;
            }
        });

        if (type != null) {
            switch (type) {
                case HTTPS -> proxyScrape = "https://api.proxyscrape.com/v2/?request=displayproxies&protocol=http&timeout=10000&country=all&ssl=all&anonymity=all";
                case SOCK5 -> proxyScrape = "https://api.proxyscrape.com/v2/?request=displayproxies&protocol=socks5&timeout=10000&country=all&ssl=all&anonymity=all";
                case SOCKS4 -> proxyScrape = "https://api.proxyscrape.com/v2/?request=displayproxies&protocol=socks4&timeout=10000&country=all&ssl=all&anonymity=all";
            }
        } else {
            proxyScrape = "https://api.proxyscrape.com/v2/?request=displayproxies&protocol=http&timeout=10000&country=all&ssl=all&anonymity=all";
        }
        add(follow);

        add(c1);
        getContentPane().add(c1);

    }


    public void listenButtons() {

        button.addActionListener(e -> {
            try {
                revalidate();
                if (!TextUtil.getText(proxyScrape).isEmpty()) {
                    gotOutput = true;
                }
                listenTicks = 1;
                outputString = TextUtil.getText(proxyScrape);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            infoButton.addActionListener(infoButton -> {
                JFrame InfoRenderer = new InformationRenderer();
                InfoRenderer.setVisible(true);
            });

        });
    }


}
