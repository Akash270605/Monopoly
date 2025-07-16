/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.cards;

import game_entities.Board;
import game_entities.Player;

public class AdvanceCard extends Card{
    private final int tileNumber;
    
    private final String tileName;
    private final Board board;
    
    public AdvanceCard(String cardName, String cardDisplayName, String flavourText,
            boolean chanceCard, int tileNumber, String tileName, Board board){
        
        super(cardName, cardDisplayName, flavourText, chanceCard);
        this.tileNumber = tileNumber;
        this.tileName = tileName;
        this.board = board;
    }
    
    @Override
    public CardActionResultModel action(Player player){
        if(tileName.equals("JailTile")){
            player.enterJail();
            return new CardActionResultModel(getCardDescription(), player, board.getJailTilePosition(), getCardName(),
            isChanceCard());
        }else if(tileNumber ==-3){
            return new CardActionResultModel(getCardDescription(), player, player.getPosition() - tileNumber,
            getCardName(), isChanceCard());
        }
        
        return new CardActionResultModel(getCardDescription(), player, tileNumber, getCardName(), isChanceCard());
    }
}
