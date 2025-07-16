/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.move_player_use_case;

import game_entities.Player;
import game_entities.tiles.Tile;
import game_entities.tiles.TilePassResultModel;

public interface MovePlayerOutputBoundary {
    
    void showResultOfAction(Player player, int playerPosition, boolean roolAgain, String flavorText,
            String buttonText);
    
    //shows the result of player passing the tile
    void showResultOfPass(Player player, int playerPosition, TilePassResultModel tilePassResultModel);
    
    //Shows the roll the player made
    void showRoll(int[] playerRollAmount);
    
    //shows the card the player drew and if they moved or not
    void showCardDraw(Player player, String cardName, boolean rollAgain, 
            boolean isChance);
    
    //shows that the property is purchasable
    void showBuyableProperty(Player player, Tile tile, boolean buyable, boolean rollAgain);
}
