/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import game.GameStateOutputBoundary.TurnActions;
import game_entities.Board;
import game_entities.Player;
import turn_interface_adapters.TurnController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameState implements Serializable{
    private final List<Player> allPlayers;
    private final List<Player> activePlayers;
    private final String gameName;
    private final Board board;
    private int numPlayers;
    private transient SaveGameState saveGameState;
    private transient GameStateOutputBoundary presenter;
    private int currentPlayer;
    private int turnCounter;
    private boolean playerAllowedToEndTurn;
    
    public GameState(List<Player> players, String gameName, Board board, SaveGameState saveGameState, GameStateOutputBoundary presenter){
        this.allPlayers = players;
        this.activePlayers  =new ArrayList<>();
        this.currentPlayer = 0;
        this.numPlayers = allPlayers.size();
        this.saveGameState = saveGameState;
        this.turnCounter = 1;
        this.board = board;
        this.gameName = gameName;
        this.presenter = presenter;
        this.playerAllowedToEndTurn = false;
    }
    
    //Return the deserialized GameState object from the ObjectInputStream
    public static GameState deserialize(ObjectInputStream objectIn, SaveGameState saveGameState,
            GameStateOutputBoundary presenter, TurnController turnController) throws ClassNotFoundException, IOException{
        
        GameState gameState = (GameState) objectIn.readObject();
        gameState.setSaveGameState(saveGameState);
        gameState.setPresenter(presenter);
        return gameState;
    }
    
    //Starts the game's first turn
    public void startGame(){
        activePlayers.addAll(allPlayers);
        numPlayers = activePlayers.size();
        
        //shallow method to avoid possible confusion about needing to start each turn by method call every time
        showTurnActions();
    }
    
    //show the valid TurnActions that the player can take
    public void showTurnActions(){
        ArrayList<TurnActions> options = new ArrayList<>();
        
        //Check if the player is allowed to end their turn
        if(playerAllowedToEndTurn){
            options.add(TurnActions.END_TURN);
        }else{
            if(currentPlayer().getTurnsInJail() == -1){
                options.add(TurnActions.ROLL_TO_MOVE);
            }else{
                options.add(TurnActions.LEAVE_JAIL);
            }
        }
        
        //potentially logic to hide building buildings or mortgaging until conditions met
        options.add(TurnActions.BUILD_BUILDING);
        options.add(TurnActions.SELL_BUILDING);
        options.add(TurnActions.MORTGAGE);
        options.add(TurnActions.UNMORTGAGE);
        options.add(TurnActions.TRADE);
        
        presenter.showTurnActions(currentPlayer(), options);
    }
    
    //End the currrent player's turn and handles the logic of switching to the next player's turn
    public void endTurn(){
        nextPlayer();
        boolean saved = saveGameState.save(this, "save_" + gameName);
        presenter.showAutosaveStatus(saved);
        
        //Start the next player's Turn
        presenter.showNextTurn(currentPlayer(), turnCounter);
    }
    
    //Returns whether this GameState is equal to the other object
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        GameState gameState  = (GameState) o;
        return numPlayers == gameState.numPlayers && currentPlayer == gameState.currentPlayer && turnCounter == gameState.turnCounter && playerAllowedToEndTurn == gameState.playerAllowedToEndTurn && allPlayers.equals(gameState.allPlayers) && activePlayers.equals(gameState.activePlayers) && gameName.equals(gameState.gameName);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(allPlayers, activePlayers, gameName, numPlayers, currentPlayer, turnCounter, playerAllowedToEndTurn);
    }
    
    
    //Decide if the player is allowed to keep rolling( primarily if they rolled doubles or not)
    public void playerRolledToMove(){
        //the player must keep rolling if they rolled doubles on their last roll
        playerAllowedToEndTurn = currentPlayer().getConsecutiveDoubles() <= 0
                || currentPlayer().getConsecutiveDoubles() >=3;
     }
    
    //to be called after the player attempts to leave jail
    public void playerAttemptedLeaveJail(){
        playerAllowedToEndTurn = true;
    }
    
    //Return the current turn's player
    public Player currentPlayer(){
        return activePlayers.get(currentPlayer);
    }
    
    //gets a list of all the players in the game, whether they have lost or not
    public List<Player> getAllPlayers(){
        return allPlayers;
    }
    
    //gets a list of all active players( currently in the game)
    public List<Player> getActivePlayers(){
        return activePlayers;
    }
    
    public Board getBoard(){
        return board;
    }
    
    //Return whether the player given has gone bankrupt or not
    public boolean hasLost(Player player){
        return !activePlayers.contains(player);
    }
    
    //Removes the player from the ActivePlayer list , indicating that they have gon bankrupted
    public void removePlayer(Player player){
        activePlayers.remove(player);
        numPlayers = activePlayers.size();
        
        //Compensate for the player removed, Assumed nextPlayer is called right after to do 
        currentPlayer --;
        endTurn();
    }
    
    public void setSaveGameState(SaveGameState saveGameState){
        this.saveGameState = saveGameState;
    }
    
    public void setPresenter(GameStateOutputBoundary presenter){
        this.presenter = presenter;
    }
    
    //Increments private counters and resets turn-based logic
    private void nextPlayer(){
        currentPlayer = (currentPlayer + 1) % numPlayers;
        turnCounter ++;
        playerAllowedToEndTurn = false;
    }
}
