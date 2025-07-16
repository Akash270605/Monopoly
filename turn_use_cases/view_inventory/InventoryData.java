/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.view_inventory;

import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;
import game_entities.tiles.RailroadTile;
import game_entities.tiles.UtilityTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryData {
    final int money;
    final int getOutofJailFree;
    final ArrayList<Property> properties;
    final String name;
    
    final String icon;
    
    public InventoryData(Player player){
        this.money = player.getMoney();
        this.getOutofJailFree = player.numGetOutofJailFreeCards();
        this.properties = player.getProperties();
        this.name = player.getName();
        this.icon = player.getIcon();
    }
    
    //Adds the cards that the player owns in a set in list and return a List of Lists
    public static List<List<Property>> sortProperties(InventoryData player){
        List<List<Property>> sorted = new ArrayList<>();
        List<Property> railRoadProperties = new ArrayList<>();
        List<Property> utilityProperties = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        
        for(Property x : player.properties){
            if(x instanceof RailroadTile){
                railRoadProperties.add(x);
            }else if(x instanceof UtilityTile){
                utilityProperties.add(x);
            }else{
                ColorPropertyTile thisProperty = (ColorPropertyTile) x;
                String color = thisProperty.getColor();
                if(colors.contains(color)){
                    for(List<Property> name : sorted){
                        ColorPropertyTile sample = (ColorPropertyTile) name.get(0);
                        if(Objects.equals(sample.getColor(), color)){
                            name.add(thisProperty);
                        }
                    }
                }else{
                    colors.add(color);
                    List<Property> newList = new ArrayList<>();
                    newList.add(thisProperty);
                    sorted.add(newList);
                }
            }
        }
        sorted.add(railRoadProperties);
        sorted.add(utilityProperties);
        return sorted;
    }
    
    public String getName(){
        return name;
    }
    
    public int getMoney() { return money; }
    
    public static List<InventoryData> creator(List<Player> playerList){
        List<InventoryData> returnList = new ArrayList<>();
        for(Player player : playerList){
            returnList.add(new InventoryData(player));
        }
        return returnList;
    }
    
    public int getGetOutOfJailFree(){
        return getOutofJailFree;
    }
}
