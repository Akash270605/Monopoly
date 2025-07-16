/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.view_inventory;

import game_entities.Player;
import java.util.ArrayList;
import java.util.List;

public class ViewInventory implements ViewInventoryInputBoundary{
    private final ViewInventoryOutputBoundary presenter;
    
    public ViewInventory(ViewInventoryOutputBoundary presenter){
        this.presenter = presenter;
    }
    
    //Creates a list of InventoryData given from the list of players and pass that to the presenter
    @Override
    public void displayInfo(Player currentPlayer, List<Player> players){
        List<InventoryData> playerData = new ArrayList<>();
        for(Player player : players ){
            playerData.add(new InventoryData(player));
        }
        presenter.showInventory(currentPlayer.getName(), playerData);
    }
}
