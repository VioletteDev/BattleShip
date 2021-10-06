package model;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

/**
 * Player Object, is an abstract class, defines what a player is
 */
public abstract class Player {

	/**
	 * The ships of the Player
	 */
	private ArrayList<Ship> ships;
	/**
	 * The name of the Player
	 */
	private String name;
	/**
	 * The life state of the Player
	 */
	private boolean alive;
	/**
	 * The Warzone of the player
	 */
    private Warzone warzone;
	/**
	 * The played moves
	 */
	private ArrayList<Integer> playedMoves;


	/** 
	 * Default constructor : Generates a random name and default objects
	 */
	public Player() {
		//We create a numbers string that we convert in a characters string if no name is given
		byte[] array = new byte[((new Random().nextInt(8))+1)];
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		this.name = generatedString;
		this.ships = new ArrayList<Ship>();
		this.alive = true;
        this.warzone = new Warzone();
		this.playedMoves = new ArrayList<Integer>();
	}

	/** 
	 * Constructor with a given name
	 * 
	 * @param String Contains the player name
	 */
	//Constructor with a given name
    public Player(String n) {
		this.name = n;
		this.ships = new ArrayList<Ship>();
		this.alive = true;
        this.warzone = new Warzone();
		this.playedMoves = new ArrayList<Integer>();
	}
	
	/** 
	 * Constructor with a given name, ships and warzone
	 * @param String The name
	 * @param ArrayList<Ship> The ships
	 * @param Warzone The warzone
	 */
	//Constructor with a given name, Ships and Warzone
	public Player(String n, ArrayList<Ship> p, Warzone w) {
		this.name = n;
		this.ships = p;
		this.alive = true;
        this.warzone = w;
		this.playedMoves = new ArrayList<Integer>();
	}

	
	/** 
	 * Gets the played moves of the Player
	 * @return ArrayList<Integer>
	 */
	//This method gets the played moves and return them
	public ArrayList<Integer> getPlayedMoves(){
		return this.playedMoves;
	}

	
	/** 
	 * Adds a move to the played moves
	 * @param int Move
	 */
	public void addMove(int move){
		this.playedMoves.add(move);
	}

	
	/** 
	 * Gets the name of the Player
	 * @return String 
	 */
	public String getName() {
		return this.name;
	}
	
	
	/** 
	 * Sets the name of the Player
	 * @param String The name
	 */
	public void setName(String n) {
		this.name = n;
	}
	
	
	/** 
	 * Gets the ships
	 * @return ArrayList<Ship>
	 */
	public ArrayList<Ship> getShips() {
		return this.ships;
	}
	
	
	/** 
	 * Sets the ships
	 * @param ArrayList<Ship> The ships
	 */
	public void setShips(ArrayList<Ship> p) {
		this.ships = p;
	}

	
	/** 
	 * Gets the life state of the player
	 * @return boolean
	 */
	public boolean isAlive() {
		return this.alive;
	}

	/** 
	 * Check if ships must live or not.
	 */
	//This method checks if the ships are alive
	public void mustLive() {
		boolean b = false;
        for(Ship s : this.ships) {
            s.mustLive();
			if(s.isAlive()) {
				b = true;
			}
        }
		//if they're not, alive is false
        if(!b) {
            this.alive = false;
        }
    }

    
	/** 
	 * Gets the Warzone
	 * @return Warzone
	 */
	public Warzone getWarzone() {
        return this.warzone;
    }
	
    
	/** 
	 * Sets the Warzone
	 * @param Warzone The warzone
	 */
	public void setWarzone(Warzone w) {
        this.warzone = w;
    }

	/** 
	 * This is an abstract method, we need it like this because from Player, his childs which are human and randomPlayer gonna use choosemove.
	 */
	public abstract int chooseMove(Battle b);

}
