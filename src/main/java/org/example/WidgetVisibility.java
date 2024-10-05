package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class WidgetVisibility {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WidgetVisibility::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Видимость виджета");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton button = new JButton("Кнопка");
        JCheckBox firstCheckBox = new JCheckBox("Показать кнопку", true);

        JTextField textField = new JTextField("Текстовое поле");
        JCheckBox secondCheckBox = new JCheckBox("Показать текстовое поле", true);

        JLabel label = new JLabel("Метка");
        JCheckBox thirdCheckBox = new JCheckBox("Показать метку", true);

        panel.add(firstCheckBox);
        panel.add(button);
        panel.add(secondCheckBox);
        panel.add(textField);
        panel.add(thirdCheckBox);
        panel.add(label);

        firstCheckBox.addItemListener(e -> button.setVisible(e.getStateChange() == ItemEvent.SELECTED));

        secondCheckBox.addItemListener(e -> textField.setVisible(e.getStateChange() == ItemEvent.SELECTED));

        thirdCheckBox.addItemListener(e -> label.setVisible(e.getStateChange() == ItemEvent.SELECTED));
        frame.add(panel);
        frame.setVisible(true);
    }
}
