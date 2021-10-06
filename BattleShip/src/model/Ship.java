package model;

import java.util.ArrayList;

/**
 * Ship object, represents a ship 
 */
public class Ship {
    
    /**
     * The life state of the Ship
     */
    private boolean alive;
    /**
     * The coordinates of the Ship
     */
    private ArrayList<String> coordinates;

    /**
     * Default constructor : Sets an empty ArrayList as corrdinates and sets the ship as alive
     */
    //Here are created the ships, they have a coordinate and they are alive
    public Ship() {
        this.coordinates = new ArrayList<String>();
        this.alive = true;
    }
    
    /**
     * Constructor with given coordinates : sets the coordinates and sets the ship as alive
     * @param ArrayList<String> The coordinates of the ship
     */
    public Ship(ArrayList<String> co) {
        this.coordinates = co;
        this.alive = true;
    }

    
    /** 
     * Gets the life state of the Ship
     * @return boolean
     */
    public boolean isAlive() {
        return this.alive;
    }

    /** 
     * Checks if the ship must live, if not it's life state is set to false
     */
    //We check if the ships are alive by testing if there is at least one coordinate left
    public void mustLive() {
        if(this.coordinates.isEmpty()) {
            //If coordinates are empty, then the ship is dead so is alive is false
            this.alive = false;
        }
    }

	
    /** 
     * Gets the coordinates of the Ship
     * @return ArrayList<String>
     */
    public ArrayList<String> getCoordinates(){
		return this.coordinates;
	}
	
	
    /** 
     * Sets the coordinates of the ship
     * @param ArrayList<String> The coordinates
     */
    public void setCoordinates(ArrayList<String> co){
		this.coordinates = co;
	}

    
    /** 
     * Removes a coordinate
     * @param String The removed coordinate
     */
    public void removeCoordinate(String s) {
        this.coordinates.remove(s);
    }

    
    /** 
     * Returns a String defining the Object
     * @return String
     */
    public String toString() {
        String str = "";
        for(String i : this.coordinates) {
            str += i + ",";
        }
        return "Ship with coordinates of " + str + " and is " + this.alive + " alive.";
    }

}
