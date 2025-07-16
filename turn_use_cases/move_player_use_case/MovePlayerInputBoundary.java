/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.move_player_use_case;

import game_entities.Player;

public interface MovePlayerInputBoundary {
    
    //Starts the logic of rolling the dies
    void startAction(Player player, boolean canRollAgain);
    
    //This is only called by startAction when it lands on a draw card
    //tile that will move the player
    void movePlayer(Player player, int rollSum, boolean doubleRoll);
}
