package org.example;

import javax.swing.*;
import java.awt.*;

public class TextFlag {
    private static final String[] colors = {"Красный", "Зелёный", "Белый", "Синий", "Жёлтый"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextFlag::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JPanel topPanel = createColorSelectionPanel("Верхняя полоса");
        JPanel middlePanel = createColorSelectionPanel("Средняя полоса");
        JPanel bottomPanel = createColorSelectionPanel("Верхняя полоса");

        panel.add(topPanel);
        panel.add(middlePanel);
        panel.add(bottomPanel);

        JButton drawButton = new JButton("Нарисовать");
        drawButton.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(drawButton);

        frame.add(panel);
        frame.setVisible(true);

        drawButton.addActionListener(e -> {
            String topColor = getSelectedColor(topPanel);
            String middleColor = getSelectedColor(middlePanel);
            String bottomColor = getSelectedColor(bottomPanel);

            JOptionPane.showMessageDialog(frame, topColor + ", " + middleColor + ", " + bottomColor);
        });
    }

    private static JPanel createColorSelectionPanel(String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel(labelText + ":");
        panel.add(label);

        ButtonGroup group = new ButtonGroup();

        for (String color : colors) {
            JRadioButton radioButton = new JRadioButton(color);
            group.add(radioButton);
            panel.add(radioButton);

            if (color.equals(colors[0])) {
                radioButton.setSelected(true);
            }
        }

        return panel;
    }

    private static String getSelectedColor(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JRadioButton radioButton) {
                if (radioButton.isSelected()) {
                    return radioButton.getText();
                }
            }
        }

        return "";
    }
}
