/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveGameStateSerialize implements SaveGameState{
    private final String savesDirectory;
    
    public SaveGameStateSerialize(String savesDirectory){
        if(!savesDirectory.endsWith("/")){
            this.savesDirectory = savesDirectory + "/";
        }else{
            this.savesDirectory = savesDirectory;
        }
    }
    
    public String getSavesDirectory(){
        return savesDirectory;
    }
    
    //Save the GameState to a file with the name saveName
    @Override
    public boolean save(GameState gameState, String saveName){
        File savesDirectoryFile = new File(savesDirectory);
        if(!savesDirectoryFile.isDirectory()){
            if(!savesDirectoryFile.mkdir()){
                return false;
            }
        }
        try{
            FileOutputStream fileOut = new FileOutputStream(savesDirectory + saveName + ".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gameState);
            return true;
        }catch(IOException e){
            return false;
        }
    }
}
