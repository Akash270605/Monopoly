/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities;

import game_entities.cards.Card;
import game_entities.cards.FactoryCard;
import game_entities.tiles.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FactoryBoard {
    
    public static Board boardMaker(String colorPropertyCSV, String utilityPropertyCSV, String railRoadPropertyCSV, 
            String cardCSV) throws FileNotFoundException{
        
        List<ColorPropertyTile> colorProperties = FactoryProperty.initializeColorProperties(colorPropertyCSV);
        List<UtilityTile> utilityProperties = FactoryProperty.initializeUtilityProperties(utilityPropertyCSV);
        List<RailroadTile> railRoadProperties = FactoryProperty.intializeRailRoadProperties(railRoadPropertyCSV);
        List<Tile> orderTileList = order(colorProperties, railRoadProperties, utilityProperties);
        ArrayList<Tile> orderTileArrayList = new ArrayList<>(orderTileList);
        Board board = new Board(orderTileArrayList);
        ArrayList<ArrayList<Card>> cards = FactoryCard.getCards(cardCSV, board);
        cardUpdate(cards, board);
        return board;
    }
    
    //Take in a list of properties and use that to create an ordered ArrayList of tiles, which will include TaxTiles
    //GoToJailTile, FreeParkingTile, GoTile, JailTile ,ChaceTile, CommunityTile
    public static ArrayList<Tile> order(List<ColorPropertyTile> colorProperty, List<RailroadTile> railRoad,
            List<UtilityTile> utility){
        
        ArrayList<Tile> tileList = new ArrayList<>();
        tileList.add(new GoTile());
        tileList.add(colorProperty.get(0));
        tileList.add(new DrawCardTile
        ("Community Chest Tile1", "Community Chest Tile", false));
        tileList.add(colorProperty.get(1));
        tileList.add(new TaxTile("Income Tax", "Income Tax", 200));
        tileList.add(railRoad.get(0));
        tileList.add(colorProperty.get(2));
        tileList.add(new DrawCardTile
        ("Chace Tile1", "Chance Tile", true));
        tileList.add(colorProperty.get(3));
        tileList.add(colorProperty.get(4));
        tileList.add(new JailTile());
        tileList.add(colorProperty.get(5));
        tileList.add(utility.get(0));
        tileList.add(colorProperty.get(6));
        tileList.add(colorProperty.get(7));
        tileList.add(railRoad.get(1));
        tileList.add(colorProperty.get(8));
        tileList.add(new DrawCardTile
                ("Community Chest Tile2", "Community Chest Tile", false));
        tileList.add(colorProperty.get(9));
        tileList.add(colorProperty.get(10));
        tileList.add(new FreeParkingTile());
        tileList.add(colorProperty.get(11));
        tileList.add(new DrawCardTile
                ("Chance Tile2", "Chance Tile", true));
        tileList.add(colorProperty.get(12));
        tileList.add(colorProperty.get(13));
        tileList.add(railRoad.get(2));
        tileList.add(colorProperty.get(14));
        tileList.add(colorProperty.get(15));
        tileList.add(utility.get(1));
        tileList.add(colorProperty.get(16));
        tileList.add(new GoToJailTile());
        tileList.add(colorProperty.get(17));
        tileList.add(colorProperty.get(18));
        tileList.add(new DrawCardTile
                ("Community Chest Tile3", "Community Chest Tile", false));
        tileList.add(colorProperty.get(19));
        tileList.add(railRoad.get(3));
        tileList.add(new DrawCardTile
                ("Chance Tile3", "Chance Tile", true));
        tileList.add(colorProperty.get(20));
        tileList.add(new TaxTile("Luxury Tile", "Luxury Tile", 200));
        tileList.add(colorProperty.get(21));
        return tileList;
    }
    
    //Take in an ArrayList of cards and add them to the board
    public static void cardUpdate(ArrayList<ArrayList<Card>> cards, Board board){
        for(Card chanceCard : cards.get(0)){
            board.addChanceCard(chanceCard);
        }
        for(Card communityCard : cards.get(1)){
            board.addCommunityCard(communityCard);
        }
    }
}
