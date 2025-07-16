/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Player;

public class TileActionResultModel {
    private final String flavorText;
    private final int playerPosition;
    private final Player player;
    
    public TileActionResultModel(String flavorText, Player player, int playerPosition){
        this.flavorText = flavorText;
        this.player = player;
        this.playerPosition = playerPosition;
    }
    
    public String getFlavorText(){
        return flavorText;
    }
    
    public int getPlayerPosition(){
        return playerPosition;
    }
    
    public Player getPlayer() { return player; }
}
