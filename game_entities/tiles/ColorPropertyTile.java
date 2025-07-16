/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Board;
import game_entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ColorPropertyTile extends Property{
    private final String color;
    private final int[] rentPrice;
    private final int buildingCost;
    private int numHouses;
    private int numHotels;
    
    public ColorPropertyTile(String color, String propertyName, String propertyDisplayName, int purchasePrice,
            int [] rentPrice, int buildingCost, int mortgageValue, int unMortgageValue){
        
        super(propertyName, propertyDisplayName, purchasePrice, mortgageValue, unMortgageValue);
        this.color = color;
        this.rentPrice = rentPrice;
        this.buildingCost = buildingCost;
        this.numHouses = 0;
        this.numHotels = 0;
    }
    
    public String getColor(){
        return color;
    }
    
    public int getNumHouses() { return numHouses; }
    public int getNumHotels() { return numHotels; }
    
    public void addHouse(int add){
        this.numHouses +=add;
    }
    
    public void addHotel(int add){
        this.numHotels += add;
    }
    
    public void subtractHouse(int subtract){
        this.numHouses -= subtract;
    }
    
    public void subtractHotel(int subtract){
        this.numHotels -= subtract;
    }
    
    //Returns whether all of the colored property in a specified colored set are owned
    public boolean allColoredPropertySetOwned(String myColor, List<Property> propertyList){
        if(!isOwned()){
            return false;
        }
        int numOwned = 0;
        for(Property property : propertyList){
            if(property instanceof ColorPropertyTile &&
                    property.isOwned() && getColor().equals(myColor) && 
                    property.getOwner().equals(getOwner())){
                numOwned++;
            }
        }
        switch(myColor){
            case "Brown":
                if(numOwned == 2){
                    return true;
                }
            case "Dark Blue":
                if(numOwned == 2){
                    return true;
                }
        }
        return numOwned == 3;
    }
    
    //Return the rent for this ColorPropertyTile property;
    @Override
    public int getRent(Player rentPayer, List<Property> propertyList){
        if(!isOwned()){
            return -1;
        }
        if((getNumHotels() == 0 && getNumHouses() == 0) & allColoredPropertySetOwned(getColor(), propertyList)){
            return rentPrice[1];
        }
        
        switch(getNumHouses()){
            case 1:
                return rentPrice[2];
            case 2: 
                return rentPrice[3];
            case 3:
                return rentPrice[4];
            case 4:
                return rentPrice[5];
        }
        if(getNumHotels() == 1){
            return rentPrice[6];
        }
        return rentPrice[0];
    }
    
    //Perform the action for when Player on this this tile
    @Override
    public TileActionResultModel action(Player player, Board board){
        if(!isOwned()){
            return new TileActionResultModel("Would you Like to Purchase " + getTileDisplayName() + " for " + getPurchasePrice() + " ?", player, player.getPosition());
        }else{
            if(getRent(player, board.getPropertyTiles()) <= player.getMoney()){
                getOwner().addMoney(getRent(player, board.getPropertyTiles()));
            }
            player.subtractMoney(getRent(player, board.getPropertyTiles()), getOwner());
            return new TileActionResultModel("You Paid " + getRent(player, board.getPropertyTiles()) + " to " + getOwner().getName(), player, player.getPosition());
        }
    }
    
    public int getBuildingCost(){
        return buildingCost;
    }
    
    public boolean checkSetOwned(List<Property> arr){
        ArrayList<Player> playerArr = new ArrayList<>();
        ArrayList<ColorPropertyTile> sameColor = new ArrayList<>();
        for(Property property : arr){
            if(property instanceof ColorPropertyTile && Objects.equals(((ColorPropertyTile) property).getColor(), this.color)){
                sameColor.add((ColorPropertyTile) property);
            }
        }
        
        for(ColorPropertyTile colorPropertyTile : sameColor){
            if(colorPropertyTile.isOwned()){
                playerArr.add(colorPropertyTile.getOwner());
            }else{
                return false;
            }
        }
        
        Player firstPlayer = playerArr.get(0);
        for(Player player : playerArr){
            if(!player.equals(firstPlayer)){
                return false;
            }
        }
        return true;
    }
}
