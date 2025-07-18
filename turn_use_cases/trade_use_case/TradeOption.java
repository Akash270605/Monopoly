/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.trade_use_case;

import game_entities.Player;
import game_entities.tiles.Property;

import java.util.ArrayList;

public class TradeOption {
    
    //the amount of money player1 has
    private final int player1Money;
    
    //the amount of money player2 has
    private final int player2Money;
    
    private final boolean player1JailCard;
    private final boolean player2JailCard;
    
    //The properties player1 owns
    private final ArrayList<Property> player1Properties;
    
    //The properties player2 owns
    private final ArrayList<Property> player2Properties;
    
    //the player who started the trade
    private final Player player1;
    
    //the player who is receiving the trade
    private final Player player2;
    
    public TradeOption(int player1Money, int player2Money, boolean player1JailCard, boolean player2JailCard,
           ArrayList<Property> player1Properties, ArrayList<Property> player2Properties, Player player1, Player player2 ){
       
        this.player1Money = player1Money;
        this.player2Money = player2Money;
        this.player1JailCard = player1JailCard;
        this.player2JailCard = player2JailCard;
        this.player1Properties = player1Properties;
        this.player2Properties = player2Properties;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public int getPlayer1Money(){
        return player1Money;
    }
    
    public int getPlayer2Money(){
        return player2Money;
    }
    
    public boolean getPlayer1JailCard(){
        return player1JailCard;
    }
    
    public boolean getPlayer2JailCard(){
        return player2JailCard;
    }
    
    public ArrayList<Property> getPlayer1Properties(){
        return player1Properties;
    }
    
    public ArrayList<Property> getPlayer2Properties(){
        return player2Properties;
    }
    
    public Player getPlayer1(){
        return player1;
    }
    
    public Player getPlayer2(){
        return player2;
    }
}


