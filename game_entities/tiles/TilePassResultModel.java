/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_entities.tiles;

/**
 *
 * @author Leveno
 */
public class TilePassResultModel {
    private final boolean actionTaken;
    private final String flavorText;
    
    public TilePassResultModel(boolean actionTaken, String flavorText){
        this.actionTaken = actionTaken;
        this.flavorText = flavorText;
    }
    
    public boolean isActionTaken(){
        return actionTaken;
    }
    
    public String getFlavorText(){
        return flavorText;
    }
}
