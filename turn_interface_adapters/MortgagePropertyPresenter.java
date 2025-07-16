/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_interface_adapters;

import game_entities.Player;
import game_entities.tiles.Property;
import turn_use_cases.mortgage_use_case.MortgagePropertyOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MortgagePropertyPresenter implements MortgagePropertyOutputBoundary{
    private final JPanel actionDialogBoxes;
    private final JPanel mortgagePanel;
    private final TurnController controller;
    
    public MortgagePropertyPresenter(JPanel actionDialogBoxes, CardLayout cardLayout, TurnController controller){
        this.controller = controller;
        this.actionDialogBoxes = actionDialogBoxes;
        
        this.mortgagePanel = new JPanel();
        actionDialogBoxes.add(mortgagePanel, "Mortgage Panel");
    }
    
    //presents the properties which can be mortgaged
    @Override
    public void showMortgagePropertyList(ArrayList<Property> properties, String flavorText){
        resetOptionsPanel();
        if(properties.size() == 0){
            mortgagePanel.add(new JLabel("You cannot mortgage a property."));
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> controller.endUseCase());
            mortgagePanel.add(backButton);
            showOptionsPanel();
        }else{
            mortgagePanel.add(new JLabel(flavorText));
            JComboBox<String> comboBox = new JComboBox<>();
            for(Property property : properties){
                comboBox.addItem(property.getTileDisplayName());
            }
            mortgagePanel.add(comboBox);
            JButton pickButton  = new JButton("Pick");
            pickButton.addActionListener(e ->{
                for(Property property : properties){
                    if(property.getTileDisplayName().equals(comboBox.getSelectedItem())){
                        controller.mortgageProperty(property);
                    }
                }
            });
            mortgagePanel.add(pickButton);
            JButton backButton = new JButton("Cancel");
            backButton.addActionListener(e -> controller.endUseCase());
            backButton.addActionListener(e -> controller.endUseCase());
            mortgagePanel.add(backButton);
            showOptionsPanel();
        }
    }
    
    //Presents the properties which can be unmortgaged
    @Override
    public void showUnmortgagePropertyList(ArrayList<Property> properties, String flavorText){
        resetOptionsPanel();
        if(properties.size() == 0){
            mortgagePanel.add(new JLabel("You cannot unmortgaged a property."));
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> controller.endUseCase());
            mortgagePanel.add(backButton);
            showOptionsPanel();
        }else{
            mortgagePanel.add(new JLabel(flavorText));
            JComboBox<String> comboBox = new JComboBox<>();
            for(Property property : properties){
                comboBox.addItem(property.getTileDisplayName());
            }
            mortgagePanel.add(comboBox);
            JButton pickButton = new JButton("Pick");
            pickButton.addActionListener(e ->{ 
                for(Property property : properties){
                    if(property.getTileDisplayName().equals(comboBox.getSelectedItem())){
                        controller.unmortgageProperty(property);
                    }
                }
            });
            mortgagePanel.add(pickButton);
            JButton backButton = new JButton("Cancel");
            backButton.addActionListener(e -> controller.endUseCase());
            mortgagePanel.add(backButton);
            showOptionsPanel();
        }
    }
    
    //Presents the property which mortgaged by player
    @Override
    public void showMortgageProperty(Player player, Property propery, String flavorText){
        resetOptionsPanel();
        mortgagePanel.add(new JLabel(flavorText));
        JButton endButton = new JButton("End Mortgage");
        endButton.addActionListener(e -> controller.endUseCase());
        mortgagePanel.add(endButton);
        showOptionsPanel();
    }
    
    //Presents the property which is unmortaged by player
    @Override
    public void showUnmortgageProperty(Player player, Property property, String flavorText){
        resetOptionsPanel();
        mortgagePanel.add(new JLabel(flavorText));
        JButton endButton = new JButton("End Unmortgage");
        endButton.addActionListener(e -> controller.endUseCase());
        mortgagePanel.add(endButton);
        showOptionsPanel();
    }
    
    private void resetOptionsPanel(){
        mortgagePanel.removeAll();
        mortgagePanel.setLayout(new BoxLayout(mortgagePanel, BoxLayout.X_AXIS));
    }
    
    private void showOptionsPanel(){
        mortgagePanel.revalidate();
        mortgagePanel.repaint();
        CardLayout cl = (CardLayout) actionDialogBoxes.getLayout();
        cl.show(actionDialogBoxes, "Mortgage Panel");
    }
}
