/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

import java.util.List;

import static java.lang.Math.min;

public class RailroadTile extends Property{
    private final int[] rentPrice;
    
    public RailroadTile(String propertyName, String propertyDisplayName, int purchasePrice, int[] rentPrice,
            int mortgageValue, int unMortgageValue){
        
        super(propertyName, propertyDisplayName, purchasePrice, mortgageValue, unMortgageValue);
        this.rentPrice = rentPrice;
    }
    
    //Returns the number of railroads that the owner of this property owns is propertyList
    public int numRailroadOwned(List<Property> propertyList){
        if(!isOwned()){
            return 0;
        }
        
        int numOwned = 0;
        for(Property property : propertyList){
            if(property instanceof RailroadTile &&
                    property.isOwned() && 
                    property.getOwner().equals(getOwner())){
                numOwned++;
            }
        }
        return numOwned;
    }
    
    //Return the rent for this railroad property
    public int getRent(Player rentPlayer, List<Property> propertyList){
        if(!isOwned()){
            return -1;
        }
        
        int numOwned = numRailroadOwned(propertyList);
        
        //if we have the unintended effect of more railroad owned than accounted for, return the last rent value
        numOwned = min(numOwned - 1, rentPrice.length - 1);
        return rentPrice[numOwned];
    }
    
    //Perform the action for when player lands on this tile
    @Override
    public TileActionResultModel action(Player player, Board board){
        if(!isOwned()){
            return new TileActionResultModel("Would you Like to Purchase " + getTileDisplayName() + " for" + getPurchasePrice() + " ?", player, player.getPosition());
        }else{
            if(getRent(player, board.getPropertyTiles()) <= player.getMoney()){
                getOwner().addMoney(getRent(player, board.getPropertyTiles()));
            }
            player.subtractMoney(getRent(player, board.getPropertyTiles()), getOwner());
            return new TileActionResultModel("You Paid" + getRent(player, board.getPropertyTiles()) + " to" + getOwner().getName(), player, player.getPosition());
        }
    }
}
