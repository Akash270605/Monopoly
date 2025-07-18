/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.liquidate_assets_use_case;

/**
 *
 * @author Leveno
 */
public interface LiquidateAssetsInputBoundary {
    //This gets the possible options of the affectedPlayer in situation
    void getPlayerOptions(LiquiditySituation situation);
    
    //This gets the list of mortgageable properties from affected Player
    void getMortgageableProperties(LiquiditySituation situation);
    
    //This gets the list of properties with houses that are sellable
    void getPropertiesWithHouses(LiquiditySituation situation);
    
    //This implements the affectedPlayer getting bankrupted by the owedPlayer
    void bankruptcy(LiquiditySituation situation);
}
