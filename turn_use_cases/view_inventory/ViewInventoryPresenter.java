/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.view_inventory;

import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;
import game_entities.tiles.RailroadTile;
import game_entities.tiles.UtilityTile;
import turn_interface_adapters.TurnController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class ViewInventoryPresenter implements ViewInventoryOutputBoundary{
    
    private final JPanel inventorySummaryBox;
    private final List<Player> playersInfo;
    private final TurnController tc;
    
    public ViewInventoryPresenter(JPanel inventorySummaryBox, List<Player> playersInfo, TurnController turnController){
        this.inventorySummaryBox = inventorySummaryBox;
        this.playersInfo = playersInfo;
        this.tc = turnController;
        showInventoryButtons();
    }
    
    @Override
    public void showInventory(String currentPlayer, List<InventoryData> playersInfo){
        InventoryData player = playersInfo.get(0);
        for(InventoryData x : playersInfo){
            if(Objects.equals(x.getName(), currentPlayer)){
                player = x;
            }
        }
        
        JFrame popUpInventory = new JFrame(currentPlayer + " s' Inventory");
        popUpInventory.setMinimumSize(new Dimension(1000, 390));
        JPanel inventoryInfo = new JPanel();
        JScrollPane testing = new JScrollPane();
        testing.setViewportView(inventoryInfo);
        popUpInventory.add(testing);
        inventoryInfo.add(new JLabel(currentPlayer + " s' Inventory"));
        JButton closeTab = new JButton("Close Inventory");
        popUpInventory.setVisible(true);
        inventoryInfo.add(closeTab);
        closeTab.addActionListener(e -> popUpInventory.setVisible(false));
        TextField money = new TextField("Money: " + player.getMoney());
        money.setEditable(false);
        TextField getOutOfJailFree = new TextField("Get Out of Jail Free Card: " +
                player.getGetOutOfJailFree());
        getOutOfJailFree.setEditable(false);
        inventoryInfo.add(money);
        inventoryInfo.add(getOutOfJailFree);
        List<List<Property>> displayProperties;
        displayProperties = InventoryData.sortProperties(player);
        
        for(List<Property> currentPropertyList : displayProperties){
            JPanel currentPropertySetPanel = new JPanel();
            for(Property currentProperty : currentPropertyList){
                String frontOrBack = "front";
                if(currentProperty.isMortgaged()){
                    frontOrBack = "mortgaged";
                }
                String id;
                if(currentProperty instanceof UtilityTile){
                    id = "utility_" + currentProperty.getTileName() + ".jpg";
                }else if(currentProperty instanceof RailroadTile){
                    id = "rr_" + currentProperty.getTileName() + ".jpg";
                }else{
                    ColorPropertyTile newTemp = (ColorPropertyTile) currentProperty;
                    id = newTemp.getColor().toLowerCase() + "_" + newTemp.getTileName() + ".jpg";
                }
                String path = "src/main/resources/assets/property/property_" + frontOrBack + "_" + id;
                System.out.println(path);
                ImageIcon temp = new ImageIcon(new ImageIcon (path)
                        .getImage().getScaledInstance(250, 320, Image.SCALE_REPLICATE));
                currentPropertySetPanel.add(new JLabel(temp));
            }
            inventoryInfo.add(currentPropertySetPanel);
        }
    }
    
    @Override
    public void showInventoryButtons(){
        GridLayout quad;
        if(playersInfo.size() == 2){
            quad = new GridLayout(1, 2);
        }else{
            quad = new GridLayout(2, 2);
        }
        inventorySummaryBox.setLayout(quad);
        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        JButton player1Button = new JButton("View "+ playersInfo.get(0).getName() + "s' Inventory");
        JButton player2Button = new JButton("View "+ playersInfo.get(1).getName() + "s' Inventory");
        player1.setBackground(new Color(44, 168, 219));
        player2.setBackground(new Color(219, 212, 63));
        player1.add(player1Button);
        player2.add(player2Button);
        inventorySummaryBox.add(player1);
        inventorySummaryBox.add(player2);
        
        if(playersInfo.size() == 3){
            JPanel player3 = new JPanel();
            JButton player3Button = new JButton("View " + playersInfo.get(2).getName() + "s' Inventory");
            player3.setBackground(new Color(219, 42, 74));
            player3.add(player3Button);
            ImageIcon player3Icon = new ImageIcon(new ImageIcon("src/main/resources/assets/misc/pieces/" + playersInfo.get(2).getIcon() + ".png")
                    .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            player3.add(new JLabel(player3Icon));
            player3Button.addActionListener(e -> tc.showInventory(playersInfo.get(2), playersInfo));
            inventorySummaryBox.add(player3);
        }else if (playersInfo.size() == 4) {
            JPanel player3 = new JPanel();
            JButton player3Button = new JButton("View " + playersInfo.get(2).getName() + "s' Inventory");
            player3.setBackground(new Color(219, 42, 74));
            player3.add(player3Button);
            ImageIcon player3Icon = new ImageIcon(new ImageIcon
                    ("src/main/resources/assets/misc/pieces/" + playersInfo.get(2).getIcon() + ".png")
                    .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            player3.add(new JLabel(player3Icon));
            player3Button.addActionListener(e -> tc.showInventory(playersInfo.get(2), playersInfo));
            inventorySummaryBox.add(player3);
            JPanel player4 = new JPanel();
            JButton player4Button = new JButton("View " + playersInfo.get(3).getName() + "s' Inventory");
            player4.setBackground(new Color(143, 34, 54));
            player4.add(player4Button);
            ImageIcon player4Icon = new ImageIcon(new ImageIcon
                    ("src/main/resources/assets/misc/pieces/" + playersInfo.get(3).getIcon() + ".png")
                    .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            player4.add(new JLabel(player4Icon));
            player4Button.addActionListener(e -> tc.showInventory(playersInfo.get(3), playersInfo));
            inventorySummaryBox.add(player4);
        }
        ImageIcon player1Icon = new ImageIcon(new ImageIcon
                ("src/main/resources/assets/misc/pieces/" + playersInfo.get(0).getIcon() + ".png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        player1.add(new JLabel(player1Icon));
        ImageIcon player2Icon = new ImageIcon(new ImageIcon
                ("src/main/resources/assets/misc/pieces/" + playersInfo.get(1).getIcon() + ".png")
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        player2.add(new JLabel(player2Icon));
        player1Button.addActionListener(e -> tc.showInventory(playersInfo.get(0), playersInfo));
        player2Button.addActionListener(e -> tc.showInventory(playersInfo.get(1), playersInfo));
        inventorySummaryBox.setVisible(true);
    }
}
