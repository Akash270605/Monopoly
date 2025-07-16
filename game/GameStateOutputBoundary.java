/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import game_entities.Player;
import java.util.List;

public interface GameStateOutputBoundary {
    //show that it is the next player's turn
    void showNextTurn(Player newPlayer, int turnNumber);
    
    //show the current player the valid turn actions they can take
    void showTurnActions(Player currentPlayer, List<TurnActions> validActions);
    
    //show whether the autosave was successful
    void showAutosaveStatus(boolean successful);
    
    enum TurnActions{
       ROLL_TO_MOVE,
      BUILD_BUILDING,
      SELL_BUILDING,
      MORTGAGE,
        UNMORTGAGE,
        TRADE,
        LEAVE_JAIL,
        END_TURN
    }
}
