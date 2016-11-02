package com.excilys.formation.battleships.ship;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class ShipState {
    private AbstractShip ship;
    private boolean struck = false;
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
    /**
     Adds a strike to the ship.
     It is used in order to track if the ship has sunk or not.
     */
    public void addStrike(){
        if(!struck) {
            struck = true;
            if (isShip()) {
                ship.strikeCount++;
            }
        }
    }
    public String toString(){
        if (ship!=null) {
            return ship.getLabel().toString();
        }else {
            return ".";
        }
    }
    /**
     This is useful to populate the table representing the ships without having to convert a String to char each time
     */
    public char getShipAsChar(){
        if (ship==null) {
            return '.';
        }else {
            return ship.getShipType().getAsChar();
        }
    }

}
