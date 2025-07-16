/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.mortgage_use_case;

import game_entities.Player;
import game_entities.tiles.Property;

public interface MortgagePropertyInputBoundary {
    //Provides a list of properties which can be mortgaged
    void showMortgageOption(Player player);
    
    //Provides a list of properties which can be unmortgaged
    void showUnmortgageOption(Player player);
    
    //Overloading method mortgage. Player mortgages a color property
    void mortgage(Player player, Property property);
    
    //Player unmortgages property
    void unmortgage(Player player, Property property);
}
