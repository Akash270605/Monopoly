/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.end_turn_use_case;

import game_entities.Player;

public interface EndTurnInputBoundary {
    //End the player's turn
    void endTurn(Player player);
    
    //Forcefully end the player's turn
    void forceEndTurn(Player player);
}
