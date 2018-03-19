/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleShip {
    char[][] GameState = new char[10][10];
    //player inputs
    boolean response = true;
    String playerName = " ";
    String aiName = " ";
    int xCor= 0;
    int yCor= 0;
    Scanner sc = new Scanner(System.in);    
    public BattleShip(){
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        boolean doYouWantToPlay = true;
        while(doYouWantToPlay){
             BattleShip game = new BattleShip();
             game.response = true;
             //initialise the game fills the array with empyt space
            game.initialiseGame();
            //Print game state
            game.printGameState();
            //Give user tip on the game
            game.startUpInstruction();
            //initialize Player
            game.setGame();
            Player player = new Player(game.playerName,game.GameState);
            //initialize AI
            Computer ai = new Computer(game.aiName, game.GameState);
            //The user deploys ships
             game.promptUser(player);
             /*
              If the user wants to quit the game while he was depoying ships
             
             */
            if(game.response){
                game.GameState = ai.deployShip(game.GameState);
                game.printGameState();
            }
            else{
                break;
            }
            //get the user attack coordinates
            int x=0,y=0;
            //while the computer and player still has battle ships left
            while(!game.GameOver(player, ai)){
                boolean read = true;
                while(read){
                    try{
                            System.out.println("Enter X1 cordinate, enter y to quit");
                            x = game.sc.nextInt();
                            System.out.println("Enter Y1 cordinate, enter y to quit");
                            y = game.sc.nextInt();
                            read = false;
                
                    }catch(InputMismatchException ex){
                        read = true;
                        char res = game.sc.nextLine().charAt(0);
                        if(res == 'Y' || res == 'y'){
                            System.out.println("Game exited");
                            game.response = false;
                            break;
                        }
                        else{
                            //resets the scanner object
                            System.out.println("Please Enter Integer");
                            game.sc = new Scanner(System.in);
                            //decrement i so the user can enter inputs again
                        }
                    }
                    
                }
                /*
                 If computer still has ships let, attack the computer
                 using the cordinates given by the user;
                */
                if(ai.numberOfShip > 0){
                    game.GameState = player.shotEnemy(game.GameState, x, y);
                    //Check if attack is successful, if it is, reduce the computer ship by 1
                    if(player.attackOutcome.equalsIgnoreCase("T")){
                        ai.numberOfShip--;
                    }
                }
                /*
                    If player still has ships, the computer will attack the player
                */
                game.printGameState();
                System.out.println("Computer\'s Turn");
                
                if(player.numberOfShip > 0){
                   game.GameState = ai.shotEnemy(game.GameState);
                   //if attack is successful, reduce the player ship by one
                    if(ai.attackOutCome.equalsIgnoreCase("T")){
                        player.numberOfShip--;
                    } 
                }
                game.printGameState();
            }
            //gives the user the opportunity to end the game or to play new game after wining or loosing
            game.sc = new Scanner(System.in);
            System.out.println("Do you still wana play!? Y or N");
            String continueGame = game.sc.nextLine();
            if (continueGame.charAt(0) == 'N'){
                doYouWantToPlay = false;
            }
        }
    
    }
    /*
     fills the array with spaces
    */
    public void initialiseGame(){
      System.out.println("Setting Up Battle Field...");
      System.out.println();
      char[][] GameState =  this.GameState;
      for(int i= 0; i<GameState.length; i++){
          for(int j = 0; j< GameState[i].length; j++){
              GameState[i][j] = ' ';
          }
      }
        System.out.println("Battle Field Set Up."+ "Hope you are ready?");
        System.out.println();
    }
    /*
      Always prints the current state of the array
    */
    public void printGameState(){
        char[][] GameState = this.GameState;
        char[] space = new char[3];
        Arrays.fill(space, ' ');
        System.out.print(new String(space));
        for(int i= 0; i<10;i++){
            System.out.print(i);
        }
        System.out.print(" ");
        System.out.println();
        for(int i = 0; i < GameState.length; i++){
            System.out.print(i + " "+ "|");
            for(int j= 0; j <GameState[i].length; j++){
                if(GameState[i][j] == '1'){
                    System.out.print("@");
                }
                else if(GameState[i][j] == '2'){
                    System.out.print(" ");
                }
                else if(GameState[i][j] == 'W' || GameState[i][j] == 'Q'){
                    System.out.print("!");
                }
                else if(GameState[i][j] == 'L' || GameState[i][j] == 'V'){
                    System.out.print("X");
                }
                else{
                    System.out.print(GameState[i][j]);
                }
                
            }
            System.out.print("|"+ " "+ i);
            System.out.println();
        }
        System.out.print(new String(space));
        for(int i= 0; i<10;i++){
            System.out.print(i);
        }
        System.out.println();
        System.out.println();
        
    }
    /*
     give user start up instruction
    */
    public void startUpInstruction(){
        System.out.println();
        System.out.println("To start you must deploay your Battle ship"+
                "\n"+ "You are to deploy 5 ship"+ "\n"+ "To Deploy just enter the x and y "+
                "coordinate of the "
                + "ocean");
        System.out.println();
        System.out.println("But first Enter your name and computer name");
    }
    /*
      Prompts user to deploy ships
    */
    public void promptUser(Player player){
        for(int i = 0; i < 5;i++){
            try{
                do{
                    System.out.println("Enter X cordinate, enter y to quit");
                    xCor = sc.nextInt();
                    System.out.println("Enter Y cordinate, enter y to quit");
                   yCor = sc.nextInt();
                }while(!(player.validateShip(this.GameState,xCor, yCor)));
                
            }catch(InputMismatchException ex){
                char res = sc.nextLine().charAt(0);
                if(res == 'Y' || res == 'y'){
                    System.out.println("Game exited");
                    this.response = false;
                    break;
                }
                else{
                    //resets the scanner object
                    sc = new Scanner(System.in);
                    //decrement i so the user can enter inputs again
                    --i;
                }
                
            }
           if(response){
              this.GameState = player.deployShip(GameState, xCor, yCor);
                //System.out.println();
               // System.out.println("Ship Deployed");
                System.out.println(); 
           } 
           
        }
        
    }
    /*
     Declares a winner or looser when game is over
    */
    public boolean GameOver(Player player, Computer Ai){
        char[][] gameState = this.GameState;
        if(player.numberOfShip == 0 || Ai.numberOfShip == 0){
            System.out.println("Game Over");
            System.out.println("Your Ships: "+ player.numberOfShip + " | Computer Ships: "+ Ai.numberOfShip);
            if(player.numberOfShip > 0){
                
                System.out.println("Hooray "+ player.playerName + " Win the Battle !!!!");
            }
            else if(Ai.numberOfShip > 0){
                System.out.println("You Loose, " + Ai.AiName + " Wins !!!");
            }
            return true;
        }
        else{
            return false;
        }
    }
    /*
      Set the name of the player and computer
    */
    public void setGame(){
        System.out.println("enter your Name: ");
        this.playerName  = sc.nextLine();
        System.out.println("enter Computer Name: ");
        this.aiName =sc.nextLine();
    }
}
