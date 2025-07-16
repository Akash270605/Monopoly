/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.mortgage_use_case;

import game_entities.Player;
import game_entities.tiles.Property;

import java.util.ArrayList;

public interface MortgagePropertyOutputBoundary {
    
    //Presents the property which is mortgaged by player
    void showMortgageProperty(Player player, Property property, String flavorText);
    
    //Presents the property which is unmortgaged by player
    void showUnmortgageProperty(Player player, Property property, String flavorText);
    
    //Presents the properties which can be mortgaged
    void showMortgagePropertyList(ArrayList<Property> properties, String flavorText);
    
    //Presents the properties which can be unmortgaged
    void showUnmortgagePropertyList(ArrayList<Property> properties, String flavorText);
}
