package com.excilys.formation.battleships.android.ui.ships;

import com.excilys.formation.battleships.ship.Battleship;
import com.excilys.formation.battleships.ship.Carrier;

import java.util.HashMap;
import java.util.Map;

import battleships.formation.excilys.com.battleships.R;

/**
 * Created by tiberiodarferreira on 09/10/2016.
 */

public class DrawableBattleship extends Battleship implements DrawableShip {
    public DrawableBattleship(Orientation orientation){
        super(orientation);
    }
    public DrawableBattleship(){
        super(Orientation.NORTH);
    }
    static final Map<Orientation, Integer> DRAWABLES = new HashMap<>();
    static {
        // le code à l'intérieur de ce bloc sera éxecuté une seule fois au chargement de la classe Example.
        // les opérations effectuées sont donc partagées par toutes les instances de Example.
        DRAWABLES.put(Orientation.NORTH,  R.drawable.battleship_n);
        DRAWABLES.put(Orientation.SOUTH,  R.drawable.battleship_s);
        DRAWABLES.put(Orientation.WEST,  R.drawable.battleship_w);
        DRAWABLES.put(Orientation.EAST,  R.drawable.battleship_e);
    }

    @Override
    public int getDrawable() {
        return DRAWABLES.get(this.getOrientation());
    }
}
