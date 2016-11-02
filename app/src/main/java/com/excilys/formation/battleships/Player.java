package com.excilys.formation.battleships;


import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.Board;

import java.io.Serializable;
import java.util.List;

public class Player {
    protected Board board;
    private Board opponentBoard;
    public int destroyedCount;
    protected AbstractShip[] ships;
    public boolean lose;
    private String name;

    public Player(String name, Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.name = name;
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coordenates.
     * Attempts to put the ships in pos x y
     * @param x the x position
     * @param y the y position
     * Is not used in this Android version
     */
    public void putShips(int x, int y) {
        int i = 0;
        boolean done = false;
        do {
            AbstractShip s = ships[i];
            System.out.println("Place your " + s.getLabel());
            InputHelper.ShipInput res = InputHelper.readShipInput();
            AbstractShip.Orientation orientation;
            switch (res.orientation) {
                case "n":
                    orientation = AbstractShip.Orientation.NORTH;
                    break;
                case "s":
                    orientation = AbstractShip.Orientation.SOUTH;
                    break;
                case "e":
                    orientation = AbstractShip.Orientation.EAST;
                    break;
                case "o":
                    orientation = AbstractShip.Orientation.WEST;
                    break;
                default:
                    System.out.println("Not a valid orientation");
                    return;
            }
            s.setOrientation(orientation);
            try {
                board.putShip(s, res.x, res.y);
                ++i;
                done = i == ships.length;
            } catch(Exception e) {
                System.err.println("Impossible de placer le navire a cette position");
                System.out.println(e);
            }
            board.printTableau();

        } while (!done);
    }
    /**
     * Asks the players for coordinates and sends an Hit on opponents table on those coordinates
     * @param coords Variable which should be an int[2] and which will be populated with the
     * coordinates of where the shot was sent
     * @return hit which contains the information about the success of the shot
     */
    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;
        do {
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
                hit = opponentBoard.sendHit(hitInput.x, hitInput.y);
                coords[0] = hitInput.x;
                coords[1] = hitInput.y;
                if (hit != null) {
                    done = true;
                }
        } while (!done);
        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}