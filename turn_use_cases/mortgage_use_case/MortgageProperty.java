/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.mortgage_use_case;

import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;
import game_entities.tiles.RailroadTile;
import game_entities.tiles.UtilityTile;

import java.util.ArrayList;

public class MortgageProperty implements MortgagePropertyInputBoundary{
    
    private final MortgagePropertyOutputBoundary presenter;
    
    //Creates an instance  of the MortgageProperty class with the provided presenter
    public MortgageProperty(MortgagePropertyOutputBoundary presenter){
        this.presenter = presenter;
    }
    
    //Provides a list of properties which can be mortgaged
    @Override
    public void showMortgageOption(Player player){
        ArrayList<Property> properties = player.getProperties();
        ArrayList<Property> mortgageable = new ArrayList<>();
        for(Property property : properties){
            if(property instanceof ColorPropertyTile
                    && ((ColorPropertyTile) property).getNumHouses() == 0
                   && ((ColorPropertyTile) property).getNumHotels() == 0 
                    && !property.isMortgaged()){
                
                mortgageable.add(property);
            }else if(property instanceof RailroadTile
                    || property instanceof UtilityTile
                    && !property.isMortgaged()){
                
                mortgageable.add(property);
            }
        }
        String text = "This is the list of properties which you can mortgage.";
        presenter.showMortgagePropertyList(mortgageable, text);
    }
    
    //Provides a list of properties which can be unmortgaged
    @Override
    public void showUnmortgageOption(Player player){
        ArrayList<Property> properties = player.getProperties();
        ArrayList<Property> unmortgageable = new ArrayList<>();
        
        for(Property property : properties){
            if(property.isMortgaged()){
                unmortgageable.add(property);
            }
        }
        String text = "This is the list of properties which you can unmortgage.";
        presenter.showUnmortgagePropertyList(unmortgageable, text);
    }
    
    //Player mortgages  a color property
    @Override
    public void mortgage(Player player, Property property){
        if(player.ownsProperty(property)
                && property instanceof ColorPropertyTile
                && ((ColorPropertyTile) property).getNumHouses() == 0
                && ((ColorPropertyTile) property).getNumHotels() == 0){
            
            player.addMoney(property.mortgage());
            int mortgageValue = property.getMortgageValue();
            String text = player.getName() +" mortgaged "+ property.getTileDisplayName() + " and get $"+ mortgageValue;
            presenter.showMortgageProperty(player, property, text);
        }else if(player.ownsProperty(property) && property instanceof RailroadTile
                || property instanceof UtilityTile){
            player.addMoney(property.mortgage());
            int mortgageValue = property.getMortgageValue();
            String text = player.getName() + " mortgaged "+ property.getTileDisplayName() + " and get $" + mortgageValue;
            presenter.showMortgageProperty(player, property, text);
        }else{
            String text = player.getName() + " cannot mortgage " + property.getTileDisplayName();
            presenter.showMortgageProperty(player, property, text);
        }
    }
    
    //Player unmortgages property
    @Override
     public void unmortgage(Player player, Property property){
         int unmortgageValue = property.getUnMortgageValue();
         if(player.ownsProperty(property)){
             player.subtractMoney(unmortgageValue);
             property.unmortgage();
             String text = player.getName() +" unmortgaged " + property.getTileName() + " and subtract $" + unmortgageValue;
             presenter.showMortgageProperty(player, property, text);
         }else{
             String text = player.getName() + " cannot unmortgage " + property.getTileName();
             presenter.showUnmortgageProperty(player, property, text);
         }
     }
}
