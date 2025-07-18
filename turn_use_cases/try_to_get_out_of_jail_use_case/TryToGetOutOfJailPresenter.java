/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.try_to_get_out_of_jail_use_case;

 import game_entities.Player;
import turn_interface_adapters.TurnController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TryToGetOutOfJailPresenter implements TryToGetOutOfJailOutputBoundary{
   
    private final JPanel actionDialogBox;
    private final JPanel playerOptionsWindow;
    private final TurnController turnController;
    private final JPanel jailRollWindow;
    
    public TryToGetOutOfJailPresenter(JPanel actionDialogBox, TurnController turnController){
        this.actionDialogBox = actionDialogBox;
        this.playerOptionsWindow = new JPanel();
        this.turnController = turnController;
        this.jailRollWindow = new JPanel();
        this.actionDialogBox.add(jailRollWindow, "Jail Roll");
        actionDialogBox.add(playerOptionsWindow, "Player Jail Options");
    }
    
    @Override
    public void showOptions(Player player, ArrayList<String> options){
        playerOptionsWindow.removeAll();
        playerOptionsWindow.setLayout(new GridLayout(options.size(), 1));
        
        for(String option : options){
            JButton optionButton  = new JButton(option);
            optionButton.addActionListener(e -> {
                
                //Turn controller will handle the option and call TryToGetOutOfJailUseCase
                turnController.leaveJailWithChoice(option);
            });
            playerOptionsWindow.add(optionButton);
        }
        actionDialogBox.revalidate();
        actionDialogBox.repaint();
        CardLayout cl = (CardLayout) actionDialogBox.getLayout();
        cl.show(actionDialogBox, "Player Jail Options");
    }
    
    @Override
    public void showRoll(int[] playerRollAmount){
        System.out.println("asd");
        jailRollWindow.removeAll();
        JLabel rollLabel = new JLabel("You rolled a " + playerRollAmount[0] + " and a " + playerRollAmount[1]);
        jailRollWindow.add(rollLabel);
        JButton rollButton = new JButton("End Turn");
        rollButton.addActionListener(e -> turnController.endTurn());
        jailRollWindow.add(rollLabel);
        actionDialogBox.revalidate();
        actionDialogBox.repaint();
        CardLayout cl = (CardLayout) actionDialogBox.getLayout();
        cl.show(actionDialogBox, "Jail Roll");
    }
}
