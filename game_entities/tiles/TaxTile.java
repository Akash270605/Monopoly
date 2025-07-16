/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

public class TaxTile extends Tile{
    private final int taxAmount ;
    
    public TaxTile(String tileName, String tileDisplayName, int taxAmount){
        super(tileName, tileDisplayName);
        this.taxAmount = taxAmount;
    }
    
    //Player pays tax when lands on tax tile
    public TileActionResultModel action(Player player, Board board){
        player.subtractMoney(taxAmount);
        return new TileActionResultModel("You paid $" + taxAmount + getTileDisplayName() + " !", player,
                    player.getPosition());
    }
}
