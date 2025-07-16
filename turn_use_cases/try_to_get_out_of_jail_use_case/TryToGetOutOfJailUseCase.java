/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.try_to_get_out_of_jail_use_case;

import game_entities.Board;
import game_entities.Player;
import turn_use_cases.end_turn_use_case.EndTurnInputBoundary;
import turn_use_cases.move_player_use_case.MovePlayerInputBoundary;
import turn_use_cases.move_player_use_case.MovePlayerOutputBoundary;

import java.util.ArrayList;

public class TryToGetOutOfJailUseCase implements TryToGetOutOfJailInputBoundary{
    private final TryToGetOutOfJailOutputBoundary tryToGetOutOfJailOutputBoundary;
    private Board board;
    private EndTurnInputBoundary endTurnUseCase;
    private final MovePlayerInputBoundary movePlayerUseCase;
    private final MovePlayerOutputBoundary movePlayerOutputBoundary;
    
    public TryToGetOutOfJailUseCase(TryToGetOutOfJailOutputBoundary tryToGetOutOfJailOutputBoundary,
            MovePlayerInputBoundary movePlayerUseCase,
            MovePlayerOutputBoundary movePlayerOutputBoundary){
        
        this.tryToGetOutOfJailOutputBoundary = tryToGetOutOfJailOutputBoundary;
        this.movePlayerUseCase = movePlayerUseCase;
        this.movePlayerOutputBoundary = movePlayerOutputBoundary;
    }
    
    @Override
    public void startAction(String playerOption, Player player){
        System.out.println(playerOption);
        switch(playerOption){
            case "Roll":
                //This is different from movePlayerUseCase as it doesn't take into Account previous double rolls
                int[] playerRollAmount = {(int) (Math.random() * 6) +1, (int) (Math.random() * 6) +1 };
                movePlayerOutputBoundary.showRoll(playerRollAmount);
                if(playerRollAmount[0] == playerRollAmount[1]){
                    //Player rolled a double and are free
                    player.resetTurnInJail();
                    int rollSum = playerRollAmount[0] + playerRollAmount[1];
                    movePlayerUseCase.movePlayer(player, rollSum, false);
                }else{
                    //player didn't roll double, force ending thier turn
                    player.addTurnInJail();
                    tryToGetOutOfJailOutputBoundary.showRoll(playerRollAmount);
                }
                break;
                
            case "Pay":
                player.subtractMoney(50);
                player.resetTurnInJail();
                
                //Normal roll dice move
                movePlayerUseCase.startAction(player, true);
                break;
                
            case "Card":
                player.removeGetOutOfJailCard();
                player.resetTurnInJail();
                
                //Normal roll dice move
                movePlayerUseCase.startAction(player, true);
                break;
        }
    }
    
    @Override
    public void getPlayerOptions(Player player){
        System.out.println(player.numGetOutofJailFreeCards());
        ArrayList<String> playerOptions = new ArrayList<>();
        playerOptions.add("Roll");
        if(player.getMoney() >= 50){
            playerOptions.add("Pay");
        }
        if(player.hasGetOutofJailFreeCard()){
            playerOptions.add("Card");
        }
        tryToGetOutOfJailOutputBoundary.showOptions(player, playerOptions);
    }
}
