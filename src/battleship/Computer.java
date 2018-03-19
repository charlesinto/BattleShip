/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Random;
public class Computer {
    char[][] gameState = new char[10][10];
    String AiName;
    String attackOutCome = "";
    int numberOfShip = 5;
    public Computer(String AiName, char[][] game){
        this.gameState = game;
        this.AiName = AiName;
    }   
    public char[][] shotEnemy(char[][] game) {
        Random rand = new Random();
        int x = Math.abs(rand.nextInt())%game.length;
        int y = Math.abs(rand.nextInt())%game.length;
        if(game[x][y] == '1'){
            this.attackOutCome = "T";
            game[x][y] = 'V';
            System.out.println("Computer got you");
            return game;
        }
        else if(game[x][y] == '2'){
            System.out.println("Computer sunk one of its own ship");
            this.numberOfShip--;
            game[x][y] = 'Q';
            this.attackOutCome = "F";
            return game;
        }
        else if (game[x][y] == ' ' || game[x][y] == 'W' || game[x][y] == 'L' || game[x][y] == 'Q' || game[x][y] == 'V'){
            System.out.println("Computer Missed");
            return game;
        }
        return game;
    }
    public char[][] deployShip(char[][] game) {
        System.out.println("Computer is deploying ships");
        Random rand = new Random();
       for(int i = 0; i < 5; i++){
           int x = Math.abs(rand.nextInt()) % game.length;
           int y = Math.abs(rand.nextInt()) % game.length;
           if(!validateShip(game,x,y)){
               --i;
           }
           else{
               game[x][y] = '2';
               System.out.println("Ship Deployed");
               System.out.println();
           }
       }
       return game;
    }
    public boolean validateShip(char[][] game,int xCordinate, int yCordinate) {
       if (xCordinate < 0 || yCordinate < 0){
          return false;
      }  
      else if(xCordinate >= game.length || yCordinate >= game[xCordinate].length){
          return false;
      }
      else{
          if(game[xCordinate][yCordinate] != ' '){
              return false;
          }
          else{
              return true;
          }
      }
    }
    
}
