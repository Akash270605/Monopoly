/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.liquidate_assets_use_case;

import game.GameState;
import game_entities.Player;
import game_entities.tiles.ColorPropertyTile;
import game_entities.tiles.Property;

import java.util.ArrayList;

public class LiquidateAssetsUseCase implements LiquidateAssetsInputBoundary{
    private final LiquidateAssetsOutputBoundary presenter;
    
    public LiquidateAssetsUseCase(LiquidateAssetsOutputBoundary presenter){
        this.presenter = presenter;
    }
    
    @Override
    public void getPlayerOptions(LiquiditySituation situation){
        Player player = situation.getAffectedPlayer();
        int moneyOwed = situation.getOwedMoney();
        ArrayList<String> playerOptions = new ArrayList<>();
        
        if(moneyOwed <= player.getMoney()){
            playerOptions.add("Play Money");
            presenter.showPlayerOptions(playerOptions, situation);
        }else{
            //You can only trade if you have properties to trade with
            if(0 < player.getProperties().size()){
                playerOptions.add("Trade");
            }
            for(int i=0; i<player.getProperties().size(); i++){
                Property property = player.getProperties().get(i);
                if(!property.isMortgaged()){
                    if(property instanceof ColorPropertyTile){
                        int houses = 0;
                        ArrayList<ColorPropertyTile> sameColor = situation.getBoard().getSameColor(((ColorPropertyTile) property).getColor());
                        for(ColorPropertyTile colorPropertyTile : sameColor){
                            houses += colorPropertyTile.getNumHouses();
                        }
                        if(houses == 0){
                            playerOptions.add("Mortgage Property");
                            break;
                        }
                    }else{
                        playerOptions.add("Mortgage Property");
                        break;
                    }
                }
            }
            for(int i=0; i<player.getProperties().size(); i++){
                if(player.getProperties().get(i) instanceof ColorPropertyTile){
                    if(0 < ((ColorPropertyTile) player.getProperties().get(i)).getNumHouses() ||
                            0 < ((ColorPropertyTile) player.getProperties().get(i)).getNumHotels()){
                        
                        playerOptions.add("Sell Houses/Hotels");
                        break;
                    }
                }
            }
            playerOptions.add("Declare Bankruptcy");
            presenter.showPlayerOptions(playerOptions, situation);
        }
    }
    
    @Override
    public void getMortgageableProperties(LiquiditySituation situation){
        Player player = situation.getAffectedPlayer();
        ArrayList<Property> mortgageableProperties = new ArrayList<>();
        
        //Goes through the list of properties the player owns and if it's not mortgaged
        for(int i=0; i<player.getProperties().size(); i++){
            Property property = player.getProperties().get(i);
            if(!property.isMortgaged()){
                if(property instanceof ColorPropertyTile){
                    int houses = 0;
                    ArrayList<ColorPropertyTile> sameColor = situation.getBoard().getSameColor(((ColorPropertyTile) property).getColor());
                    for(ColorPropertyTile colorPropertyTile : sameColor){
                        houses += colorPropertyTile.getNumHouses();
                    }
                    if(houses == 0){
                        mortgageableProperties.add(property);
                    }
                }
                else{
                    mortgageableProperties.add(property);
                }
            }
        }
        presenter.showMortgageableProperties(mortgageableProperties, situation);
    }
    
    @Override
    public void getPropertiesWithHouses(LiquiditySituation situation){
        Player player = situation.getAffectedPlayer();
        ArrayList<ColorPropertyTile> propertiesWithHouses = new ArrayList<>();
        
        //Goes through the list of properties the player owns and if it's not mortgaged it adds the name
        for(int i=0; i<player.getProperties().size(); i++){
            if(player.getProperties().get(i) instanceof ColorPropertyTile){
                ColorPropertyTile propertyTile = (ColorPropertyTile) player.getProperties().get(i);
                if(0 < propertyTile.getNumHouses()){
                    if(propertyDoesNotHaveLessHouses(player, propertyTile)){
                        propertiesWithHouses.add(propertyTile);
                    }
                }
            }
        }
        presenter.showPropertiesWithHouses(propertiesWithHouses, situation);
    }
    
    private static boolean propertyDoesNotHaveLessHouses(Player player, ColorPropertyTile property){
        for(int i=0; i<player.getProperties().size(); i++){
            int propertyHouses = property.getNumHouses() + property.getNumHotels();
            
            //Checks if this property is a color property 
            if(player.getProperties().get(i) instanceof ColorPropertyTile){
                ColorPropertyTile property1  =(ColorPropertyTile) player.getProperties().get(i);
                int property2Houses = property1.getNumHouses() + property1.getNumHotels();
                if(property1.getColor().equals(property.getColor()) &&
                        propertyHouses < property2Houses){
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void bankruptcy(LiquiditySituation situation){
        GameState gameState = situation.getGameState();
        Player bankruptPlayer = situation.getAffectedPlayer();
        Player owedPlayer = situation.getOwedPlayer();
        
        if(owedPlayer == null){
            bankruptPlayer.subtractMoney(bankruptPlayer.getMoney());
            while(0 < bankruptPlayer.getProperties().size()){
                Property property = bankruptPlayer.getProperties().get(0);
                if(property.isMortgaged()){
                    property.unmortgage();
                }
                
                if(property instanceof ColorPropertyTile){
                    ((ColorPropertyTile) property).subtractHotel(((ColorPropertyTile) property).getNumHotels());
                    ((ColorPropertyTile) property).subtractHouse(((ColorPropertyTile) property).getNumHouses());
                }
                property.setOwner(null);
                bankruptPlayer.sellProperty(property);
            }
            gameState.removePlayer(bankruptPlayer);
        }
        else{
            owedPlayer.addMoney(bankruptPlayer.getMoney());
            bankruptPlayer.subtractMoney(bankruptPlayer.getMoney());
            while(0 < bankruptPlayer.getProperties().size()){
                
                //If the owedPlayer inherites a mortgaged property they will have to unmortgage it on their own turn
                Property property = bankruptPlayer.getProperties().get(0);
                property.setOwner(owedPlayer);
                bankruptPlayer.sellProperty(property);
                owedPlayer.addProperty(property);
            }
            gameState.removePlayer(bankruptPlayer);
        }
        presenter.showTransferOfAssets(situation);
    }
}
