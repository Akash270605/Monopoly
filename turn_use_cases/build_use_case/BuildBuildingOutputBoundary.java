/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.build_use_case;

import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;

import java.util.ArrayList;

public interface BuildBuildingOutputBoundary {
    
    //presents the list properties which would build  a building
    void showBuildOption(ArrayList<ColorPropertyTile> properties, String flavorText);
    
    //Presents the property which would build a building
    void showBuildBuilding(Player player, ColorPropertyTile property, String flavorText);
    
    //Presents the property on which the building would be sold
    void showSellBuilding(Player player, ColorPropertyTile property, String flavorText);
    
    //Presents the list properties which would sell a building
    void showSellOption(ArrayList<ColorPropertyTile> properties, String flavorText);
}
