package me.felix.proxygetter.gui;

import me.felix.proxygetter.Project;
import me.felix.proxygetter.TextUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class UIRenderer extends JFrame {

    JButton button = new JButton();

    public int width, height;

    public String proxyScrape = "https://api.proxyscrape.com/v2/?request=displayproxies&protocol=http&timeout=10000&country=all&ssl=all&anonymity=all";

    public static boolean gotOutput = false;

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
        listenButtons();

    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void revalidate() {
        render();
        super.revalidate();
    }

    public void render() {
        Dimension size = button.getPreferredSize();
        final JLabel jLabel = new JLabel("Proxygetter coded by Felix1337.");
        jLabel.setFont(new Font("arial", Font.BOLD, 22));

        jLabel.setBounds(250 / 2 - size.width / 2, 20 / 2 - size.height, 422, 40);

        add(jLabel);
        getContentPane().add(jLabel);

        button.setFont(new Font("arial", Font.PLAIN, 12));
        button.setBounds(350 / 2 - size.width / 2, 100 / 2 - size.height / 2, 220, 30);
        button.setText("Get proxys from proxyscrape");
        add(button);
        System.out.println(gotOutput);
        getContentPane().add(button);
        JLabel output = new JLabel();
        add(output);
        if (gotOutput) {
            output.setText("Proxys copied to clipboard!");
            output.setBounds(817 / 2 - size.width / 2, 35, 422, 40);
            output.setFont(new Font("arial", Font.BOLD, 12));
            getContentPane().add(output);
            output.setVisible(true);
        }
    }

    public void listenButtons() {
        button.addActionListener(e -> {
            try {
                revalidate();
                if (!TextUtil.getText(proxyScrape).isEmpty()) {
                    gotOutput = true;
                }
                StringSelection selection = new StringSelection(TextUtil.getText(proxyScrape));
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
