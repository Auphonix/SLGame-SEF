/* Written by Charles Theva as part of the Introductory Programming Course  */
/* You are free to refactor and modify the program 							*/
package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Game.Board.inGameTerminalInput;
import static Game.Board.textEntered;

// The main system level class 
public class SLGame {
    public Player players[] = new Player[4];
    public int pCount = -1;
    public int pPiecesCount;
    public int snakeCount;
    private Board bd;
    private Account ac[] = new Account[10]; //Maximum 10 Accounts
    private int turn = 0;
    public int[] numberOfSnakes = new int[4];
    public int[] numberOfPlayerPieces = new int[4];

    Scanner scan = new Scanner(System.in);

    public static void main(String args[]) {
        SLGame sg = new SLGame();
    }

    public char displayMenu() {
        System.out.println("******* Snakes Menu ********");
        System.out.println("Play Game        : 1");
        System.out.println("Customize Board  : 2");
        System.out.println("Exit             : 3");
        System.out.println("Enter 1/2/3      : ");
        System.out.println("****************************");
        System.out.println("Enter Text");
        return scan.nextLine().charAt(0);
    }

    public void changeTurn() {
        turn++;
        if (turn == pCount)
            turn = 0;
    }

    public SLGame() {
        bd = new Board();
        //NOTE Ignore bellow as this is an attempt to make the ingame console works
        /*inGameTerminalInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Something was pressed");
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textEntered = inGameTerminalInput.getText();
                    bd.inputStream = new ByteArrayInputStream(textEntered.getBytes(StandardCharsets.UTF_8));
                    System.setIn(bd.inputStream);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        */
        char ch = ' ';
        do {
            ch = displayMenu();
            switch (ch) {
                case '1':
                    play();
                    break;
                case '2':
                    bd.customize();
                    break;
            }
            System.out.println("> Please enter a valid option");
        } while (ch != '3');
    }

    public void play() {
        while (pCount < 2 || pCount > 4) {
            try {
                System.out.print("Enter number of players : ");
                pCount = scan.nextInt();
                //Ensure that the users have no more than 4 and less than 2 players.
                if (pCount > 4 || pCount < 2) {
                    System.out.println("> Error. Minimum 2 players and Maximum 4 players");
                }
                //handles non integers
            } catch (InputMismatchException e) {
                System.out.println("> Error. Input is not an integer");
                scan.nextLine();
                continue;
            }
            scan.nextLine();
        }


        Dice dice = new Dice(bd.getGraphics());

        for (int i = 0; i < pCount; i++) {
            System.out.print("Enter name of player " + (i + 1) + " : ");
            String name = scan.nextLine();
            players[i] = new Player(bd, dice, i, name, 0);
            //set to minus one to ensure that while loops below work.
            numberOfPlayerPieces[i] = -1;
            numberOfSnakes[i] = -1;
            bd.repaint();
            //Ensures number is between 1 and 3
            while (numberOfPlayerPieces[i] < 1 || numberOfPlayerPieces[i] > 3) {
                //Used to determine number of player pieces on the board
                try {
                    System.out.print(players[i].getName() + " , Enter number of player pieces : ");
                    numberOfPlayerPieces[i] = scan.nextInt();
                    if (numberOfPlayerPieces[i] < 1 || numberOfPlayerPieces[i] > 3) {
                        System.out.println("> Error. Please enter a number between 1 and 3");
                    }
                    //handles non integers
                } catch (InputMismatchException e) {
                    System.out.println("> Error. Input is not an integer");
                    scan.nextLine();
                    continue;
                }

            }
            //used to determine the average number of snakes on the board
            while (numberOfSnakes[i] < 4 || numberOfSnakes[i] > 8) {
                try {
                    System.out.print(players[i].getName() + " , Enter number of snakes on board : ");
                    numberOfSnakes[i] = scan.nextInt();
                    if (numberOfSnakes[i] < 4 || numberOfSnakes[i] > 8) {
                        System.out.println("> Error. Please enter a number between 4 and 8");
                    }
                    //handles non integers
                } catch (InputMismatchException e) {
                    System.out.println("> Error. Input is not an integer");
                    scan.nextLine();
                    continue;
                }
            }
            //Ensure no buffer overflow
            scan.nextLine();
        }

        //Get the number of players

        pPiecesCount = avgIntArray(numberOfPlayerPieces);
        snakeCount = avgIntArray(numberOfSnakes);
        System.out.println(pPiecesCount + " is the number of pieces");
        System.out.println(snakeCount + " is the number of snakes");

        //Sets the number of pieces and positions based on average user input
        for (int i = 0; i < pCount; i++) {
            players[i].setPieces(pPiecesCount);
            players[i].setPositions(pPiecesCount);
        }

        bd.add(players, pCount);

        int again;

        while (true) {
            int pos = players[turn].move();   // players get to move in turn
            if (pos != -1) {
                System.out.println("**** GAME OVER " + players[turn].getName() + " is the winner with piece ");
                break;
            } else
                changeTurn();
        }
    }

    //Used to get the average player number chosen by all players
    public int avgIntArray(int[] numbers) {
        int total = 0;
        int counter = getNumOfElements(numbers);

        for (int x = 0; x < counter; x++) {
            total += numbers[x];
        }
        total = total / counter;
        return total;
    }

    //get the number of elements in array *NOT THE LENGTH
    public int getNumOfElements(int[] numbers) {
        int counter = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                counter++;
            }
        }
        return counter;
    }

}

