package vue_controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


import model.*;

/**
 * Main Class : Where the game begins !
 */
public class Main {

    /**
     * Asks a number to the user
     * @param Scanner The scanner
     * @param String The message to display
     * @return int
     */
    public static int askNumber(Scanner sc, String q) {
        int nombre = 0;
        boolean b = false;
        while (!b) {
            try {
                nombre = 0;
                System.out.println(q);
                nombre = sc.nextInt();
                b = true;
            } catch (InputMismatchException e) {
                System.out.println("You need to input an integer");
            }
        }
        return nombre;
    }

    /**
     * Asks a number to the user, the number being between two ints
     * @param Scanner The scanner
     * @param String The message to display
     * @param int The first limit
     * @param int The second limit
     * @return int
     */
    public static int askNumberWithLimits(Scanner sc, String q, int limit1, int limit2) {
        int nombre = 0;
        boolean b = false;
        while (!b) {
            try {
                nombre = 0;
                System.out.println(q);
                nombre = sc.nextInt();
                if(limit1 <= nombre && nombre <= limit2){
                    b = true;
                }
                else{
                    System.out.println("Please input a valid number");
                }
            } catch (InputMismatchException e) {
                System.out.println("You need to input an integer");
                sc.next();
            }
        }
        return nombre;
    }

    /**
     * Ask the user to confirm
     * @param Scanner The scanner
     * @param String The message to display
     * @return boolean
     */
    public static boolean askConfirmation(Scanner sc, String q) {
        String c = "";
        boolean b = false;
        while (!b) {
            try {
                System.out.println(q);
                c = sc.next();
                if (c.equals("y") || c.equals("n")) {
                    b = true;
                } else {
                    System.out.println("You have to input either 'y' or 'n'");
                }
            } catch (Exception e) {
                System.out.println("You have to input either 'y' or 'n'");
                sc.next();
            }
        }
        if (c.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Asks the user to give an answer among an array of choices
     * @param Scanner The scanner
     * @param String The message
     * @param ArrayList<Integer> The possible choices
     * @return int
     */
    public static int askChoice(Scanner sc, String q, ArrayList<Integer> choices) {
        int nombre = 0;
        boolean b = false;
        while (!b) {
            try {
                nombre = 0;
                System.out.println(q);
                nombre = sc.nextInt();
                if (choices.contains(nombre)) {
                    b = true;
                } else {
                    System.out.println("You need to input an integer corresponding to one of the choices");
                }
            } catch (Exception e) {
                System.out.println("You need to input an integer");
                sc.next();
            }
        }
        return nombre;
    }
    
    
    /** The program starts here, initiates everything before starting the main loop 
     * @param args
     */
    public static void main(String[] args) {

        //Ask whether we play with GUI or in the console
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to BattleShip©®™ !");
        int what2display = askChoice(sc, "Would you like to play ? "+System.lineSeparator()+ "1. In the console ?" + System.lineSeparator()+ "2. Using the graphic interface ?", new ArrayList<Integer>(Arrays.asList(1,2)));
		
        if (what2display == 1){

            //Ask whether the user play against someone else or against our very developed AI that totally definitely does not play completely randomly at all
            int who2play = askChoice(sc, "Would you like to play against :"+System.lineSeparator()+"1. Another human ?"+System.lineSeparator()+"2. A definitely not random-based AI ? ", new ArrayList<Integer>(Arrays.asList(1,2)));
            System.out.println("What's you name ?");
            String p1name = sc.next();
            Player p1 = new Human(p1name);

            //Ask for the P2 name if P2 there is
            Battle b = new Battle();
            b.setFirstPlayer(p1);
            if(who2play == 1){
                System.out.println("What's the second player's name ?");
                String p2name = sc.next();
                b.setSecondPlayer(new Human(p2name));
            }
            
            //Ask for grid size change
            System.out.println("The default grid size is 10x10");
            int grid_width = 10;
            int grid_height = 10;

            //Commented out because we can't handle dynamic grid sizes on the display side
            // if (askConfirmation(sc, "Do you want to change it ? (y/n)")){
                // grid_width = askNumber(sc, "What's the new grid width ?");
                // grid_height = askNumber(sc, "What's the new grid height ?");
                // grid_size = grid_width*grid_height;
            // }

            //Ask each Human to put each of their ships
            //Default ships = 1:5, 1:4, 2:3, 1:2
            Map<String, Integer> ships = Map.of("Carrier", 5, "Battlecruiser", 4, "Submarine", 3, "Battleship", 3, "Destroyer", 2);
            System.out.println("You have 5 ships to place, either horizontally or vertically");
            System.out.println("Here's how you are gonna place them : ");
            System.out.println("We'll ask you for the coordinates of the 2 points at each end of your ship");
            System.out.println("For both coordinates, we'll ask separately for the X and the Y value of each point");
            ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(b.getFirstPlayer(), b.getSecondPlayer()));
            for (Player p : players){
                ArrayList<Ship> shipsToAdd = new ArrayList<Ship>();
                System.out.println("Player Class : " + p.getClass());
                for (Map.Entry<String, Integer> entry : ships.entrySet()){
                    Ship toAdd = new Ship();
                    if (p.getClass() == (new RandomPlayer()).getClass()){
                        boolean randomShipIsCorrect = false;
                        ArrayList<String> coords = new ArrayList<String>();
                        while (!randomShipIsCorrect){
                            coords = new ArrayList<String>();
                            int anchor = (int) Math.floor(Math.random()*9);
                            int floating = (int) Math.floor(Math.random()*9);
                            int end = ((floating+(entry.getValue()-1)) <= 9) ? (floating+(entry.getValue()-1)) : (floating-(entry.getValue()-1));
                            if(Math.random() < 0.5){
                                //is vertical
                                //x1 == x2;
                                for (int i = Math.min(floating, end); i <= Math.max(floating, end); i++) {
                                    coords.add(((Integer) ((grid_width * i) + anchor)).toString());
                                }
                            }
                            else{
                                //is horizontal
                                //y1 == y2;
                                for (int i = Math.min(floating, end); i <= Math.max(floating, end); i++) {
                                    coords.add(((Integer) ((grid_width * anchor) + i)).toString());
                                }
                            }
                            if (coords.size() == entry.getValue()) {
                                // Verify collisions with other ships
                                randomShipIsCorrect = true;
                                for (Ship s : p.getShips()) {
                                    for (String c : s.getCoordinates()) {
                                        if(coords.contains(c)){
                                            randomShipIsCorrect = false;
                                        }
                                    }
                                }
                            }
                            else{
                                randomShipIsCorrect = false;
                            }
                        }
                        toAdd.setCoordinates(coords);
                    }
                    else{
                        boolean inputsAreCorrect = false;
                        while (!inputsAreCorrect){
                            System.out.println(p.getName() + ", let's place your " + entry.getKey());
                            System.out.println("This ship is " + entry.getValue() + " tiles big");
                            System.out.println("Where do you wanna put the first end ?");
                            //Asks for the X and Y value of each end of the ship
                            int x1 = askNumberWithLimits(sc, "First end X coordinate ? (Between 0 and "+(grid_width-1)+")", 0, grid_width-1);
                            int y1 = askNumberWithLimits(sc, "First end Y coordinate ? (Between 0 and "+(grid_height-1)+")", 0, grid_height-1);
                            int x2 = askNumberWithLimits(sc, "Second end X coordinate ? (Between "+Math.max(0, (x1-entry.getValue()+1))+" and "+ Math.min(grid_width, (x1 + entry.getValue() - 1))+")",
                                    Math.max(0, (x1 - entry.getValue() + 1)), Math.min(grid_width, (x1+entry.getValue()-1)));
                            int y2 = askNumberWithLimits(sc, "Second end Y coordinate ? (Between "+Math.max(0, (y1-entry.getValue()+1))+" and "+ Math.min(grid_height, (y1 + entry.getValue() - 1))+")", 
                                    Math.max(0, (y1 - entry.getValue() + 1)), Math.min(grid_height, (y1+entry.getValue()-1)));
                            if (x1 == x2 || y1 == y2){
                                //Determines the coordinates from the inputted points
                                inputsAreCorrect = true;
                                ArrayList<String> coords = new ArrayList<String>();
                                //Determines whether the ship is horizontal or vertical
                                if (x1 == x2){
                                    for (int i = Math.min(y1,y2); i <= Math.max(y1, y2); i++){
                                        coords.add(((Integer) ((grid_width*i)+x1)).toString());
                                    }
                                }
                                else{
                                    for (int i = Math.min(x1,x2); i <= Math.max(x1, x2); i++){
                                        coords.add(((Integer) ((grid_width*y1)+i)).toString());
                                    }
                                }
                                //Verifies the size of the ship
                                if(coords.size() == entry.getValue()){
                                    //Verify collisions with other ships
                                    for (Ship s : p.getShips()){
                                        for (String c : s.getCoordinates()){
                                            if(coords.contains(c)){
                                                inputsAreCorrect = false;
                                                System.out.println("There is a collision with another of your ships !");
                                                System.out.println("Please input other coordinates");
                                            }
                                        }                                
                                    }
                                    toAdd.setCoordinates(coords);
                                }
                                else{
                                    inputsAreCorrect = false;
                                    System.out.println("Your coordinates aren't the size of your ship");
                                    System.out.println("Please input other coordinates");
                                }
                            }
                            else{
                                System.out.println("You can only put ships vertically or horizontally");
                                System.out.println("Please input other coordinates");
                            }
                        }
                    }
                    shipsToAdd.add(toAdd);
                    p.setShips(shipsToAdd);
                    p.getWarzone().fillWarzone(p);
                    System.out.println("Here's what your grid looks like, "+p.getName());
                    if(p.getClass().equals(new Human().getClass())) {
                        GUI.displayWarzone(p);
                    }
                }
            }
            Orchestrator game = new Orchestrator(b);
            game.play();
        }
        else{
            GUI g = new GUI();
            ArrayList<ArrayList<String >>playersData = g.getPlayerName();
            ArrayList<String> p1Data = playersData.get(0);
            ArrayList<String> p2Data = playersData.get(1);
            Player p1;
            Player p2;
            if (p1Data.get(1).equals("true")){//human player
                p1 = new Human(p1Data.get(0));
            }
            else{//random player
                p1 = new RandomPlayer(p1Data.get(0));
            }
            if (p2Data.get(1).equals("true")){//human player
                p2 = new Human(p2Data.get(0));
            }
            else{//random player
                p2 = new RandomPlayer(p2Data.get(0));
            }

            ArrayList<Ship> p1Ships=new ArrayList<Ship>();
            if(p1.getClass().equals(new Human().getClass())) {
                ArrayList<ArrayList<String>> p1Shipscoord=g.setShip(p1.getName());
                for (ArrayList<String> i :p1Shipscoord){
                    Ship toAdd = new Ship();
                    toAdd.setCoordinates(i);
                    p1Ships.add(toAdd);
                }
            }
            else{
                
                ArrayList<ArrayList<String>> p1Shipscoord=g.setShip(p1.getName(),true);
                for (ArrayList<String> i :p1Shipscoord){
                    Ship toAdd = new Ship();
                    toAdd.setCoordinates(i);
                    p1Ships.add(toAdd);
                }
                
            }
            p1.setShips(p1Ships);
            ArrayList<Ship> p2Ships=new ArrayList<Ship>();
            if(p2.getClass().equals(new Human().getClass())) {
                ArrayList<ArrayList<String>> p2Shipscoord=g.setShip(p2.getName());
                for (ArrayList<String> i :p2Shipscoord){
                    Ship toAdd = new Ship();
                    toAdd.setCoordinates(i);
                    p2Ships.add(toAdd);
                }
            }
            else{
                ArrayList<ArrayList<String>> p2Shipscoord=g.setShip(p2.getName(),true);
                for (ArrayList<String> i :p2Shipscoord){
                    Ship toAdd = new Ship();
                    toAdd.setCoordinates(i);
                    p2Ships.add(toAdd);
                }
                
            }
            p2.setShips(p2Ships);
            p1.getWarzone().fillWarzone(p1);
            p2.getWarzone().fillWarzone(p2);
            Battle b = new Battle(p1,p2);
            g.play(b);
        }
    }
}
