package com.excilys.formation.battleships.android.ui.ships;

import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;

import com.excilys.formation.battleships.android.ui.BoardGridFragment;
import com.excilys.formation.battleships.ship.Destroyer;

import java.util.HashMap;
import java.util.Map;

import battleships.formation.excilys.com.battleships.R;

/**
 * Created by tiberiodarferreira on 06/10/2016.
 */

public class DrawableDestroyer extends Destroyer implements DrawableShip {
    public DrawableDestroyer(Orientation orientation){
        super(orientation);
    }
    public DrawableDestroyer(){
        super(Orientation.NORTH);
    }
    static final Map<Orientation, Integer> DRAWABLES = new HashMap<>();
    static {
        // le code à l'intérieur de ce bloc sera éxecuté une seule fois au chargement de la classe Example.
        // les opérations effectuées sont donc partagées par toutes les instances de Example.
        DRAWABLES.put(Orientation.NORTH,  R.drawable.destroyer_n);
        DRAWABLES.put(Orientation.SOUTH,  R.drawable.destroyer_s);
        DRAWABLES.put(Orientation.WEST,  R.drawable.destroyer_w);
        DRAWABLES.put(Orientation.EAST,  R.drawable.destroyer_e);
    }

    @Override
    public int getDrawable() {
        return DRAWABLES.get(this.getOrientation());
    }
}
