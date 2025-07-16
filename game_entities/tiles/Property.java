/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

import game_entities.Player;
import java.util.List;

public abstract class Property extends Tile{
    private final int purchasePrice;
    private final int mortgageValue;
    private final int unMortgageValue;
    private boolean mortgaged;
    private Player owner;
    
    public Property(String propertyName, String propertyDisplayName, int purchasePrice, int mortgageValue){
        this(propertyName, propertyDisplayName, purchasePrice, mortgageValue, mortgageValue);
    }
    
    public Property(String propertyName, String propertyDisplayName, int purchasePrice, int mortgageValue,
            int unMortgageValue){
        
        super(propertyName, propertyDisplayName, true);
        this.purchasePrice = purchasePrice;
        this.mortgageValue = mortgageValue;
        this.unMortgageValue = unMortgageValue;
        this.mortgaged = false;
        this.owner = null;
    }
    
    //Get the rent value for this property
    public abstract int getRent(Player rentPlayer, List<Property> properList);
    
    //Mortgage this property and return the mortgage value
    public int mortgage(){
        this.mortgaged = true;
        return mortgageValue;
    }
    
    //Unmortgage this property
    public void unmortgage(){
        this.mortgaged = false;
    }
    
    public int getPurchasePrice(){
        return purchasePrice;
    }
    
    public int getMortgageValue(){
        return mortgageValue;
    }
    
    public int getUnMortgageValue(){
        return unMortgageValue;
    }
    
    public boolean isMortgaged(){
        return mortgaged;
    }
    
    public boolean isOwned(){
        return owner != null;
    }
    
    public Player getOwner(){
        return owner;
    }
    
    //Set the owner of this property
    public Player setOwner(Player owner){
        Player prevOwner = this.owner;
        this.owner = owner;
        return prevOwner;
    }
}
