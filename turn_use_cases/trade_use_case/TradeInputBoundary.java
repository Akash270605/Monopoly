/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.trade_use_case;

import game_entities.Player;
import java.util.List;

public interface TradeInputBoundary {
    
    //Provides a list of all the other players in teh game player can trade with to the presenter
    void choosePlayer(List<Player> listOfPlayers, Player player);
    
    //Provides the presenter with all potential options for player1 and player2 to trade
    void getTradeOptions(Player player1, Player player2);
    
    //Provides the presenter with the details of the trade offer and whether it is valid
    void makeOffer(TradeOffer tradeOffer, Player player1, Player player2);
    
    //Provides the presenter with the result of the trade and rearranges player1' s and player2's inventory
    void getResultOfTradeOffer(int option, Player player1, Player player2, TradeOffer tradeOffer);
}
