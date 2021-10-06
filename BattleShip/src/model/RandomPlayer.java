package model;

import java.util.ArrayList;

/**
 * RandomPlayer object, a Player that plays random moves
 */
public class RandomPlayer extends Player{
	
	/** 
     * Default constructor : We call the default constructor of the super class
     */
    public RandomPlayer() {
		super();
	}

	/** 
	 * Constructor with a given name. Calls the super class constructor.
	 * 
     * @param String The name of our player
     */
    public RandomPlayer(String n) {
		super(n);
	}
	
	/** 
	 * Constructor with given name, ships and warzone. Calls the super class constructor
	 * 
     * @param String The name of the player
	 * @param ArrayList<Ship> The ship list of the player
	 * @param Warzone The warzone
     */
	public RandomPlayer(String n, ArrayList<Ship> p, Warzone w) {
		super(n, p, w);
	}

	
	/** 
	 * Chooses a random move among valid moves. 
	 * @param Battle The battle 
	 * @return int
	 */
	@Override
    public int chooseMove(Battle b) {
		//We get the valid moves for the current Player and put them into moves
		ArrayList<Integer> moves = b.validMoves();
        //We get a random index in the ArrayList by casting Math.random*moves.size() to an int
        int move = (int)(Math.random() * moves.size());
        //We set the move as a played move
		this.addMove(moves.get(move));
        //And return it
		return moves.get(move);
	}

}
