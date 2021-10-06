package vue_controller;

import model.Battle;
/** 
* This class is the main loop of the game
*/
public class Orchestrator {

    /** 
    * A battle object : Contains the whole game
    */
    private Battle battle;
    
    /** 
    * Default constructor : Creates a new Battle object
    */
    public Orchestrator() {
        this.battle = new Battle();
    }

    /** 
     * Constructor with given Battle : Associates as the current battle
     * @param Battle
     */
    public Orchestrator(Battle b) {
        this.battle = b;
    }

    /** 
    * The main loop of the game, while the battle is not over
    */
    //Main loop of the game, while battle is not over
    public void play() {
        while(!(this.battle.isOver())) {
            //Asks the GUI to print the situation
            GUI.displayMessage("It's your turn to play, "+this.battle.getCurrentPlayer().getName());
			GUI.situationToGrid(this.battle.getCurrentPlayer(),this.battle.getOtherPlayer());
            //Ships shots management
			this.battle.fire(this.battle.getCurrentPlayer().chooseMove(this.battle), battle.getOtherPlayer().getWarzone());
            //Checks of the current player's state
            this.battle.getCurrentPlayer().mustLive();
            //And the other player's state
            this.battle.getOtherPlayer().mustLive();
		}
        //If there is a winner, then we print a message to give the winner name
        GUI.situationToGrid(this.battle.getOtherPlayer(),this.battle.getCurrentPlayer());
		GUI.displayMessage("And the winner is : "+ this.battle.getWinner().getName() + " !");
        
    }

}
