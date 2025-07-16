/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

import java.io.Serializable;
import java.util.Objects;

public abstract class Tile implements Serializable{
    private final String tileName;
    private final String tileDisplayName;
    private final boolean ownable;
    
    //tiles are spaces on the board that player land on and may have effects when landed on or when passed
    public Tile(String tileName, String tileDisplayName){
        this.tileName = tileName;
        this.tileDisplayName = tileDisplayName;
        this.ownable = false;
    }
    
    //Returns whether this Tile equals other object
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return ownable == tile.ownable && tileName.equals(tile.tileName) && tileDisplayName.equals(tile.tileDisplayName);
        
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(tileName, tileDisplayName, ownable);
    }
    
    //tiles are spaces on the board the players land on and may have effects when landed on or when passed
    protected Tile(String tileName, String tileDisplayName, boolean ownable){
        this.tileName = tileName;
        this.tileDisplayName = tileDisplayName;
        this.ownable = ownable;
    }
    
    //performs the action for when Player lands on this tile
    public abstract TileActionResultModel action(Player player, Board board);
    
    //Performs the action for when Player passes this tile
    public TilePassResultModel passing(Player player){
        return new TilePassResultModel(false, "");
    }
    
    public String getTileName(){
        return tileName;
    }
    
    public String getTileDisplayName(){
        return tileDisplayName;
    }
    
    public boolean isOwnable(){
        return ownable;
    }
}
