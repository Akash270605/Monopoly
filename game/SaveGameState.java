/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author Leveno
 */
public interface SaveGameState {
    //Save the GameState object
    boolean save(GameState gameState, String saveName);
}
