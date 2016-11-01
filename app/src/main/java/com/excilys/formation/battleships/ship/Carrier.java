package com.excilys.formation.battleships.ship;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class Carrier extends AbstractShip {
    public Carrier(){
        super("", ShipType.CARRIER, null, 5);
    }
    public Carrier(Orientation orientation){
        super("", ShipType.CARRIER, orientation, 5);
    }
}
