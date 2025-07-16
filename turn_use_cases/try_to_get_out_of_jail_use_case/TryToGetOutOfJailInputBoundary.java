/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.try_to_get_out_of_jail_use_case;

import game_entities.Player;

public interface TryToGetOutOfJailInputBoundary {
    
    void startAction(String playerOption, Player player);
    
    //Returns a string array of possible options the player can choose
    void getPlayerOptions(Player player);
}
