package com.excilys.formation.battleships.ship;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class Battleship extends AbstractShip {
    public Battleship(){
        super("", ShipType.BATTLESHIP, null, 4);
    }
    public Battleship(Orientation orientation){
        super("", ShipType.BATTLESHIP, orientation, 4);
    }

}
