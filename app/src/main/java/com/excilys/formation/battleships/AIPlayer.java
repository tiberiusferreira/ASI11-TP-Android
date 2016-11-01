package com.excilys.formation.battleships;

import com.excilys.formation.battleships.ship.AbstractShip;

import java.io.Serializable;
import java.util.List;

// This is the AI player which plays against the actual player
public class AIPlayer extends Player {
    private BattleShipsAI ai;
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super("AI", ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
        // Put the ships on creation to make sure it is ready to play
        ai.putShips(ships);
    }
    public Hit sendHit(int[] coords){
        return ai.sendHit(coords);
    }
}