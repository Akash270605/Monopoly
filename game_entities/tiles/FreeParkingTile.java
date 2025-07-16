/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

public class FreeParkingTile extends Tile{
    public FreeParkingTile(){
        super("FreeParkingTile", "Free Parking Tile");
    }
    
    //Nothing happens
    @Override
    public TileActionResultModel action(Player player, Board board){
        return new TileActionResultModel("You land on free parking tile! Nothing happens!", player,
                player.getPosition());
    }
}
