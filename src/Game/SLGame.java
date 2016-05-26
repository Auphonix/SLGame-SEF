/* Written by Charles Theva as part of the Introductory Programming Course  */
/* You are free to refactor and modify the program 							*/
package Game;

import javax.print.DocFlavor;
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
    //array of players
    public Player players[] = new Player[4];
    //array of snakes
    //NOTE as there is a potential for an overcrowded screen, snake players is capped at 4.
    public Snake snakes[] = new Snake[4];
    public int pCount = -1;
    public int pPiecesCount;
    public int snakeCount;
    public boolean isCustomized;
    private Board bd;
    private Account ac[] = new Account[10]; //Maximum 10 Accounts
    private int turn = 0;
    public int[] snakeOnBoardCount;
    public int[] numberOfSnakes = new int[4];
    public int[] numberOfPlayerPieces = new int[4];

    Scanner scan = new Scanner(System.in);

    public static void main(String args[]) {SLGame sg = new SLGame(); }

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
            try {
                ch = displayMenu();
                isCustomized = false;
                switch (ch) {
                    case '1':
                        play();
                        break;
                    case '2':
                        bd.customize();
                        isCustomized = true;
                        break;
                }
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("> Error. Please enter text");
                continue;
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
            if(isCustomized == false) {
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
            }
            else{
                System.out.println("Board is already customized");
            }
            //Ensure no buffer overflow
            scan.nextLine();
        }

        //Get the number of players

        pPiecesCount = avgIntArray(numberOfPlayerPieces);
        System.out.println(pPiecesCount + " is the number of pieces");
        if(isCustomized == false) {
            snakeCount = avgIntArray(numberOfSnakes);
            System.out.println(snakeCount + " is the number of snakes");
        }
        //Setup the board if not already customized
        if(isCustomized == false) {
            setupSAndL(snakeCount);
        }
        //Sets the number of pieces and positions based on average user input
        for (int i = 0; i < pCount; i++) {
            players[i].setPieces(pPiecesCount);
            players[i].setPositions(pPiecesCount);
        }

        bd.add(players, pCount);

        int again;

        while (true) {
            inGameDisplay(pPiecesCount); //inform the user if there are pieces on the same square
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

    public void setupSAndL(int snakeCount){
        //Create the snakes
        for(int i = 0; i < snakeCount; i++){
            //get head as random number below 100 (not 100 to prevent impossible win)
            int rnd = (int)(Math.random() * 75);
            rnd += 25;

            //get the max of the tail. Max tail must be minimum of 10 squares from head.
            int rnd2Max;
            int rnd2;
            //if head is on 10 then there will be a math error therefore change tail to 0
            if(rnd < 11){
                rnd2 = 1;
            }else {
                rnd2Max = rnd - 10;
                rnd2 = (int)(Math.random() * rnd2Max);
            }
            //create snake on the board
            bd.add(new Snake(rnd, rnd2));
        }
        //create the ladders must be snakes minus 2
        for(int i = 0; i < snakeCount - 2; i++){
            //get top as random number below 100 (not 100 to prevent impossible win)
            int rnd = (int)(Math.random() * 75);
            rnd += 25;

            //get the max of the bottom position. bottom must be minimum of 10 squares from top.
            int rnd2Max;
            int rnd2;
            //if top is on 10 then there will be a math error therefore change bottom to 0
            if(rnd < 11){
                rnd2 = 1;
            }else {
                rnd2Max = rnd - 20;
                rnd2 = (int)(Math.random() * rnd2Max);
            }
            //create Ladder on the board
            bd.add(new Ladder(rnd2, rnd));
        }
    }


    //Note this function can definitely be shortened!!!
    public void inGameDisplay(int numPieces) {

        //Reset the ingame terminal upon display
        Board.inGameTerminalOutput.setText("");

        //Display Position Info
        disPieceOverlap(numPieces);
    }

    public void disPieceOverlap(int numPieces){
        //Create new string builder
        StringBuilder strBld = new StringBuilder();
        String dispString;

        Board.terminalOutput("PLAYER PIECE POSITION.");
        Board.terminalOutput("----------------------");
        Board.terminalOutput("NAME -> (P1, P2, P3)");
        for(int i = 0; i < pCount; i++){
            //add name to string
            strBld.append(players[i].getName() + " -> ");
            for(int j = 0; j < numPieces; j++){
                if(j != (numPieces - 1)) {
                    strBld.append(Integer.toString(players[i].getPos(j)) + ", ");
                }
                else {
                    //checks if only one or two players on the board
                    if(j == 1){
                        strBld.append(" -.");
                    }else {
                        strBld.append(Integer.toString(players[i].getPos(j)) + ".");
                    }
                }
            }
            dispString = strBld.toString();
            Board.terminalOutput(dispString);
            strBld.setLength(0);
        }
    }

}

