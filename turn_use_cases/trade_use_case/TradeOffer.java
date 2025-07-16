/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.trade_use_case;

import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;

import java.util.ArrayList;

public class TradeOffer {
    
    private final int tradeMoney;
    private final int jailCard;
    private final ArrayList<Property> propertiesOffered;
    private final ArrayList<Property> propertiesReceived;
    private final Player player1;
    private final Player player2;
    private final boolean isValid;
    
    public TradeOffer(int tradeMoney, int jailCard, 
            ArrayList<Property> propertiesOffered, ArrayList<Property> propertiesReceived,
            Player player1, Player player2){
        
        this.tradeMoney = tradeMoney;
        this.jailCard = jailCard;
        this.propertiesOffered = propertiesOffered;
        this.propertiesReceived = propertiesReceived;
        this.player1 = player1;
        this.player2 = player2;
        this.isValid = checkIsValid();
    }
    
    //Checks if this TradeOffer is valid or not
    public boolean checkIsValid(){
        if(tradeMoney > 0 && player1.getMoney() < tradeMoney){
            return false;
        }else if(tradeMoney < 0 && player2.getMoney() < -tradeMoney){
            return false;
        }else if(!checkPropertiesOffered()){
            return false;
        }else if(!checkPropertiesReceived()){
            return false;
        }else{
            return checkJailCard();
        }
    }
    
    //Checks if player1 actually owns all the properties he/she is offering
    public boolean checkPropertiesOffered(){
        for(Property p : propertiesOffered){
            if(!player1.getProperties().contains(p)){
                return false;
            }else if(p instanceof ColorPropertyTile){
                ColorPropertyTile cp = (ColorPropertyTile) p;
                if(cp.getNumHouses() > 0 || cp.getNumHotels() > 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    //checks if player2 actually owns all the properties he/she is offering
    public boolean checkPropertiesReceived(){
        for(Property p : propertiesReceived){
            if(!player2.getProperties().contains(p)){
                return false;
            }
        }
        return true;
    }
    
    //checks if the player offering the jail card has one
    public boolean checkJailCard(){
        if(getJailCard() > 0 && !player1.hasGetOutofJailFreeCard()){
            return false;
        }else return !(getJailCard() < 0 && !player2.hasGetOutofJailFreeCard());
    }
    
    public int getTradeMoney(){
        return tradeMoney;
    }
    
    public int getJailCard(){
        return jailCard;
    }
    
    public Player getPlayer1(){
        return player1;
    }
    
    public Player  getPlayer2(){
        return player2;
    }
    
    public ArrayList<Property> getPropertiesOffered(){
        return propertiesOffered;
    }
    
    public ArrayList<Property> getPropertiesReceived(){
        return propertiesReceived;
    }
    
    public boolean isValid(){
        return isValid;
    }
}
