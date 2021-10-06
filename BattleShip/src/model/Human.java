package model;

import java.util.ArrayList;
import java.util.Scanner;

/** 
* Human Object : Represents a human player
*/
public class Human extends Player{
	
	/** 
     * We use the scanner to ask move to the human player
     */
    private Scanner scanner;

	/** 
     * Default constructor : We call the default constructor of the super class and adds a Scanner
     */
	public Human() {
		super();
		this.scanner = new Scanner(System.in);
	}

	/** 
	 * Constructor with a given name. Calls the super class constructor and adds a Scanner.
	 * 
     * @param String The name of our player
     */
    public Human(String n) {
		super(n);
		this.scanner = new Scanner(System.in);
	}
	
	/** 
	 * Constructor with given name, ships and warzone. Calls the super class constructor and adds a Scanner.
	 * 
     * @param String The name of the player
	 * @param ArrayList<Ship> The ship list of the player
	 * @param Warzone The warzone
     */
	public Human(String n, ArrayList<Ship> p, Warzone w) {
		super(n, p, w);
		this.scanner = new Scanner(System.in);
	}

	/** 
	 * Asks a number the the human
	 * 
     * @param String The human's input
	 * @return int
     */
	//Method used in chooseMove to ask numbers
	private int askNumber(String q){
		int nombre = 0;
		boolean bool = false;
		while (!bool) {
			try {
				System.out.println(q);
				nombre = this.scanner.nextInt();
				if (0 <= nombre && nombre <= 9) {
					bool = true;
				}
			} catch (Exception e) {
				System.out.println("You need to input a valid move");
				this.scanner.next();
			}
		}
		return nombre;
	}

	/** 
	 * ChooseMove asks to the human a move
	 * 
	 * @param Battle The battle object : our game 
	 * @return int
	 */
	@Override
    public int chooseMove(Battle b) {
		//This method will ask the player to make a move
		boolean moveIsValid = false;
		int move = 0;
		while(!moveIsValid){
			int moveX = askNumber("Input the X value of your next hit (between 0 and 9):");
			int moveY = askNumber("Input the Y value of your next hit (between 0 and 9):");
			move = (moveY*10)+moveX;
			if(b.validMoves().contains(move)){
				this.addMove(move);
				moveIsValid = true;
			}
		}
		return move;
	}
}
