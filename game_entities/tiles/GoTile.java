/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

public class GoTile extends Tile{
    public GoTile(){
        super("GoTile" ,"Go Tile");
    }
    
    //Nothing happens when a player lands on this tile specifically, only when passing
    @Override
    public TileActionResultModel action(Player player, Board board){
        return new TileActionResultModel("You landed on Go!", player, player.getPosition());
    }
    
    //Give $200 to the player that passes on this tile
    @Override
    public TilePassResultModel passing(Player player){
        player.addMoney(200);
        return new TilePassResultModel(true, "You get $200!");
    }
}
