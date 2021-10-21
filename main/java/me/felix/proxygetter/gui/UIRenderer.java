package me.felix.proxygetter.gui;

import me.felix.proxygetter.Project;
import me.felix.proxygetter.TextUtil;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.net.URL;
import java.util.Objects;

public class UIRenderer extends JFrame {

    JButton button = new JButton();

    public int width, height;

    public String proxyScrape;

    public static String outputString = "";
    public static int listenTicks = 0;

    public static boolean gotOutput = false;
    static JComboBox c1;

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
        Dimension size = button.getPreferredSize();

        button.setFont(new Font("arial", Font.PLAIN, 12));
        button.setBounds(122, 22, 220, 30);
        button.setText("Get proxys from proxyscrape");
        add(button);
        getContentPane().add(button);
        JLabel output = new JLabel();
        add(output);
        JTextArea textField = new JTextArea();
        textField.setEditable(false);


        String s1[] = {"Socks4", "Socks5", "Http"};

        c1 = new JComboBox(s1);

        c1.setBounds(347, 22, 120, 30);


        c1.addActionListener(e -> {
            proxyScrape = "";
            String data = (String) c1.getItemAt(c1.getSelectedIndex());
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

        assert outputString != null;
        if (!outputString.isEmpty()) {
            String str = "";
            for (int i = 0; i < 50; ++i)
                str += outputString + "\n";
            textField.setText(str);

            JScrollPane scroll = new JScrollPane(textField);
            scroll.setBounds(10, 81, 455, 249);                     // <-- THIS

            getContentPane().add(scroll);
            setLocationRelativeTo(null);

            scroll.setVisible(true);
        }
        JLabel follow = new JLabel();
        add(follow);
        if (gotOutput) {
            output.setText("Proxys copied to clipboard!");
            output.setBounds(4, -10, 422, 40);
            output.setFont(new Font("arial", Font.PLAIN, 10));
            getContentPane().add(output);
            output.setVisible(true);

            follow.setText("Coded by felix1337");
            follow.setBounds(9, 16, 422, 40);
            follow.setFont(new Font("arial", Font.PLAIN, 10));
            getContentPane().add(follow);
            follow.setVisible(true);

        }


            add(c1);
            getContentPane().add(c1);


    }


    public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if (e.getSource() == c1) {

            if ("Socks4".equals(Objects.requireNonNull(c1.getSelectedItem()))) {
                type = SELECTED_TYPE.SOCKS4;
            }
            if ("Socks3".equals(Objects.requireNonNull(c1.getSelectedItem()))) {
                type = SELECTED_TYPE.SOCK5;
            }
            if ("http".equals(Objects.requireNonNull(c1.getSelectedItem()))) {
                type = SELECTED_TYPE.HTTPS;
            }
        }
    }

    public void listenButtons() {

        button.addActionListener(e -> {
            try {
                revalidate();
                if (!TextUtil.getText(proxyScrape).isEmpty()) {
                    gotOutput = true;
                }
                listenTicks = 1;
                StringSelection selection = new StringSelection(TextUtil.getText(proxyScrape));
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                outputString = TextUtil.getText(proxyScrape);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
