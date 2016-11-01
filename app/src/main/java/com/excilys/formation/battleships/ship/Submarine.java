package com.excilys.formation.battleships.ship;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class Submarine extends AbstractShip {
    public Submarine(){
        super("", ShipType.SUBMARINE, null, 3);
    }
    public Submarine(Orientation orientation){
        super("", ShipType.SUBMARINE, orientation, 3);
    }
}
