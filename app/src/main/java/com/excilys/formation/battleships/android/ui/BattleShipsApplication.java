package com.excilys.formation.battleships.android.ui;

import android.app.Application;
import android.content.Intent;

import com.excilys.formation.battleships.AIPlayer;
import com.excilys.formation.battleships.Board;
import com.excilys.formation.battleships.Player;
import com.excilys.formation.battleships.android.ui.ships.DrawableBattleship;
import com.excilys.formation.battleships.android.ui.ships.DrawableCarrier;
import com.excilys.formation.battleships.android.ui.ships.DrawableDestroyer;
import com.excilys.formation.battleships.android.ui.ships.DrawableSubmarine;
import com.excilys.formation.battleships.ship.AbstractShip;

import java.util.Arrays;
import java.util.List;

public class BattleShipsApplication extends Application {

    /* ***
     * Attributes
     */
    // BoardController = Implements the visual part of the Board on top of the Board logic
    private static BoardController mBoardcontroller;
    // The opponent Board, at first it will just be an AI, but could be another player over network
    private static Board mOpponentBoard;
    // The game object itself created during the start of the application by the
    // onCreate method
    private static Game mGame;
    // Stores the player's name
    private String mPlayerName;
    // Stores the the players
    private static Player[] mPlayers;

    public BoardController getBoardController(){
        return mBoardcontroller;
    }

    static public Board getOpponentBoard(){
        return mOpponentBoard;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGame = new Game();
    }

    public static Game getGame() {
        return mGame;
    }

    public static BoardController getBoard() {
        return mBoardcontroller;
    }

    public static Player[] getPlayers() {
        return mPlayers;
    }

    /* ***
     * Nested classes
     */
    public class Game {
        /* ***
         * Attributes
         */
        private AndroidPlayer mPlayer1;
        private Player mPlayer2;
        /* ***
         * Methods
         */
        public Game init(String playerName) {
            mPlayerName = playerName;
            Board board = new Board(playerName);
            mBoardcontroller = new BoardController(board);
            mOpponentBoard = new Board("IA");
            // Android Player is the "regular player", but the putShips opens an Android Activity
            // so the ships can be put using a nice interface
            mPlayer1 = new AndroidPlayer(playerName, board, mOpponentBoard, createDefaultShips());
            mPlayer2 = new AIPlayer(mOpponentBoard, board, createDefaultShips());
            mPlayers = new Player[] {mPlayer1, mPlayer2};
            // Show the putShips view and wait for the player to put it's ships.
            mPlayer1.putShips();
            return this;
        }

        private List<AbstractShip> createDefaultShips() {
            AbstractShip[] ships;
             ships = new AbstractShip[]{new DrawableDestroyer(), new DrawableSubmarine(), new DrawableSubmarine(), new DrawableBattleship(), new DrawableCarrier()};
            return Arrays.asList(ships);
        }
    }

    public class AndroidPlayer extends Player {
        // Just a regular "Player" class apart from the putShips method which is overloaded
        AndroidPlayer(String name, Board board, Board opponentBoard, List<AbstractShip> ships){
            super(name, board, opponentBoard, ships);
        }
        void putShips() {
            Intent intent = new Intent(getApplicationContext(), PutShipsActivity.class);
            startActivity(intent);
        }

    }

}
