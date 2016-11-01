package com.excilys.formation.battleships.ship;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class Destroyer extends AbstractShip {
    public Destroyer(){
        super("", ShipType.DESTROYER, null, 2);
    }
    public Destroyer(Orientation orientation){
        super("", ShipType.DESTROYER, orientation, 2);
    }
}
