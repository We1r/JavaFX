package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RestaurantOrder {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RestaurantOrder::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ресторанный Заказ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Map<String, Integer> menu = new HashMap<>();
        menu.put("Пирожки «Утопленники»", 300);
        menu.put("Салат «Тещин язык»", 450);
        menu.put("Блины «Грешники»", 200);
        menu.put("Торт «Трухлявый пень»", 500);

        Map<String, JSpinner> portionFields = new HashMap<>();
        Map<String, JCheckBox> checkBoxes = new HashMap<>();

        int row = 0;
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            String dishName = entry.getKey();
            int price = entry.getValue();

            JCheckBox checkBox = new JCheckBox(dishName + " - " + price + " руб.");
            checkBox.setPreferredSize(new Dimension(150, 50));
            checkBoxes.put(dishName, checkBox);

            gbc.gridx = 0;
            gbc.gridy = row;
            panel.add(checkBox, gbc);

            JSpinner portionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
            portionSpinner.setPreferredSize(new Dimension(50, 25));
            portionSpinner.setVisible(false);
            portionFields.put(dishName, portionSpinner);

            gbc.gridx = 1;
            panel.add(portionSpinner, gbc);

            checkBox.addActionListener(e -> {
                portionSpinner.setVisible(checkBox.isSelected());
                panel.revalidate();
                panel.repaint();
            });

            row++;
        }

        JButton orderButton = new JButton("Оформить заказ");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(orderButton, gbc);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        orderButton.addActionListener(e -> {
            StringBuilder receipt = new StringBuilder();
            int totalPrice = 0;

            for (String dish : menu.keySet()) {
                JCheckBox checkBox = checkBoxes.get(dish);
                JSpinner portionSpinner = portionFields.get(dish);

                if (checkBox != null && portionSpinner != null && checkBox.isSelected()) {
                    int price = menu.get(dish);
                    int quantity = (int) portionSpinner.getValue();
                    int dishTotalPrice = price * quantity;

                    receipt.append(dish)
                            .append(" - ")
                            .append(quantity)
                            .append(" порций, ")
                            .append("итог: ")
                            .append(dishTotalPrice)
                            .append(" руб.\n");

                    totalPrice += dishTotalPrice;
                }
            }

            if (totalPrice > 0) {
                receipt.append("\nОбщая стоимость заказа: ").append(totalPrice).append(" руб.");

                JOptionPane.showMessageDialog(frame, receipt.toString(), "Чек", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Вы не выбрали ни одного блюда.", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });
        frame.setVisible(true);
    }
}
