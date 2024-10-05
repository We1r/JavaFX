package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordSwitcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordSwitcher::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Переключатель слов");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        JTextField textFieldLeft = new JTextField(15);
        JTextField textFieldRight = new JTextField(15);

        Dimension textFieldSize = new Dimension(200, 30);
        textFieldLeft.setPreferredSize(textFieldSize);
        textFieldRight.setPreferredSize(textFieldSize);

        JButton button = new JButton("←");
        button.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.add(textFieldLeft);
        textPanel.add(button);
        textPanel.add(textFieldRight);

        button.addActionListener(new ActionListener() {
            private boolean isRight = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRight) {
                    textFieldRight.setText(textFieldLeft.getText());
                    textFieldLeft.setText("");
                    button.setText("→");
                } else {
                    textFieldLeft.setText(textFieldRight.getText());
                    textFieldRight.setText("");
                    button.setText("←");
                }
                isRight = !isRight;
            }
        });
        panel.add(Box.createVerticalGlue());
        panel.add(textPanel);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }
}