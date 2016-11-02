package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.ship.ShipState;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiberiodarferreira on 16/09/2016.
 */
public class Board implements IBoard{
    private int size;
    private Boolean[][] frappes;
    private ShipState[][] navire;
    private String name;
    private Board(String name, int size){
        this.name = name;
        if (size>20){
            System.out.println("Tableau trop grand! Limit = 20");
        }else{
            this.size = size;
        }
        frappes = new Boolean[size][size];
        navire = new ShipState[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                navire[i][j] = new ShipState();
            }
        }
    }

    String getName(){
        return name;
    }

    public Board(String name){
        this(name, 10);
    }

    /**
     Outputs the tables to the screen
     */
    void printTableau(){
        System.out.print("Navires:");
        for (int i=0; i<1 + 2*size; i++) {
            System.out.print(" ");
        }


        System.out.println("Frappes:");
        System.out.print("\t");
        for (char ch = 'A'; ch<(size+'A'); ch++){
            System.out.print( " " + ch);
        }


        System.out.print("\t\t");
        for (char ch = 'A'; ch<(size+'A'); ch++){
            System.out.print( " " + ch);
        }
        System.out.println();
        for (int i = 0; i < size; i++){
            for (int un_deux=0; un_deux<2; un_deux++) { // 0 = navire 1 = frappes
                System.out.print(i);
                System.out.print('\t');
                for (int i_nested1 = 0; i_nested1 < size; i_nested1++) {
                    if (un_deux == 1) {
                        if (frappes[i_nested1][i] == null) {
                            System.out.print(" .");
                        } else if(!frappes[i_nested1][i]) {
                            System.out.print(ColorUtil.colorize(" X", ColorUtil.Color.WHITE));
                        } else if(frappes[i_nested1][i]) {
                            System.out.print(ColorUtil.colorize(" X", ColorUtil.Color.RED));
                        }
                    }else{
                        if(navire[i_nested1][i].isStruck()) {
                            System.out.print(ColorUtil.colorize(" " + navire[i_nested1][i].getShipAsChar(), ColorUtil.Color.RED));
                        }else {
                            System.out.print(" " + navire[i_nested1][i].getShipAsChar());
                        }
                    }
                }
                System.out.print('\t');

            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean hasShip(int x, int y) {
        return (navire[x][y].isShip());
    }


    /**
     @param hit True if hit something, false if missed
     @param x The coordinate x of the hit
     @param y The coordinate y of the hit
     */
    @Override
    public void setHit(boolean hit, int x, int y) {
        if (!isInsideGrid(x, y)){
            System.out.println("Invalid location: x = " + x + " y= " + y);
            return;
        }
        frappes[x][y] = hit;
    }


    /**
     @param x The coordinate x of the hit
     @param y The coordinate y of the hit
     @return True if there is a hit (!=miss) in the coordinates, false if there is not
     */
    @Override
    public Boolean getHit(int x, int y) {
        if (!isInsideGrid(x, y)){
            System.out.println("Invalid location: x = " + x + " y= " + y);
            return false;
        }
        return frappes[x][y];
    }


    /**
     @param x The coordinate x of the hit
     @param y The coordinate y of the hit
     @return A Hit representing what was hit at (x, y)
     */
    public Hit sendHit(int x, int y){
        if (!isInsideGrid(x, y)){
            System.out.println("Invalid location: x = " + x + " y= " + y);
            return null;
        }
        navire[x][y].addStrike();
        if(!navire[x][y].isShip()){
            return Hit.fromInt(-1);
        }else if(navire[x][y].isSunk()) {
            switch (navire[x][y].getShip().getShipType()) {
                case BATTLESHIP:
                    return Hit.fromInt(4);
                case DESTROYER:
                    return Hit.fromInt(2);
                case SUBMARINE:
                    return Hit.fromInt(3);
                case CARRIER:
                    return Hit.fromInt(5);
                default:
                    System.out.println("Holy intervention is preventing this program from working properly.");
                    return null;
            }
        }else{
            return Hit.fromInt(-2);
        }
    }



    /**
     @param ship The ship to be put
     @param x The coordinate x where to put the ship
     @param y The coordinate y where to put the ship
     */
    @Override
    public int putShip(AbstractShip ship, int x, int y) {
        if (!isInsideGrid(x, y)){
            System.out.println("Invalid location: x = " + x + " y= " + y);
            return -1;
        }
        assert ship.getOrientation() != null : "Ship with null orientation being added!";

        int x_temp = x;
        int y_temp = y;
        List<AbstractShip.Location> shipLocation = new ArrayList<>();
        for (int i=0; i < ship.getLength(); i++) {
            AbstractShip.Location Loc = new AbstractShip.Location(x_temp, y_temp);
            shipLocation.add(Loc);
            if (ship.getOrientation() == AbstractShip.Orientation.EAST) {
                x_temp += 1;
            } else if (ship.getOrientation() == AbstractShip.Orientation.WEST) {
                x_temp -= 1;
            } else if (ship.getOrientation() == AbstractShip.Orientation.NORTH) {
                y_temp -= 1;
            } else if (ship.getOrientation() == AbstractShip.Orientation.SOUTH) {
                y_temp += 1;
            }
        }
        for (AbstractShip.Location Loc : shipLocation){
            if (!isInsideGrid(Loc.getX(), Loc.getY())){
                System.out.println("This puts the ship outside the board!");
                return -1;
            }
            if(hasShip(Loc.getX(), Loc.getY())){
                System.out.println("Cannot put ship there, there is already one at X = " + Loc.getX() + " Y = " + Loc.getY());
                return -1;
            }
        }
        for (AbstractShip.Location Loc : shipLocation){
            System.out.println("X = " + Loc.getX() + " Y= " + Loc.getY());
            navire[Loc.getX()][Loc.getY()] = new ShipState(ship);
        }
        printTableau();
        return 1;
    }

    /**
     @param x Coordinate x
     @param y Coordinate y
     @return True if the coordinates are inside the game table, false otherwise
     */
    private boolean isInsideGrid(int x, int y){
        return !((x < 0 || y < 0) || ((x > size - 1 || y > size - 1)));
    }
}


