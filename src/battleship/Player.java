/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;
public class Player implements Game{
    char[][] GameState = new char[10][10];
    String playerName;
    String attackOutcome = " ";
    int numberOfShip = 5;
    public Player(String playerName,char[][] game){
        this.GameState = game;
        this.playerName = playerName;
    }
    @Override
    public char[][] shotEnemy(char[][] game,int xCordinate, int yCordinate) {
        if(xCordinate > game.length || yCordinate >= game.length){
            System.out.println("Out of Range");
            this.attackOutcome = "0";
            return game;
        }
        else if(game[xCordinate][yCordinate] == ' ' || game[xCordinate][yCordinate] == 'W' || game[xCordinate][yCordinate] == 'L'){
            System.out.println("You missed");
            return game;
        }
        else if(game[xCordinate][yCordinate] == '2'){
            System.out.println("Boom!, you sunk the ship");
            this.attackOutcome = "T";
            game[xCordinate][yCordinate] = 'W';
            return game;
        }
        else if(game[xCordinate][yCordinate] == '1'){
            System.out.println("Oh no, you sunk your own ship");
            this.attackOutcome = "F";
            game[xCordinate][yCordinate] = 'L';
            this.numberOfShip--;
            return game;
        }
        return game;
    }
    public char[][] deployShip(char[][] game,int xCordinate, int yCordinate) {
        game[xCordinate][yCordinate] = '1';
        return game;
    }

    @Override
    public boolean validateShip(char[][] game,int xCordinate, int yCordinate) {
      if (xCordinate < 0 || yCordinate < 0){
          System.out.println("The Coordinate must be positive integer");
          return false;
      }  
      else if(xCordinate >= game.length || yCordinate >= game[xCordinate].length){
          System.out.println("Sorry Coordinate is Out of range");
          return false;
      }
      else{
          if(game[xCordinate][yCordinate] != ' '){
              System.out.println("Sorry Coordinate not available");
              return false;
          }
          else{
              return true;
          }
      }
    }
    
}
