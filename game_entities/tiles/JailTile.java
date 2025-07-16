/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

public class JailTile extends Tile{
    public JailTile(){
        super("JailTile" , "jail Tile");
    }
    
    //When lands on this tile, player cannot move for the next 3 turns unless they pay $50 to the bank
    @Override
    public TileActionResultModel action(Player player, Board board){
        String inJail = "You are in jail";
        String notInJail = "You are just visitiing";
        if(player.getTurnsInJail() != -1){
            return new TileActionResultModel(inJail, player, board.getJailTilePosition());
        }else{
            return new TileActionResultModel(notInJail, player, board.getJailTilePosition());
        }
    }
}
