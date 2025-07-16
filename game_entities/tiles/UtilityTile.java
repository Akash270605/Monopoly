/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

import java.util.List;
import static java.lang.Math.min;

public class UtilityTile extends Property{
    private final int[] rentFactor;
    
    public UtilityTile(String propertyName, String propertyDisplayName, int purchasePrice, int[] rentFactor,
            int mortgageValue, int unMortgageValue){
        
        super(propertyName, propertyDisplayName, purchasePrice, mortgageValue, unMortgageValue);
        this.rentFactor = rentFactor;
    }
    
    //Returns the number of utilites that the owner of this property owns in propertyList
    public int numUtilityOwned(List<Property> propertyList){
        if(!isOwned()){
            return 0;
        }
        
        int numOwned = 0;
        for(Property property : propertyList){
            if(property instanceof UtilityTile && 
                    property.isOwned() &&
                    property.getOwner().equals(getOwner())){
                numOwned++;
            }
        }
        return numOwned;
    }
    
    //Get the Rent value for this Utility
    @Override
    public int getRent(Player rentPayer, List<Property> propertyList){
        int numOwned = numUtilityOwned(propertyList);
        
        //If we have the unintended effect of more railroads owned then, accounted for, return the last rent value
        numOwned = min(numOwned -1, rentFactor.length - 1);
        
        int diceRollSum = 0;
        for(int value : rentPayer.getLastRoll()){
            diceRollSum += value;
        }
        
        return rentFactor[numOwned] * diceRollSum;
    }
    
    //Perform the action for when player lands on this tile
    @Override
    public TileActionResultModel action(Player player ,Board board){
        if(!isOwned()){
            return new TileActionResultModel("Would you Like to Purchase "  + getTileDisplayName() + " for " + getPurchasePrice() + " ?", player, player.getPosition());            
        }else{
            if(getRent(player, board.getPropertyTiles()) <= player.getMoney()){
                getOwner().addMoney(getRent(player, board.getPropertyTiles()));
            }
            player.subtractMoney(getRent(player, board.getPropertyTiles()), getOwner());
            return new TileActionResultModel("You Paid " + getRent(player, board.getPropertyTiles()) + " to" + getOwner().getName(), player, player.getPosition());
        }
    }
}
