/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.build_use_case;

import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;

public interface BuildBuildingInputBoundary {
    
    //check if player can build building
    boolean isBuildable(Player player, Property property);
    
    //List of properties on which can build a building
    void showBuildOption(Player player);
    
    //Build a house on the property
    void buildHouse(Player player, ColorPropertyTile property);
    
    //Build a hotel on the property
    void buildHotel (Player player, ColorPropertyTile property);
    
    //Check if player can sell buildings
    boolean isSellable(Player player, ColorPropertyTile property);
    
    //A list of properties which has building can be  sold
    void showSellOption(Player player);
    
    //Selling a building on the property
    void sellHouse(Player player , ColorPropertyTile property);
    
    //selling a building on the property;
    void sellHotel(Player player , ColorPropertyTile property);
}
