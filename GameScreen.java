package cpsc2150.connectX;

import java.util.*;

public class GameScreen {
    // The main that will run the file.
    public static void main(String[] args) {
        boolean repeatGame = true;
        while (repeatGame == true) {
            GameBoard myGame = new GameBoard();
            String myBoard = myGame.toString();
            System.out.println(myBoard);
            char currentPlayer = 'X';
            boolean killGame = false;
            while (killGame == false) {
                Scanner input = new Scanner(System.in);
                System.out.println("Player " + currentPlayer + ", what column do you want to place your marker in?");
                int userInput = input.nextInt();
                while (userInput >= myGame.getNumColumns() || userInput < 0) {
                    System.out.println("Invalid input, must be columns in between 0 - " + myGame.getNumColumns());
                    userInput = input.nextInt();
                }
                while (myGame.checkIfFree(userInput) == false) {
                    System.out.println("This column is not free, re-choose another valid column");
                    userInput = input.nextInt();
                    while (userInput >= myGame.getNumColumns() || userInput < 0) {
                        System.out.println("Invalid input, must be columns in between 0 - " + (myGame.getNumColumns()-1));
                        userInput = input.nextInt();
                    }
                }
                myGame.placeToken(currentPlayer, userInput);
                myBoard = myGame.toString();
                System.out.println(myBoard);
                if (myGame.checkForWin(userInput) == true) {
                    break;
                }
                if (myGame.checkTie() == true) {
                    System.out.println("The game has ended in a tie.");
                    break;
                }

                // Player Turns
                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                }
                else {
                    currentPlayer = 'X';
                }
            }
            if (myGame.checkTie() == false) {
                System.out.println("Player " + currentPlayer + " won!");
            }
            System.out.println("Would you like to play again? Y/N");
            String userInput2;
            Scanner repeatIn = new Scanner(System.in);
            userInput2 = repeatIn.next();

            boolean repeat = true;
            while(repeat) {
                if (userInput2.equals("y")) {
                    repeatGame = true;
                    break;
                }
                else if (userInput2.equals("n")) {
                    repeatGame = false;
                    break;
                }
                System.out.println("Would you like to play again? Y/N");
                userInput2 = repeatIn.next();
            }
        }
    }
}
