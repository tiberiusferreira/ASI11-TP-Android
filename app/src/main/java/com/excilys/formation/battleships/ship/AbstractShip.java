package com.excilys.formation.battleships.ship;

import com.excilys.formation.battleships.Board;

/**
 * Created by tiberiodarferreira on 23/09/2016.
 */
public class AbstractShip {
    private ShipType shiptype;
    private Orientation orientation;
    private int taille;
    public Location location;
    int strikeCount = 0;
    public static class Location{
        public Location(int x, int y){
            this.x=x;
            this.y=y;
        }
        int x;
        int y;
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }
    public boolean isSunk(){
        return this.getLength()==this.strikeCount;
    }
    AbstractShip(String nom, ShipType shiptype, Orientation orientation, int taille){
        this.taille = taille;
        this.shiptype = shiptype;
        this.orientation = orientation;
    }

    public ShipType getLabel(){
        return this.shiptype;
    }

    public int getLength(){
        return taille;
    }

    public enum Orientation{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    public Orientation getOrientation(){
        return orientation;
    }




    public enum ShipType {
        DESTROYER('D'),
        SUBMARINE('S'),
        BATTLESHIP('B'),
        CARRIER('C');

        Character label;

        ShipType(Character character){
            label = character;
        }
        public char getAsChar() {
            return label;
        }
    }




    public ShipType getShipType(){
        return this.shiptype;
    }
}
