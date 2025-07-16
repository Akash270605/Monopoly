/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.view_inventory;

import java.util.List;

public interface ViewInventoryOutputBoundary {
    //display the inventory of the players
    void showInventory(String currentName, List<InventoryData> playersInfo);
    
    //Displays 4 buttons that when clicked will open a players inventory
    void showInventoryButtons();
}
