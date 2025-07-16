/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

public class GoToJailTile extends Tile{
    public GoToJailTile(){
        super("GoToJailTile", "Go To Jail Tile");
    }
    
    //When lands on this tile, player moves to JailTile
    @Override
    public TileActionResultModel action(Player player, Board board){
        player.enterJail();
        return new TileActionResultModel("You are being sent ot jail.", player, -1);
    }
}
