/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import game.LoadGameStateSerialize;
import ui.MainMenu;

public class Main {
    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu(new LoadGameStateSerialize("./saves/"));
        mainMenu.show();
    }
}
