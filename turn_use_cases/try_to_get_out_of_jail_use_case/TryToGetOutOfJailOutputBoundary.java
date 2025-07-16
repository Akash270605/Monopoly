/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.try_to_get_out_of_jail_use_case;

import game_entities.Player;
import java.util.ArrayList;

public interface TryToGetOutOfJailOutputBoundary {
    
    //shows the options the player can choose
    void showOptions(Player player, ArrayList<String> options);
    
    //shows what the player rolled
    void showRoll(int[] playerRollAmount);
}
