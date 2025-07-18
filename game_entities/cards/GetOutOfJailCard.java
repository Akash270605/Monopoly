/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.cards;

import game_entities.Player;

public class GetOutOfJailCard extends Card{
    public GetOutOfJailCard(String cardName, String cardDisplayName, String flavourText, boolean chanceCard){
       super(cardName, cardDisplayName, flavourText, chanceCard); 
    }
    
    @Override
    public CardActionResultModel action(Player player){
        player.addGetOutOfJailCard();
        return new CardActionResultModel(getCardDescription(), player, player.getPosition(), getCardName(), 
        isChanceCard());
    }
}
