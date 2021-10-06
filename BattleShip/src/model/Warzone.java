package model;

import java.util.HashMap;

/** 
     * A Warzone object, contains the player's grid and can set it with ships
     */
public class Warzone {
    
    /**
     * The player's grid
     */
    private HashMap<Integer, Ship> battlefield;

    /**
     * Default constructor : Initiate the warzone to the size 100
     */
    public Warzone() {
        this.battlefield = Warzone.initWarzone(100);
    }
    
    /** 
     * Gets the grid
     * @return HashMap<Integer, Ship>
     */
    public HashMap<Integer, Ship> getBattlefield() {
        return this.battlefield;
    }

    
    /** 
     * Sets the grid
     * @param HashMap<Integer, Ship> The grid
     */
    public void setBattlefield(HashMap<Integer, Ship> b) {
        this.battlefield = b;
    }

    
    /** 
     * Static method, initiates the Warzone to the given size, filling a HashMap with null objects
     * @param zoneSize The given size
     * @return HashMap<Integer, Ship>
     */
    public static HashMap<Integer, Ship> initWarzone(int zoneSize) {
        int hashSize = (int) ((zoneSize/0.75) + 1);
        HashMap<Integer, Ship> grid = new HashMap<Integer, Ship>(hashSize, 0.75f);
        //We fill the keys
        for(int i = 0; i < zoneSize; i++){
            grid.put(i, null);
        }
        return grid;
    }
    
    
    /** 
     * Fills the Warzone with the Player's ships
     * @param Player The player
     */
    //This method fill the warzone with the player's ships
    public void fillWarzone(Player player) {
        for(Ship s : player.getShips()) {
            for(int i=0; i < s.getCoordinates().size(); i++) {
                this.battlefield.put(Integer.parseInt(s.getCoordinates().get(i)), s);
            }
        }
    }
    
}
