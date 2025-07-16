/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turn_use_cases.end_turn_use_case;

import game_entities.Player;

public interface EndTurnOutputBoundary {
    void showResultOfAction(Player player, String flavorText);
}
