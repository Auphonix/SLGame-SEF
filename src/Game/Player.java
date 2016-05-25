package Game;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Player extends Draw
{
    private String name;
    private int[] pos; // the current position of the player piece
    private int index;		// represents the player index 0, 1, 2 or 3 if 4 players
    private Dice dice;
    private Board bd;
    private int[] pieces;
    static Scanner scan = new Scanner(System.in);

    public String getName()
    {
        return name;
    }
    public int getPos(int pieceNum)
    {
        return pos[pieceNum];
    }

    public Player(Board bd, Dice dice, int index, String name, int pieces)
    {
        this.bd = bd;
        this.name = name;
        if(pieces != 0) {
            this.pos = new int[pieces];
            this.pieces = new int[pieces];
        }
        this.index = index;
        this.dice = dice;
    }

    //set the number of pieces a player can control
    public void setPieces(int piece){
        this.pieces = new int[piece];
        for (int i = 0; i < piece; i++){
            //Create pieces for the player to use and give them a number between 1-3
            this.pieces[i] = i + 1;
        }
    }

    //set the number positions needed for a player based on number of pieces
    public void setPositions(int piece){
        this.pos = new int[piece];
        for (int i = 0; i < piece; i++){
            //Create pieces for the player to use and give them a number between 1-3
            this.pos[i] = 1;
        }
    }

    public void computePos(int val, int piece)
    {
        if ( pos[piece] + val <= 100)
        {
            pos[piece] += val;
        }
        pos[piece] = bd.newPos(pos[piece]);
    }

    // Causes the dice to be thrown and the new position to be computed
    public int move()
    {
        System.out.println("***** Turn of " + name + " ******" );
        String resp; //No clue what this variable is for?
        int val; //value of roll
        int pPiece = 0; //player piece
        do {
            System.out.print("Press Enter to throw dice ");
            resp = scan.nextLine(); //not sure why you'd want to capture the button pressed
            System.out.println("Rolling the dice .... Please wait");
            val = dice.roll();
            System.out.println("You threw a " + val);
            //get the piece the user wants to move
            while(pPiece < 1 || pPiece > pos.length){
                try {
                    System.out.println("Which piece do you want to move? ");
                    System.out.println("Please select a number between 1 and " + pos.length + ": ");
                    pPiece = scan.nextInt();
                    //display error if out of bounds
                    if(pPiece < 1 || pPiece > pos.length){
                        System.out.println("Error. Piece not available");
                    }
                    //display error if input is not a number
                }catch (InputMismatchException e) {
                    System.out.println("> Error. Input is not an integer");
                    scan.nextLine();
                    continue;
                }catch (StringIndexOutOfBoundsException e){
                    System.out.println("> Error. Please enter text");
                    scan.nextLine();
                    continue;
                }
            }
            //As the player sees 1 on the piece and it is 0 in array. -1 below is to align with array values;
            computePos(val, pPiece - 1);	// computes the new position based on the dice value
            bd.repaint();		// causes the board and pieces to be redrawn
            System.out.println(name + "." + pPiece + " is now at position " + pos[pPiece - 1]);
            if (pos[pPiece-1] == 100)
                return index;	// returns the index of the player winning the game
            if (val == 6)
                System.out.println("You get to throw again");
        } while (val == 6);
        return -1;
    }


    // Drawing the positions of the pieces using different colors
    public void draw(Graphics g)
    {
        if (index == 0)
        {
            for(int i = 0; i < pos.length; i++) {
                g.setColor(Color.WHITE); // Used to set Players visible on the board
                g.fillOval(getX(pos[i]) - 10, getY(pos[i]) - 10, 20, 20);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(i + 1), getX(pos[i]) - 5, getY(pos[i]) + 5);

            }
            g.setColor(Color.WHITE); // Used to set Players visible on key
            g.fillOval(25,455,40,40);
            g.setColor(Color.BLACK);
            g.drawString("P1",40,478);

            JLabel lblPlayer1 = new JLabel(this.getName());
            lblPlayer1.setBounds(75,455,150,40);
            lblPlayer1.setFont(new Font("Menlo", Font.PLAIN, 13));
            bd.add(lblPlayer1);
        }
        if (index == 1)
        {
            for(int i = 0; i < pos.length; i++) {
                g.setColor(Color.RED);
                g.fillOval(getX(pos[i]) + 10, getY(pos[i]) - 10, 20, 20);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(i + 1), getX(pos[i]) + 15, getY(pos[i]) + 5);
            }
            g.setColor(Color.RED); // Used to set Players visible on the key
            g.fillOval(260,455,40,40);
            g.setColor(Color.BLACK);
            g.drawString("P2",275,478);

            JLabel lblPlayer2 = new JLabel(this.getName());
            lblPlayer2.setBounds(310,455,150,40);
            lblPlayer2.setFont(new Font("Menlo", Font.PLAIN, 13));
            bd.add(lblPlayer2);

        }
        if (index == 2)
        {
            for(int i = 0; i < pos.length; i++) {
                g.setColor(Color.BLUE);
                g.fillOval(getX(pos[i]) - 10, getY(pos[i]) + 10, 20, 20);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(i + 1), getX(pos[i]) - 5, getY(pos[i]) + 25);
            }
            g.setColor(Color.BLUE); // Used to set Players visible on the key
            g.fillOval(25,520,40,40);
            g.setColor(Color.BLACK);
            g.drawString("P3",40,543);

            JLabel lblPlayer3 = new JLabel(this.getName());
            lblPlayer3.setBounds(75,520,150,40);
            lblPlayer3.setFont(new Font("Menlo", Font.PLAIN, 13));
            bd.add(lblPlayer3);
        }
        if (index == 3)
        {
            for(int i = 0; i < pos.length; i++) {
                g.setColor(Color.CYAN);
                g.fillOval(getX(pos[i]) + 10, getY(pos[i]) + 10, 20, 20);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(i + 1), getX(pos[i]) + 15, getY(pos[i]) + 25);
            }
            g.setColor(Color.CYAN); // Used to set Players visible on the key
            g.fillOval(260,520,40,40);
            g.setColor(Color.BLACK);
            g.drawString("P4",275,543);

            JLabel lblPlayer4 = new JLabel(this.getName());
            lblPlayer4.setBounds(310,520,150,40);
            lblPlayer4.setFont(new Font("Menlo", Font.PLAIN, 13));
            bd.add(lblPlayer4);
        }
    }

}
