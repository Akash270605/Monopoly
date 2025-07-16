/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow {
    public static void showErrorWindow(String errorMessage){
        JPanel errorWindow = new JPanel();
        errorWindow.setLayout(new GridBagLayout());
        GridBagConstraints rowSpecifier = new GridBagConstraints();
        rowSpecifier.insets = new Insets(5, 5, 5, 5);
        rowSpecifier.gridy = 0;
        errorWindow.add(new JLabel("An error occured: " + errorMessage + ". Program will now exit."), rowSpecifier);
        JButton acknowledge = new JButton("ok");
        acknowledge.addActionListener(e -> System.exit(1));
        errorWindow.add(acknowledge, rowSpecifier);
        JFrame errorFrame = new JFrame();
        JDialog errorDialog = new JDialog(errorFrame, "Monopoly Game encountered an error", true);
        errorDialog.setContentPane(errorWindow);
        errorDialog.pack();
        errorDialog.setLocationRelativeTo(null);
        errorDialog.setVisible(true);
}
}
