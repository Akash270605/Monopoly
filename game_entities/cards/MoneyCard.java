/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.cards;

import game_entities.Player;

public class MoneyCard extends Card{
    private final int money;
    
    public MoneyCard(String cardName, String cardDisplayName, String flavourText,
            boolean chanceCard, int money){
        
        super(cardName, cardDisplayName, flavourText, chanceCard);
        this.money = money;
    }
    
    @Override
    public CardActionResultModel action(Player player){
        if(0 <= this.money){
            player.addMoney(this.money);
        }else{
            player.subtractMoney(this.money * -1);
        }
        
        return new CardActionResultModel(getCardDescription(), player, player.getPosition(), getCardName(),
        isChanceCard());
    }
}
