package com.excilys.formation.battleships.ship;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class ShipState {
    AbstractShip ship;
    boolean struck = false;
    public ShipState(AbstractShip ship){
        this.ship = ship;
    }
    public ShipState(){
        this.ship = null;
    }
    public boolean isShip(){
        return !(ship==null);
    }
    public AbstractShip getShip(){
        return ship;
    }
    public boolean isSunk(){
        return ship.strikeCount==ship.getLength();
    }
    public boolean isStruck(){
        return struck;
    }


    public void addStrike(){
        struck = true;
        if(isShip()) {
            ship.strikeCount++;
        }
    }
    public String toString(){
        if (ship!=null) {
            return ship.getLabel().toString();
        }else {
            return ".";
        }
    }
    public char getShipAsChar(){
        if (ship==null) {
            return '.';
        }else {
            return ship.getShipType().getAsChar();
        }
    }

}
