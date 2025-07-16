/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.view_inventory;

import game_entities.Player;
import java.util.List;

public interface ViewInventoryInputBoundary {
    //Pass the information that is needed to be displayed to be presenter
    void displayInfo(Player currentPlayer, List<Player> players);
}
