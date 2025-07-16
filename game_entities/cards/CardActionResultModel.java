/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.cards;

import game_entities.Player;
import game_entities.tiles.TileActionResultModel;

public class CardActionResultModel extends TileActionResultModel{
    private final String cardName;
    private final boolean isChance;
    
    public CardActionResultModel(String flavourText, Player player, int playerPosition, String cardName,
            boolean isChance){
        super(flavourText, player, playerPosition);
        this.cardName = cardName;
        this.isChance = isChance;
    }
    
    public String getCardName(){
        return cardName;
    }
    
    public boolean isChance(){
        return isChance;
    }
}
