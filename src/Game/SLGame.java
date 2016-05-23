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
public class SLGame
{
    public Player players[] = new Player[4];
    public int pCount;
    private Board bd;
    private Account ac[] = new Account[10]; //Maximum 10 Accounts
    private int turn = 0;
    public int numberOfSnakes[] = new int[4];
    public int numberOfPlayerPieces[] = new int[4];
    Scanner scan = new Scanner(System.in);

    public static void main(String args[])
    {
        SLGame sg = new SLGame();
    }

    public char displayMenu()
    {
        System.out.println("******* Snakes Menu ********");
        System.out.println("Play Game        : 1");
        System.out.println("Customize Board  : 2");
        System.out.println("Exit             : 3");
        System.out.println("Enter 1/2/3      : ");
        System.out.println("****************************");
        System.out.println("Enter Text");
        return scan.nextLine().charAt(0);
    }

    public void changeTurn()
    {
        turn++;
        if (turn == pCount)
            turn = 0;
    }

    public SLGame()
    {
        bd = new Board();
        inGameTerminalInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Something was pressed");
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textEntered = inGameTerminalInput.getText();
                    bd.inputStream = new ByteArrayInputStream(textEntered.getBytes(StandardCharsets.UTF_8));
                    System.setIn(bd.inputStream);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        char ch = ' ';
        do {
            ch = displayMenu();
            System.out.println(ch);
            switch (ch)
            {
                case '1' : play(); break;
                case '2' : bd.customize(); break;
            }
        } while (ch != '3');
    }

    public void play()
    {
        System.out.print("Enter number of players : ");
        pCount = scan.nextInt();
        if ( pCount> 4 || pCount < 2)
        {
            System.out.println("Minimum 2 players and Maximum 4 players");
            System.exit(0);
        }
        scan.nextLine();


        Dice dice = new Dice(bd.getGraphics());

        for ( int i=0; i <pCount; i++)
        {
            System.out.print("Enter name of player " + (i + 1) + " : ");
            String name = scan.nextLine();
            players[i] = new Player(bd,dice,i,1,name);
            bd.repaint();
            while(true) {
                //Used to determine number of player pieces on the board
                try{
                    System.out.print("Player " + (i + 1) + ", Enter number of player pieces : ");
                    numberOfPlayerPieces[i] = scan.nextInt();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.println("> Error. Input is not an integer");
                    scan.nextLine();
                    continue;
                }

            }
            //used to determine the average number of snakes on the board
            System.out.println("The average is: " + averagePlayersNum(numberOfPlayerPieces));
            while(true) {
                try {
                    System.out.print("Player " + (i + 1) + ", Enter number of snakes on board : ");
                    numberOfSnakes[i] = scan.nextInt();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.println("> Error. Input is not an integer");
                    scan.nextLine();
                }
            }
            scan.nextLine();
        }
        System.out.println("The average is: " + averagePlayersNum(numberOfPlayerPieces));
        bd.add(players,pCount);

        int again;

        while ( true )
        {
            int pos = players[turn].move();   // players get to move in turn
            if ( pos != -1)
            {
                System.out.println("**** GAME OVER " + players[turn].getName() + " is the winner ******");
                break;
            }
            else
                changeTurn();
        }
    }

    //Used to get the average player number chosen by all players
    public int averagePlayersNum(int[] numbers) {
        int total = 0;
        for(int x = 0; x < numbers.length; x++){
            total += numbers[x];
        }
        total = total % numbers.length;
        return total;
    }
}

