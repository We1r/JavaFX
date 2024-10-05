package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::createAndShowGUI);
    }

    private static JTextField display;
    private static double currentResult;
    private static String currentOperator = "";
    private static boolean newNumber = true;

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5,5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            buttonPanel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    static class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789.".contains(command)) {
                if (newNumber) {
                    display.setText(command.equals(".") ? "0." : command);
                    newNumber = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if ("+-*/".contains(command)) {
                executeCurrentOperation();
                currentOperator = command;
                newNumber = true;
            } else if (command.equals("=")) {
                executeCurrentOperation();
                currentOperator = "";
                newNumber = true;
            } else if (command.equals("C")) {
                clear();
            }
        }
    }

    private static void executeCurrentOperation() {
        try {
            double displayedNumber = Double.parseDouble(display.getText());

            switch (currentOperator) {
                case "+":
                    currentResult += displayedNumber;
                    break;
                case "-":
                    currentResult -= displayedNumber;
                    break;
                case "*":
                    currentResult *= displayedNumber;
                    break;
                case "/":
                    if (displayedNumber == 0) {
                        JOptionPane.showMessageDialog(null, "Ошибка: Деление на 0!");
                        return;
                    }
                    currentResult /= displayedNumber;
                    break;
                default:
                    currentResult = displayedNumber;
                    break;
            }

            display.setText(String.valueOf(currentResult));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка: Некорректный ввод!");
        }
    }

    private static void clear() {
        currentResult = 0;
        currentOperator = "";
        display.setText("0");
        newNumber = true;
    }
}
