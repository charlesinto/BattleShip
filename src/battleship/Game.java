/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

public interface Game {
    public char[][] shotEnemy(char[][] game,int xCordinate, int yCordinate);
    public char[][] deployShip(char[][] game,int xCordinate, int yCordinate);
    public boolean validateShip(char[][] game,int xCordinate, int yCordinate);
}
