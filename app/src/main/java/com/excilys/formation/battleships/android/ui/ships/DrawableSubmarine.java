package com.excilys.formation.battleships.android.ui.ships;

import com.excilys.formation.battleships.ship.Submarine;

import java.util.HashMap;
import java.util.Map;

import battleships.formation.excilys.com.battleships.R;

/**
 * Created by tiberiodarferreira on 06/10/2016.
 */

public class DrawableSubmarine extends Submarine implements DrawableShip {
    public DrawableSubmarine(Orientation orientation){
        super(orientation);
    }
    public DrawableSubmarine(){
        super(Orientation.NORTH);
    }
    static final Map<Orientation, Integer> DRAWABLES = new HashMap<>();
    static {
        // The code inside here will be executed just once when the class is loaded
        // So all the changes here are shared with the "brother" classes
        DRAWABLES.put(Orientation.NORTH,  R.drawable.submarine_s);
        DRAWABLES.put(Orientation.SOUTH,  R.drawable.submarine_n);
        DRAWABLES.put(Orientation.WEST,  R.drawable.submarine_w);
        DRAWABLES.put(Orientation.EAST,  R.drawable.submarine_e);
    }

    @Override
    public int getDrawable() {
        return DRAWABLES.get(this.getOrientation());
    }
}
