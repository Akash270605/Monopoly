/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities;

import game.GameState;
import game_entities.tiles.Property;
import turn_use_cases.liquidate_assets_use_case.LiquidateAssetsInputBoundary;
import turn_use_cases.liquidate_assets_use_case.LiquiditySituation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Player implements Serializable{
    private final String name;
    private int money;
    private final ArrayList<Property> properties = new ArrayList<>();
    private int turnsInJail = -1;
    private int position = 0;
    private final String icon;
    private final int[] lastRoll = {0, 0};
    private int consecutiveDoubles = 0;
    
    private int getOutOfJailFree = 0;
    
    private final Board board;
    
    private transient LiquidateAssetsInputBoundary liquidateAssetsInputBoundary;
    private GameState gameState;
    
    public Player(String nameInput, String iconInput, int money, Board board){
        this.name = nameInput;
        this.icon = iconInput;
        this.money = money;
        this.board = board;
    }
    
    public void setLiquidateAssetsInputBoundary(LiquidateAssetsInputBoundary liquidateAssetsInputBoundary){
        this.liquidateAssetsInputBoundary = liquidateAssetsInputBoundary;
    }
    
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }
    
    //getters
    public String getName(){
        return this.name;
    }
    
    public int getMoney(){
        return this.money;
    }
    
    public ArrayList<Property> getProperties(){
        return this.properties;
    }
    
    public int getTurnsInJail(){
        return this.turnsInJail;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public String getIcon(){
        return this.icon;
    }
    
    public int[] getLastRoll(){
        return this.lastRoll;
    }
    
    public int getConsecutiveDoubles(){
        return this.consecutiveDoubles;
    }
    
    //setters
    public void setLastRoll(int roll1, int roll2){
        this.lastRoll[0] = roll1;
        this.lastRoll[1] = roll2;
    }
    
    //Add an amount of money to the players total
    public void addMoney (int add){
        this.money += add;
    }
    
    //Attempt to subtract an amount of money from the players total
    public void subtractMoney(int subtract){
        this.subtractMoney(subtract, null);
    }
    
   public void subtractMoney(int subtract, Player owedPlayer){
       if(subtract <= this.money){
           this.money -= subtract;
       }
       else{
           LiquiditySituation situation = new LiquiditySituation(this, owedPlayer, subtract, this.gameState, this.board);
           liquidateAssetsInputBoundary.getPlayerOptions(situation);
       }
   } 
   
   //Check if a player owns a property
   public boolean ownsProperty(Property check){
       return this.properties.contains(check);
   }
   
   //Add a property to a player property ArrayList
   public void addProperty(Property add){
       this.properties.add(add);
       add.setOwner(this);
   }
   
   //Remove a given property from a player from a players property ArrayList
   public void sellProperty(Property sell){
       this.properties.remove(sell);
   }
   
   //This method will be called every turn a player is in the jail to update the in jail turn tracker
   public void addTurnInJail(){
       if(this.turnsInJail == 2){
           this.turnsInJail = -1;
           this.resetConsecutiveDoubles();
       }else{
           this.turnsInJail += 1;
       }
   }
   
   //Called when the player has left the jail to reset the number of turn spent in jail to -1 which just states that 
   //player is not in jail
   public void resetTurnInJail(){
       this.turnsInJail = -1;
   }
   
   //Called when a player enter a jail
   public void enterJail(){
       this.addTurnInJail();
       this.position = this.board.getJailTilePosition();
       this.resetConsecutiveDoubles();
   }
   
   //update the position of a player given the sum of the dies they have rolled
   public void updatePosition(int rollSum){
       this.position = (this.position + rollSum) % this.board.getTilesList().size();
   }
   
   //Set the position of the player to a given position
   public void setPosition(int position){
       this.position = position;
   }
   
   //Checks if a player has a get out of jail free card
   public boolean hasGetOutofJailFreeCard(){
       return (this.getOutOfJailFree != 0);
   }
   
   //Return the nmber of get out of jail free cards a player has
   public int numGetOutofJailFreeCards() { return this.getOutOfJailFree; }
   
   //Remove 1 get out of jail free card from the players inventory
   public void removeGetOutOfJailCard(){
       this.getOutOfJailFree -=1;
   }
   
   //Adds one get out of jail free card to the players inventory
   public void addGetOutOfJailCard(){
       this.getOutOfJailFree += 1;
   }
   
   //Returns whether this player equals the other object
   @Override
   public boolean equals(Object o){
       if(this == o) return true;
       if(o == null || getClass() != o.getClass()) return false;
       Player player = (Player ) o;
       return money == player.money && turnsInJail == player.turnsInJail && position == player.position && consecutiveDoubles == player.consecutiveDoubles && getOutOfJailFree == player.getOutOfJailFree && name.equals(player.name) && properties.equals(player.properties) && icon.equals(player.icon) && Arrays.equals(lastRoll, player.lastRoll);
       
   }
   
   @Override
   public int hashCode(){
       int result = Objects.hash(name, money, properties, turnsInJail, position, icon, consecutiveDoubles, getOutOfJailFree);
       result = 31 * result + Arrays.hashCode(lastRoll);
       return result;
   }
   
   //Take in a player two die rolls and update the consecutive doubles attributes if they are equal
   public void updateConsecutiveDoubles(int roll1, int roll2){
       if(roll1 == roll2){
           this.consecutiveDoubles += 1;
       }else{
           this.resetConsecutiveDoubles();
       }
   }
   
   //Resets the consecutive doubles rolled by the player to 0
   public void resetConsecutiveDoubles(){
       this.consecutiveDoubles = 0;
   }
}
