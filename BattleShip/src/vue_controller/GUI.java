package vue_controller;

import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Map;

import model.Player;
import model.Ship;
import model.Battle;
import model.Human;

/** 
* GUI Object : Graphical User Interface
*/
public class GUI extends JFrame implements ActionListener,ComponentListener {
    private JFrame f;
    private JPanel pPlayGround1;
    private JPanel pPlayGround2;
    private JButton buttons1[];
    private JButton buttons2[];
    private ImageIcon hit;
    private ImageIcon notHit;
    private Image shr; 
    private Image hitDScaleImage;
    private ImageIcon ic1;
    private Image notHitDScaleImage;
    private ImageIcon ic2;
    private JLabel P1Name;
    private JLabel P2Name;
    private JButton vert;
    private JButton hori; 
    private JButton reset;
    private JButton done;
    private int movePlayed;//ici (input player)
    private String namePlayer1;
    private String namePlayer2;
    private boolean shipPlacement; 
    private ArrayList<ArrayList<String>> shipList;
    private String currentPlayer;
    private boolean itsDone;
    private JLabel shipPLeft;
    private JLabel winner;
    private boolean playa;
    private JList<String> listShipTPlaced;
    private DefaultListModel<String> DLM;
    private int shipLength;
    private boolean fire;

    private ImageIcon asciiWin;
    private JLabel ShowAscii;
    

    private JTextField TFP1;
    private JTextField TFP2;
    private JButton sendname;
    private boolean goodName;

    
    private JCheckBox p2Human;

    /** 
    * Default Constructor : Creates the game window
    */
    public GUI (){//todo
        String absoluteDirPath = (new File("")).getAbsolutePath() + FileSystems.getDefault().getSeparator();
        this.shipPlacement=false;
        this.f=new JFrame("BattleShip");//Definition of the main window and set the name
        //Set window icon
        this.shr=Toolkit.getDefaultToolkit().getImage(absoluteDirPath+"images/shr.png");
        this.f.setIconImage(shr);
        this.hit=new ImageIcon(absoluteDirPath+"images/hit.png");
        this.notHit=new ImageIcon(absoluteDirPath+"images/ic1.jpg");//Set the icon for not hit ship
        this.asciiWin=new ImageIcon(absoluteDirPath+"images/ErJpUWoW8AkQDtU.png");
        this.ShowAscii=new JLabel();
        //Set the default scaling of hit or notHit icon
        this.hitDScaleImage = hit.getImage().getScaledInstance(5*300/100,5*300/100,Image.SCALE_DEFAULT);
        this.ic1=new ImageIcon(hitDScaleImage);
        //Set the default scaling of hit or notHit icon
        this.notHitDScaleImage = notHit.getImage().getScaledInstance(5*300/100,5*300/100,Image.SCALE_DEFAULT);
        this.ic2=new ImageIcon(notHitDScaleImage);
        this.pPlayGround1=new JPanel();//Set the panel for the playground of the player 1
        this.pPlayGround1.setBackground(Color.gray);//Color of the panel
        this.pPlayGround1.setLayout(new GridLayout(10,10));//Set the layout of the panel
        this.pPlayGround2=new JPanel(); //Set the panel for the playground of the player 2
        this.pPlayGround2.setBackground(Color.gray);//Color of the panel
        this.pPlayGround2.setLayout(new GridLayout(10,10));//Set the layout of the panel

        //Init the label to display the player name in the window
        this.P2Name = new JLabel();
        this.P1Name = new JLabel();
        this.P1Name.setText(namePlayer1);
        this.P2Name.setText(namePlayer2);
        //Init the button use for the ship placement 
        this.hori = new JButton("Horizontal");
        this.hori.addActionListener(this);
        this.vert = new JButton("Vertical");
        this.vert.addActionListener(this);
        this.reset = new JButton("Reset");
        this.reset.addActionListener(this);
        this.done = new JButton("Done");
        this.done.addActionListener(this);
        this.hori.setEnabled(false);//
        this.vert.setEnabled(false);
        this.reset.setEnabled(false);
        this.done.setEnabled(false);
        //Label to display the numbers of ship that need to be placed
        this.shipPLeft=new JLabel();
        this.shipPLeft.setVisible(false);
        //Init the button list used in the playground
        this.buttons1 = new JButton [100];
        this.buttons2 = new JButton [100];
        this.winner = new JLabel();//label used to display the winner name 
        this.winner.setVisible(false);
        //define the chexkbox used for place the diffente ship
        this.DLM = new DefaultListModel<String>(); 
        DLM.addElement("Carrier taille 5");
        DLM.addElement("Battlecruiser taille 4");
        DLM.addElement("Submarine taille 3");
        DLM.addElement("Battleship taille 3");
        DLM.addElement("Destroyer taille 2");
        this.listShipTPlaced = new JList<String>(DLM);
        ShowAscii.setIcon(asciiWin);
        ShowAscii.setVisible(false);
        this.TFP1=new JTextField();
        this.TFP2=new JTextField();
        this.TFP1.setVisible(false);
        this.TFP2.setVisible(false);
        this.sendname=new JButton("Done");
        this.sendname.setVisible(false);
        this.sendname.addActionListener(this);
        this.p2Human=new JCheckBox("player 2 human ? ");
        this.p2Human.setSelected(true);
        this.p2Human.setVisible(false);
        
        //Init all the button of the list, add them to the panel and add an Action Listener
        for (int i=0;i<100;i++){
            this.buttons1[i] = new JButton (); 
            this.buttons2[i] = new JButton  (); 
            pPlayGround1.add(buttons1[i]);
            pPlayGround2.add(buttons2[i]);
            buttons1[i].addActionListener(this);
            buttons2[i].addActionListener(this);
        }
        //Add every component to the main window
        f.add(pPlayGround1);
        f.add(pPlayGround2);
        f.add(P1Name);
        f.add(P2Name);
        f.add(vert);
        f.add(hori);
        f.add(reset);
        f.add(done);
        f.add(shipPLeft);
        f.add(winner);
        f.add(listShipTPlaced);
        f.add(ShowAscii);
        f.add(TFP1);
        f.add(TFP2);
        f.add(sendname);
        f.add(p2Human);
        f.setLayout(null);//Set the layout of the main window to null
        f.addComponentListener(this);//Add an Component Listener to the window to resize every compenent when the window is resized
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height/2);//Set the window size to 1/2 of the screen size 
        f.setVisible(true);//Set the screen to visible 
    }

    /** 
    * Gets the played moves for a player and enables or disables buttons
    * @param String Player's name
    * @return int
    */
    public int getMovePlayed(String PlayerName){


        if(PlayerName.equals(this.namePlayer1)){
            for(int i=0;i<=99;i++){ //Change the grid enable to let the other player play his move 
                buttons1[i].setEnabled(false);
                buttons2[i].setEnabled(true);
            }
        }
        if(PlayerName.equals(this.namePlayer2)){
            for(int i=0;i<=99;i++){ //Change the grid enable to let the other player play his move 
                buttons1[i].setEnabled(true);
                buttons2[i].setEnabled(false);
            }
        }
        fire=false;
        shipPLeft.setVisible(true);
        while(!fire){
            shipPLeft.setText("it's "+PlayerName+" turn");
        }
        fire=false;
        shipPLeft.setVisible(false);
        return this.movePlayed;
    }

    
    /** 
     * ComponentHidden method
     * @param ComponentEvent 
     */
    public void componentHidden(ComponentEvent e) {}
      
    /** 
     * ComponentMoved method
     * @param ComponentEvent 
     */
    public void componentMoved(ComponentEvent e) {}

    /** 
     * ComponentShown method
     * @param ComponentEvent 
     */
    public void componentShown(ComponentEvent e) {}
      
    /** 
     * Method called when the window is resized
     * @param ComponentEvent 
     */
    public void componentResized(ComponentEvent e) {//Call when the window is resize
        Dimension newSize = e.getComponent().getBounds().getSize();//Get the current size of the window
        int icoSize = Math.min(newSize.height,newSize.width)/100;//Determine the min between the height and the width of the screen
        //Create a new icon with the new size(5% of min(heidth,weight) of the window size)
        Image scaleImage = ic1.getImage().getScaledInstance(5*icoSize,5*icoSize,Image.SCALE_DEFAULT);
        ic1=new ImageIcon(scaleImage);
        //Set all the icon in both of the 2 playground
        for(int i=0;i<=99;i++){ 
            if(buttons1[i].getIcon()!=null){  
                buttons1[i].setIcon(ic1);
            }
            if(buttons2[i].getIcon()!=null){  
                buttons2[i].setIcon(ic1);
            }
        }
        pPlayGround1.setBounds(45*(newSize.width-(45*icoSize)*2)/100,10*icoSize,45*icoSize,45*icoSize);//Set the playeground1 panel size
        pPlayGround2.setBounds((55*(newSize.width-(45*icoSize)*2)/100)+45*icoSize,10*icoSize,45*icoSize,45*icoSize);//Set the playeground2 panel size
        P1Name.setBounds(45*(newSize.width-(45*icoSize)*2)/100,0,45*icoSize,10*icoSize);//Set the label of the player 1 size
        P2Name.setBounds((55*(newSize.width-(45*icoSize)*2)/100)+45*icoSize,0,45*icoSize,10*icoSize);//Set the label of the player 2 size
        //Set the size of the ship placement button
        vert.setBounds(55*(newSize.width-(45*icoSize)*2)/100+20*icoSize, 10*icoSize*2+45*icoSize, 20*icoSize, 10*icoSize);
        hori.setBounds((55*(newSize.width-(45*icoSize)*2)/100)+20*icoSize, 10*icoSize*3+45*icoSize, 20*icoSize, 10*icoSize);
        reset.setBounds(45*(newSize.width-(45*icoSize)*2)/100, 10*icoSize*2+45*icoSize, 20*icoSize, 10*icoSize);
        done.setBounds(45*(newSize.width-(45*icoSize)*2)/100, 10*icoSize*3+45*icoSize, 20*icoSize, 10*icoSize);
        shipPLeft.setBounds(45*(newSize.width-(45*icoSize)*2)/100,10*icoSize+45*icoSize ,40*icoSize,10*icoSize);
        //resize the stuff for the winning screen
        Image asciiImg = asciiWin.getImage().getScaledInstance(100*icoSize,100*icoSize,Image.SCALE_DEFAULT);
        asciiWin=new ImageIcon(asciiImg);
        ShowAscii.setIcon(asciiWin);
        winner.setBounds(newSize.width/2-50*icoSize,newSize.height-16*icoSize,40*icoSize,10*icoSize);
        ShowAscii.setBounds(newSize.width/2-60*icoSize,newSize.height/2-60*icoSize,100*icoSize,100*icoSize);
        this.listShipTPlaced.setBounds(55*(newSize.width-(45*icoSize)*2)/100+50*icoSize,10*icoSize*2+40*icoSize,25*icoSize,35*icoSize);//resize the list of ship who need to be placed
        this.TFP1.setBounds(45*(newSize.width-(45*icoSize)*2)/100,10*icoSize,25*icoSize,10*icoSize);
        this.TFP2.setBounds((90*(newSize.width-(45*icoSize)*2)/100),10*icoSize,25*icoSize,10*icoSize);
        this.sendname.setBounds(45*(newSize.width-(45*icoSize)*2)/100, 10*icoSize*3+45*icoSize, 20*icoSize, 10*icoSize);
        this.p2Human.setBounds((90*(newSize.width-(45*icoSize)*2)/100),25*icoSize,25*icoSize,10*icoSize);
    }

    /** 
     * Called when a button is pressed, determines which action must happen
     * @param ActionEvent
     */
    public void actionPerformed(ActionEvent e){//Method used when an action listener is call, like ours buttons
        //Look if the action source is a button from the playground and set movePlayed to the address(int) of the button
        if(shipPlacement==false){
            for(int i=0;i<=99;i++){ 
                if(e.getSource()==buttons1[i]&&buttons1[i].getIcon()==null){
                    this.movePlayed=i;
                    fire=true;
                }
                if(e.getSource()==buttons2[i]&&buttons2[i].getIcon()==null){
                    this.movePlayed=i;
                    fire=true;
                    
                }
            }  
        }


        
        //Part for the ship placement
        else if (shipPlacement==true){//look if we need to place ship
            //If the user want horizontal placement
            if(e.getSource()==hori){
                hori.setEnabled(false);
                vert.setEnabled(true);
                this.listShipTPlaced.setSelectedIndex(0);
            }
            //If the user want vertical placement
            if(e.getSource()==vert){
                hori.setEnabled(true);
                vert.setEnabled(false);
                this.listShipTPlaced.setSelectedIndex(0);
            }
            //Reset the placement
            if(e.getSource()==reset){
                
                this.done.setEnabled(false);
                if(currentPlayer.equals(namePlayer1)){
                    for(int i=0;i<=99;i++){
                        buttons1[i].setEnabled(true);
                        shipList = new ArrayList<ArrayList<String>>(); 
                    }
                }
                else{
                    for(int i=0;i<=99;i++){
                        buttons2[i].setEnabled(true);
                        shipList = new ArrayList<ArrayList<String>>();
                    }
                }
                DLM.removeAllElements();
                DLM.addElement("Carrier taille 5");
                DLM.addElement("Battlecruiser taille 4");
                DLM.addElement("Submarine taille 3");
                DLM.addElement("Battleship taille 3");
                DLM.addElement("Destroyer taille 2");
                this.listShipTPlaced.setSelectedIndex(0);
            }

            if(shipList.size() == 4){//if ship stil need to be placed 
                done.setEnabled(true);
            }
            if(e.getSource()==done){
                itsDone=true;
            }
            //When the user clic on a button of the playground its look if its an horizontal or vertical placement then add the ship coordinate to the arraylist and disable the button 
            if (shipList.size() < 5){
                for(int i=0;i<=99;i++){
                    if(e.getSource() == buttons1[i]||e.getSource() == buttons2[i]){//if we clic on the playground button
                        //set the ship length to the one selected by user
                        if(this.listShipTPlaced.getSelectedValue().equals("Destroyer taille 2")){
                            this.shipLength=2;
                        }
                        if(this.listShipTPlaced.getSelectedValue().equals("Battleship taille 3")||this.listShipTPlaced.getSelectedValue().equals("Submarine taille 3")){
                            this.shipLength=3;
                        }
                        if(this.listShipTPlaced.getSelectedValue().equals("Battlecruiser taille 4")){
                            this.shipLength=4;
                        }
                        if(this.listShipTPlaced.getSelectedValue().equals("Carrier taille 5")){
                            this.shipLength=5;
                        }


                        if (!vert.isEnabled()){//if user select vertical placement
                            //looks if the ship can be placed here
                            boolean notGood=false;
                            if(this.currentPlayer.equals(this.namePlayer1)){
                                if((i+((this.shipLength-1)*10))>=100){
                                    notGood=true;
                                }
                                else{
                                    for (int x=0;x<this.shipLength;x++){
                                        if (!this.buttons1[i+(x*10)].isEnabled()){
                                            notGood=true;
                                        }
                                    }
                                }
                            }
    
                            if(this.currentPlayer.equals(this.namePlayer2)){
                                
                                    
    
                                if((i+((this.shipLength-1)*10))>=100){
                                    notGood=true;
                                }
                                else{
                                    for (int x=0;x<this.shipLength;x++){
                                        if (!this.buttons2[i+(x*10)].isEnabled()){
                                            notGood=true;
                                        }
                                    }
                                }
                            }  

                            //place the ship here
                            if (!notGood){    
                                ArrayList<String> shipCoord = new ArrayList<String>();
                                for (int x=0;x<this.shipLength;x++){
                                    
                                    shipCoord.add(Integer.toString(i+(x*10)));
                                    buttons1[i+(x*10)].setEnabled(false);
                                    buttons2[i+(x*10)].setEnabled(false);
                                    
                                }
                                DLM.remove(this.listShipTPlaced.getSelectedIndex());
                                this.listShipTPlaced.setSelectedIndex(0);
                                shipList.add(shipCoord);

                            }
                        }
                        //If the user want horizontal placement
                        else if(!hori.isEnabled()) { 
                            boolean notGood=false;
                            if(this.currentPlayer==this.namePlayer1){
                                //looks if the ship position is not out of bounds
                                if(i<=9&&i+this.shipLength>9){
                                    notGood=true;
                                }
                                else if(i<=19&&i+this.shipLength-1>19){
                                    notGood=true;
                                }
                                else if(i<=29&&i+this.shipLength-1>29){
                                    notGood=true;
                                }
                                else if(i<=39&&i+this.shipLength-1>39){
                                    notGood=true;
                                }
                                else if(i<=49&&i+this.shipLength-1>49){
                                    notGood=true;
                                }
                                else if(i<=59&&i+this.shipLength-1>59){
                                    notGood=true;
                                }
                                else if(i<=69&&i+this.shipLength-1>69){
                                    notGood=true;
                                }
                                else if(i<=79&&i+this.shipLength-1>79){
                                    notGood=true;
                                }
                                else if(i<=89&&i+this.shipLength-1>89){
                                    notGood=true;
                                }
                                else if(i<=99&&i+this.shipLength-1>99){
                                    notGood=true;
                                }
                                else{
                                    for (int x=0;x<this.shipLength;x++){
                                        if (!this.buttons1[i+x].isEnabled()){
                                            notGood=true;
                                        }
                                    }
                                }
                            }
                            if(this.currentPlayer==this.namePlayer2){
                                if(i<=9&&i+this.shipLength-1>9){
                                    notGood=true;
                                }
                                else if(i<=19&&i+this.shipLength-1>19){
                                    notGood=true;
                                }
                                else if(i<=29&&i+this.shipLength-1>29){
                                    notGood=true;
                                }
                                else if(i<=39&&i+this.shipLength-1>39){
                                    notGood=true;
                                }
                                else if(i<=49&&i+this.shipLength-1>49){
                                    notGood=true;
                                }
                                else if(i<=59&&i+this.shipLength-1>59){
                                    notGood=true;
                                }
                                else if(i<=69&&i+this.shipLength-1>69){
                                    notGood=true;
                                }
                                else if(i<=79&&i+this.shipLength-1>79){
                                    notGood=true;
                                }
                                else if(i<=89&&i+this.shipLength-1>89){
                                    notGood=true;
                                }
                                else if(i<=99&&i+this.shipLength-1>99){
                                    notGood=true;
                                }
                                else{
                                    for (int x=0;x<this.shipLength;x++){
                                        if (!this.buttons2[i+x].isEnabled()){
                                            notGood=true;
                                        }
                                    }
                                }
                            }  
                            if (!notGood){
                                ArrayList<String> shipCoord = new ArrayList<String>();
                                for (int x=0;x<this.shipLength;x++){
                                    shipCoord.add(Integer.toString(i+x));
                                    buttons1[i+x].setEnabled(false);
                                    buttons2[i+x].setEnabled(false);
                                }
                                DLM.remove(this.listShipTPlaced.getSelectedIndex());
                                this.listShipTPlaced.setSelectedIndex(0);
                                shipList.add(shipCoord);
                            }
                        }
                    }
                }
            }
        }



        if(e.getSource()==sendname){
            goodName=true;
        }
    } 

    
    /** 
     * Sets the grid on the interface
     * @param int 
     * @param boolean Tells if a ship was hit or not
     * @param String Player's name
     */
    public void setGrid(int num, boolean hit, String playerName){
        if (playerName.equals(namePlayer1)){//Look which player played this moove
            if (hit==true){//If the moove hit a ship
                buttons2[num].setIcon(ic1);//Set the icon of this moove to the hit icon (ic1)
            }
            else{
                buttons2[num].setIcon(ic2);//Set the icon of this moove to the not hit icon (ic2)
            }

        }
        else if (playerName.equals(namePlayer2)){//Look which player played this moove
            if (hit==true){//If the moove hit a ship
                buttons1[num].setIcon(ic1);//Set the icon of this move to the hit icon (ic1)
            }
            else{
                buttons1[num].setIcon(ic2);//Set the icon of this move to the not hit icon (ic2)
            }
        }
    }

    /** 
     * Shows dead ships ont the GUI
     * @param Player
     */
    public void showDeadShip(Player p2){
        for(Map.Entry<Integer, Ship> set :p2.getWarzone().getBattlefield().entrySet()) {
            if(set.getValue()!=null){
                if (!set.getValue().isAlive()){
                    if(p2.getName().equals(namePlayer1)){
                        buttons1[set.getKey()].setBackground(Color.BLACK);
                    }
                    else{
                        buttons2[set.getKey()].setBackground(Color.BLACK);
                    }
                    this.buttons1[0].doClick();
                }
            }
        }
    }

    
    /** 
     * Sets the ships on the interface
     * @param String The player's name
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> setShip(String PlayerName){//todo enable player grid and disable other one
        
        this.listShipTPlaced.setSelectedIndex(0);
        ShowAscii.setVisible(false);
        this.itsDone = false;
        this.shipPlacement=true;
        shipList=new ArrayList<ArrayList<String>>();
        this.currentPlayer=PlayerName;
        //set the placement components to true except done which be enable when all ships will be placed
        hori.setEnabled(true);
        vert.setEnabled(true);
        reset.setEnabled(true);
        done.setEnabled(false);
        shipPLeft.setVisible(true);
        listShipTPlaced.setVisible(true);
        vert.doClick();
        if (PlayerName.equals(this.namePlayer1)){

            for(int i=0;i<=99;i++){ 
                buttons1[i].setEnabled(true);
                buttons2[i].setEnabled(false);
            }
        }
        if (PlayerName.equals(this.namePlayer2)){
            for(int i=0;i<=99;i++){ 
                buttons1[i].setEnabled(false);
                buttons2[i].setEnabled(true);
            }
        }
        while(!itsDone){//wait the user for select all the ship
            shipPLeft.setText("il vous reste "+Integer.toString(5-shipList.size())+" bateaux a placer");
        }
        this.shipPLeft.setVisible(false);
        this.shipPlacement=false;
        hori.setEnabled(false);
        vert.setEnabled(false);
        reset.setEnabled(false);
        done.setEnabled(false);
        shipPLeft.setVisible(false);
        listShipTPlaced.setVisible(false);
        for(int i=0;i<=99;i++){
            buttons1[i].setEnabled(true);
            buttons2[i].setEnabled(true);
        }
        DLM.removeAllElements();
        DLM.addElement("Carrier taille 5");
        DLM.addElement("Battlecruiser taille 4");
        DLM.addElement("Submarine taille 3");
        DLM.addElement("Battleship taille 3");
        DLM.addElement("Destroyer taille 2");
        return shipList;
    }

    /** 
     * Sets the ships of a Player
     * @param String Player's name
     * @param boolean Tells if the player is Random or Human
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> setShip(String PlayerName,boolean ia){//todo enable player grid and disable other one
        vert.setSelected(true);
        this.listShipTPlaced.setSelectedIndex(0);
        ShowAscii.setVisible(false);
        this.itsDone = false;
        this.shipPlacement=true;
        shipList=new ArrayList<ArrayList<String>>();
        this.currentPlayer=PlayerName;
        //set the placement components to true except done which be enable when all ships will be placed
        hori.setEnabled(true);
        vert.setEnabled(true);
        reset.setEnabled(true);
        done.setEnabled(false);
        shipPLeft.setVisible(true);
        listShipTPlaced.setVisible(true);
        if (PlayerName.equals(this.namePlayer1)){
            for(int i=0;i<=99;i++){ 
                buttons1[i].setEnabled(true);
                buttons2[i].setEnabled(false);
            }
        }
        if (PlayerName.equals(this.namePlayer2)){
            for(int i=0;i<=99;i++){ 
                buttons1[i].setEnabled(false);
                buttons2[i].setEnabled(true);
            }
        }
        
        while(shipList.size()<5){
            if (PlayerName.equals(this.namePlayer1)){
                
                if (Math.random()<0.5){
                    vert.doClick(5);
                    int clicHere = (int)(Math.random() * (100));
                    buttons1[clicHere].doClick(5);
                }
                if (Math.random()>0.5){
                    hori.doClick(5);
                    int clicHere = (int)(Math.random() * (100));
                    buttons1[clicHere].doClick(5);
                }
            }
            if (PlayerName.equals(this.namePlayer2)){
                if (Math.random()<0.5){
                    vert.doClick(5);
                    int clicHere = (int)(Math.random() * (100));
                    buttons2[clicHere].doClick(5);
                }
                if (Math.random()>0.5){
                    hori.doClick(5);
                    int clicHere = (int)(Math.random() * (100));
                    buttons2[clicHere].doClick(5);
                }
            }
        }
        this.shipPLeft.setVisible(false);
        this.shipPlacement=false;
        hori.setEnabled(false);
        vert.setEnabled(false);
        reset.setEnabled(false);
        done.setEnabled(false);
        shipPLeft.setVisible(false);
        listShipTPlaced.setVisible(false);
        for(int i=0;i<=99;i++){
            buttons1[i].setEnabled(true);
            buttons2[i].setEnabled(true);
        }
        DLM.removeAllElements();
        DLM.addElement("Carrier taille 5");
        DLM.addElement("Battlecruiser taille 4");
        DLM.addElement("Submarine taille 3");
        DLM.addElement("Battleship taille 3");
        DLM.addElement("Destroyer taille 2");

        return shipList;
    }

    /** 
    * Gets the player's name at the beggining of the program
    * @return String Player's name
    */
    public ArrayList<ArrayList<String>> getPlayerName(){
        ArrayList<ArrayList<String>> name = new ArrayList<ArrayList<String>>();
        ArrayList<String> p1 =new ArrayList<String>();
        ArrayList<String> p2 =new ArrayList<String>();
        pPlayGround1.setVisible(false);
        pPlayGround2.setVisible(false);
        P1Name.setVisible(false);
        P2Name.setVisible(false);
        vert.setVisible(false);
        hori.setVisible(false);
        reset.setVisible(false);
        done.setVisible(false);
        listShipTPlaced.setVisible(false);
        winner.setVisible(false);
        ShowAscii.setVisible(false);
        

        p2Human.setVisible(true);
        TFP1.setVisible(true);
        TFP2.setVisible(true);
        sendname.setVisible(true);
        sendname.setEnabled(false);

        while(!goodName){
            String namep1=TFP1.getText();
            String namep2=TFP2.getText();
        
            if (!namep2.equals("")&&!namep1.equals("")){
                sendname.setEnabled(true);
            }
        }
        p1.add(TFP1.getText());
        name.add(p1);
        p1.add(String.valueOf(true));
        p2.add(TFP2.getText());
        p2.add(String.valueOf(p2Human.isSelected()));
        name.add(p2);

        this.namePlayer1=TFP1.getText();
        this.namePlayer2=TFP2.getText();

        
        p2Human.setVisible(false);
        TFP1.setVisible(false);
        TFP2.setVisible(false);
        sendname.setVisible(false);

        pPlayGround1.setVisible(true);
        pPlayGround2.setVisible(true);
        P1Name.setVisible(true);
        P2Name.setVisible(true);
        vert.setVisible(true);
        hori.setVisible(true);
        reset.setVisible(true);
        done.setVisible(true);
        listShipTPlaced.setVisible(true);
        winner.setVisible(true);
        ShowAscii.setVisible(false);
        

        P1Name.setText(TFP1.getText());
        P2Name.setText(TFP2.getText());

        return name;
    }

    /** 
     * Shows the winner screen
     * @param String
     * @return boolean 
     */
    public boolean winner(String winnerName){//Make all the game stuff not visible to display the winner name 
        pPlayGround1.setVisible(false);
        pPlayGround2.setVisible(false);
        P1Name.setVisible(false);
        P2Name.setVisible(false);
        vert.setVisible(false);
        hori.setVisible(false);
        reset.setVisible(false);
        done.setVisible(false);
        listShipTPlaced.setVisible(false);
        
        
        this.winner.setVisible(true);
        this.ShowAscii.setVisible(true);
        while(!playa){
            this.winner.setText(winnerName+" won the game!!!");
        }
        return true;
    }

    /** 
     * Displays the Warzone in the console, used when the player did place his ships
     * @param Player
     */
    public static void displayWarzone(Player p){
        String str = "";
        for(Map.Entry<Integer, Ship> set : p.getWarzone().getBattlefield().entrySet()){
            if(set.getKey() % 10 == 0){
                str += System.lineSeparator();
            }
            if(set.getValue() == null){
                str += " . ";
            }
            else{
                str += " X ";
            }
        }
        System.out.println(str);
    }

    
    /** 
     * Shows (in the console) the current state of the game
     * @param Player The current player
     * @param Player His opponent 
     */
    //This method gives colors to the game elements and the state of the actual game
    public static void situationToGrid(Player currentPlayer, Player opponentPlayer){
        //That loop is used to browse all the elements of the game and give them colors
        String str = currentPlayer.getName() + "'s grid : "+System.lineSeparator();
        for(Map.Entry<Integer, Ship> set : currentPlayer.getWarzone().getBattlefield().entrySet()) {
            if(set.getKey() % 10 == 0) {
                System.out.println(str);
                str = "";
            }
            if (set.getValue() == null) {
                if (opponentPlayer.getPlayedMoves().contains(set.getKey())) {
                    str += " \033[0;31m! ";
                } else {
                    str += " \033[0;0m. ";
                }
            } else {
                if(!set.getValue().isAlive()) {
                    str += " \033[0;36mO ";
                }
                else if (opponentPlayer.getPlayedMoves().contains(set.getKey())) {
                    str += " \033[0;32mO ";
                } 
                else {
                    str += " \033[0;35mX ";
                }
            }
        }
        System.out.println(str+System.lineSeparator());

        str = "Your opponent's grid :"+System.lineSeparator();
        for(Map.Entry<Integer, Ship> set : opponentPlayer.getWarzone().getBattlefield().entrySet()){
            if (set.getKey() % 10 == 0){
                System.out.println(str);
                str = "";
            }
            if (set.getValue() == null){
                if (currentPlayer.getPlayedMoves().contains(set.getKey())){
                    str += " \033[0;31m! ";
                }
                else{
                    str += " \033[0;0m. ";
                }
            }
            else{
                if(!set.getValue().isAlive()) {
                    str += " \033[0;32mO ";
                }
                else if (currentPlayer.getPlayedMoves().contains(set.getKey())){
                    str += " \033[0;35mX ";
                }
                else{
                    str += " \033[0;0m. ";
                }
            }
        }
        System.out.println(str+System.lineSeparator());
    }

    /** 
     * Prints the winner on the console
     * @param Player The winner 
     */
    public static void printWinner(Player p){
        System.out.println(p.getName()+" won!!!!");
    }

    public void play(Battle b) {

        while(!(b.isOver())) {

           Player currentPlayer =  b.getCurrentPlayer();

           int movePlayed;

            if (b.getCurrentPlayer().getClass() == (new Human()).getClass()){
                movePlayed=this.getMovePlayed(b.getCurrentPlayer().getName());
            }
            else{
                movePlayed=b.getCurrentPlayer().chooseMove(b);
            }
            this.setGrid(movePlayed, b.fire(movePlayed,b.getOtherPlayer().getWarzone()),currentPlayer.getName());

            //Ships shots management
            
            //show dead ship
            this.showDeadShip(currentPlayer);
            //Checks of the current player's state
            b.getCurrentPlayer().mustLive();
            //And the other player's state
            b.getOtherPlayer().mustLive();
		}
        //If there is a winner, then we print a message to give the winner name
        this.winner(b.getWinner().getName());
    }

    /**
     * Displays a given message in the console
     * @param String The message to display
     */
    public static void displayMessage(String s) {
        System.out.println(s);
    }

}
