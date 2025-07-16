/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import turn_interface_adapters.TurnController;
import java.util.List;

public interface LoadGameState {
    
    //Load the GameState object
    GameState load(String saveName, SaveGameState saveGameState, GameStateOutputBoundary presenter, TurnController turnController);
    
    //Check if the save exists
    boolean saveExists(String saveName);
    
    //Get a list of all the names of the games that are currently saved
    List<String> getAllSaves();
    String getSavesDirectory();
}
