package com.excilys.formation.battleships.android.ui;

import com.excilys.formation.battleships.Hit;
import com.excilys.formation.battleships.IBoard;
import com.excilys.formation.battleships.android.ui.ships.DrawableShip;
import com.excilys.formation.battleships.ship.AbstractShip;

import battleships.formation.excilys.com.battleships.R;

public class BoardController implements IBoard {

    /* ***
     * Public constants
     */
    public static final int SHIPS_FRAGMENT = 0;
    public static final int HITS_FRAGMENT = 1;

    /* ***
     * Attributes
     */
    private final IBoard mBoard;
    private final BoardGridFragment[] mFragments;
    private final BoardGridFragment mHitsFragment;
    private final BoardGridFragment mShipsFragment;



    public BoardController(IBoard board) {
        mBoard = board;
        mShipsFragment = BoardGridFragment.newInstance(SHIPS_FRAGMENT, mBoard.getSize(), R.drawable.ships_bg, R.string.board_ships_title);
        mHitsFragment = BoardGridFragment.newInstance(HITS_FRAGMENT, mBoard.getSize(), R.drawable.hits_bg, R.string.board_hits_title);

        mFragments = new BoardGridFragment[] {
            mShipsFragment, mHitsFragment
        };
    }

    public BoardGridFragment[] getFragments() {
        return mFragments;
    }

    public void displayHitInShipBoard(boolean hit, int x, int y) {
        mShipsFragment.putDrawable(hit ? R.drawable.hit : R.drawable.miss, x, y);
    }


    @Override
    public Hit sendHit(int x, int y) {

        return mBoard.sendHit(x,y);
    }

    @Override
    public int getSize() {
        return 10;
    }

    @Override
    /**
     * Attempts to put a ship in pos x y
     * @param ship the ship to be put
     * @param x the x position
     * @param y the y position
     * @throws IllegalArgumentException
     */
    public int putShip(AbstractShip ship, int x, int y) {
        if ((!(ship instanceof DrawableShip))) {
            throw new IllegalArgumentException("Cannot put a Ship that does not implement DrawableShip.");
        }
        if(mBoard.putShip(ship, x, y) != 1){
            throw new IllegalArgumentException("Cannot put a Ship there.");
        }
        if ( ship.getOrientation() == AbstractShip.Orientation.NORTH ){
            y = y - (ship.getLength() - 1);
        }else if(ship.getOrientation() == AbstractShip.Orientation.WEST ){
            x = x - (ship.getLength() - 1);
        }
        System.out.println("Ship length = " + ship.getLength() );
        System.out.println("Putting ship at pos : x = " + x + " y= " + y );
        mShipsFragment.putDrawable(((DrawableShip) ship).getDrawable(), x, y);
        return 0;
    }

    @Override
    public boolean hasShip(int x, int y) {

        return mBoard.hasShip(x,y);
    }

    @Override
    public void setHit(boolean hit, int x, int y) {
        mBoard.setHit(hit, x, y);
        if(hit) {
            mHitsFragment.putDrawable(R.drawable.hit, x, y);
        }else{
            mHitsFragment.putDrawable(R.drawable.miss, x, y);
        }
    }

    @Override
    public Boolean getHit(int x, int y) {

        return mBoard.getHit(x, y);
    }
}
