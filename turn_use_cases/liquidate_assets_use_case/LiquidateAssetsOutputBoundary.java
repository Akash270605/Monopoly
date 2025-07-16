/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.liquidate_assets_use_case;

import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;

import java.util.ArrayList;
import java.util.List;

public interface LiquidateAssetsOutputBoundary {
    
    //playerOptions the presenter will need the list of options the player has available
    void showPlayerOptions(ArrayList<String> playerOptions, LiquiditySituation situation);
    
    //mortgageableProperties the presenter will need the list of properties that the player can mortgage
    void showMortgageableProperties(List<Property> mortgageableProperties, LiquiditySituation situation);
    
    //The presenter will need the list of properties that have houses/hotels
    //that can be sold
    void showPropertiesWithHouses(List<ColorPropertyTile> propertiesWithHouses, LiquiditySituation situation);
    
    //situation holds  all the information as to who will be bankrupted and by who
    void showTransferOfAssets(LiquiditySituation situation);
}
