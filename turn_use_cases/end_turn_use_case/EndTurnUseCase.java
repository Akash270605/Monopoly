/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.end_turn_use_case;

import game.GameState;
import game_entities.Player;

public class EndTurnUseCase implements EndTurnInputBoundary{
    
    private final GameState gameState;
    
    public EndTurnUseCase(EndTurnOutputBoundary endTurnOutputBoundary, GameState gameState){
        this.gameState = gameState;
    }
    
    @Override
    public void endTurn(Player player){
        gameState.endTurn();
    }
    
    @Override
    public void forceEndTurn(Player player){
        gameState.endTurn();
    }
}
