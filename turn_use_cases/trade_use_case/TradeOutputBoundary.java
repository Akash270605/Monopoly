/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.trade_use_case;

import game_entities.Player;
import java.util.ArrayList;

public interface TradeOutputBoundary {
    
    //Presents the list of players player can trade with
    void showListOfPlayers(ArrayList<Player> listOfPlayers, Player player, String falvorText);
    
    //Presents the possible options for player1 to trade with player2
    void showTradeOptions(TradeOption tradeOption, String flavorText);
    
    //Presents the trade offer
    void showTradeOffer(TradeOffer tradeOffer, String flavorText);
    
    //Presents the result of the trade offer
    void showResultOfTradeOffer(int option, String flavorText, Player player1, Player player2);
}
