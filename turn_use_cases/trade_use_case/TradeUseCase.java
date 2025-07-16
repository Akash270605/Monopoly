/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.trade_use_case;

import game_entities.Player;
import game_entities.tiles.Property;

import java.util.ArrayList;
import java.util.List;

public class TradeUseCase implements TradeInputBoundary{
    
    private final TradeOutputBoundary presenter;
    
    public TradeUseCase(TradeOutputBoundary presenter){
        this.presenter = presenter;
    }
    
    //Provides a list of all other players in the game player can with to the presenter
    @Override
    public void choosePlayer(List<Player> listOfPlayers, Player player){
        ArrayList<Player> listOfPotentialPlayers = new ArrayList<>(listOfPlayers);
        listOfPotentialPlayers.remove(player);
        
        presenter.showListOfPlayers(listOfPotentialPlayers, player, player.getName() + ", please choose who to trade with.");
    }
    
    //Provides presenter with all potential options for player1 and player2 to trade
    @Override
    public void getTradeOptions(Player player1, Player player2){
        TradeOption tradeOption = new TradeOption(player1.getMoney(), player2.getMoney(),
                    player1.hasGetOutofJailFreeCard(), player2.hasGetOutofJailFreeCard(),
                    player1.getProperties(), player2.getProperties(), player1, player2);
        
        presenter.showTradeOptions(tradeOption, "<html><body>" + player1.getName() +
                ", please choose what you want to trade. (Ctrl + Click to select mulitple properties)" + "</body></html>");
        
    }
    
    //Provides the presenter with the details of the trade offer and whether it is valid
    @Override
    public void makeOffer(TradeOffer tradeOffer, Player player1, Player player2){
        if(tradeOffer.isValid()){
            presenter.showTradeOffer(tradeOffer, player2.getName() + ", do you accept this trade?");
        }else{
            presenter.showTradeOffer(tradeOffer, player1.getName() +
                    ", this is an invalid trade, please make another offer.");
        }
    }
    
    //Provides the presenter with the result of the trade and rearranges player1's and player2's inventory
    @Override
    public void getResultOfTradeOffer(int option, Player player1, Player player2, TradeOffer tradeOffer){
        
        if(option == 1){
            ExecuteOffer(player1, player2, tradeOffer);
            presenter.showResultOfTradeOffer(1, "The Trade was a success!", player1, player2);
        }else if(option == 2){
            presenter.showResultOfTradeOffer(2, player2.getName() + "wants to make a counter offer!", player1, player2);
        }else if(option == 3){
            presenter.showResultOfTradeOffer(3, "The offer was declined!", player1, player2);
        }else{
            presenter.showResultOfTradeOffer(4, "That was a invalid input, please try again.", player1, player2);
        }
    }
    
    //Rearranges player1's and player2's inventory depending on tradeOffer
    public void ExecuteOffer(Player player1, Player player2, TradeOffer tradeOffer){
        player1.subtractMoney(tradeOffer.getTradeMoney());
        player2.addMoney(tradeOffer.getTradeMoney());
        
        for(Property p : tradeOffer.getPropertiesReceived()){
            player1.addProperty(p);
            player2.sellProperty(p);
        }
        
        for(Property p : tradeOffer.getPropertiesOffered()){
            player1.sellProperty(p);
            player2.addProperty(p);
        }
        
        if(tradeOffer.getJailCard() > 0){
            player1.removeGetOutOfJailCard();
            player2.addGetOutOfJailCard();
        }else if(tradeOffer.getJailCard() < 0){
            player1.addGetOutOfJailCard();
            player2.removeGetOutOfJailCard();
        }
    }
}
