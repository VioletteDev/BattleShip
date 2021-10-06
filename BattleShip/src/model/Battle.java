package model;

import java.util.ArrayList;
/** 
     * Battle Object : Contains the whole game
     */
public class Battle {
    
    /** 
     * Introduction of the variable of type Player: player1
     */
    private Player player1;

    /** 
     * Introduction of the variable of type Player: player2
     */
    private Player player2;

    /** 
     * Introduction of the variable of type Player: currentPlayer
     */
    private Player currentPlayer;

    /** 
     * Default constructor : We create two RandomPlayer, kept in the two variables player1 and player2
     */
    public Battle() {
        this.player1 = new RandomPlayer();
        this.player2 = new RandomPlayer();
        this.currentPlayer = this.player1;
    }

    /** 
     * Constructor with players : Associate both Player attributes to the Players given 
     * 
     * @param Player The first player
     * @param Player The second player
     */
    public Battle(Player p, Player p2) {
        this.player1 = p;
        this.player2 = p2;
        this.currentPlayer = p;
    }

    
    /** 
     * This method takes the first player and returns it
     * 
     * @return Player
     */
    public Player getFirstPlayer() {
        return this.player1;
    }

    
    /** 
     * This method takes the second player and returns it
     * 
     * @return Player
     */
    public Player getSecondPlayer() {
        return this.player2;
    }

    
    /** 
     * This method takes the current player and returns it
     * 
     * @return Player
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    
    /** 
     * This method takes the other player (not current) and returns it
     * 
     * @return Player
     */
    public Player getOtherPlayer() {
        if(this.currentPlayer.equals(this.player1)) {
            return this.player2;
        }
        return this.player1;
    }

    /** 
     * Sets the player1 to a Player object
     * 
     * @param Player The first player 
     */
    public void setFirstPlayer(Player p) {
        this.player1 = p;
    }

    /** 
     * Sets the player2 to a Player object
     * 
     * @param Player The second player 
     */
    public void setSecondPlayer(Player p) {
        this.player2 = p;
    }

    
    /** 
     * This method sets the current player to the given Player object
     *
     * @param Player The current player
     */
    public void setCurrentPlayer(Player p) {
        this.currentPlayer = p;
    }

    
    /** 
     * This incredible method checks all the possible moves and put them in an array
     * 
     * @return ArrayList<Integer>
     */
    //This method check the possible moves of the current player
    public ArrayList<Integer> validMoves() {
        //we create an ArrayList
        ArrayList<Integer> a = new ArrayList<Integer>();
        //We browse through the grid to search the possible moves 
        for(int i = 0 ; i < this.getOtherPlayer().getWarzone().getBattlefield().size() ; i++) {
            if(!this.currentPlayer.getPlayedMoves().contains(i)) {
                //the possible moves are added to our a array list
                a.add(i);
            }
        }
        //We return our ArrayList
        return a;
    }

    
    /** 
     * In the fire method we manage the shots of the ships. To proceed, we use the coordinate of the ships on the game grid and the coordinate choosed by the player.
     * Then if the shot hits a ship, the coordinate of where the ship is hit is removed from the ships coordinates.
     * 
     * @param int The position of the ships
     * @param Warzone The battlefield
     * @return boolean
     */
    //This method manages the shots of the ships 
    public boolean fire(int coordinate, Warzone zone) {
        //We take the coordinate of the ship
        Ship s = zone.getBattlefield().get(coordinate);
        this.setCurrentPlayer(this.getOtherPlayer());
        if(s != null) {
            //If our shot hits a ship on the chosen coordinate, we remove this coordinate from the ship
            s.removeCoordinate(String.valueOf(coordinate));
            return true;
        }
        else {
            return false;
        }
    }

    
    /** 
     * This method manages if the game is over or not, if both players are still alive the game is not over
     * 
     * @return boolean
     */
    //This method run the game while both players are alive
    public boolean isOver() {
        //If a player is dead, then we return true and the game will stop
        if(!this.player1.isAlive() || !this.player2.isAlive()) {
            return true;
        }
        //If they are both alive the game continue and isOver() is still false
        return false;
    }
    
    /** 
     * This method, gives the winner. Basicly, this game have a single winner, we check which Player is alive. This method is called when the game is over.
     * 
     * @return Player
     */
    //This method defines the winner of the game
    public Player getWinner() {
        //If player 1 is alive, then he is the winner
        if(this.player1.isAlive()) {
            return player1;
        }
        else {
            //And if its the player 2, then he is the winner
            return player2;
        }
    }

}