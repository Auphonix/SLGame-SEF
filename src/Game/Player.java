package Game;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Player extends Draw
{
    private String name;
    private int pos = 1;		// the current position of the player piece
    private int index;		// represents the player index 0, 1, 2 or 3 if 4 players
    private Dice dice;
    private Board bd;
    static Scanner scan = new Scanner(System.in);

    public String getName()
    {
        return name;
    }
    public int getPos()
    {
        return pos;
    }

    public Player(Board bd, Dice dice, int index, int pos, String name)
    {
        this.bd = bd;
        this.name = name;
        this.pos = pos;
        this.index = index;
        this.dice = dice;
    }

    public void computePos(int val)
    {
        if ( pos + val <= 100)
        {
            pos += val;
        }
        pos = bd.newPos(pos);
    }

    // Causes the dice to be thrown and the new position to be computed
    public int move()
    {
        System.out.println("***** Turn of  " + name + " ******" );
        String resp;
        int val;
        do {
            System.out.print("Press Enter to throw dice ");
            resp = scan.nextLine();
            System.out.println("Rolling the dice .... Please wait");
            val = dice.roll();
            System.out.println("You threw a " + val);
            computePos(val);	// computes the new position based on the dice value
            bd.repaint();		// causes the board and pieces to be redrawn
            System.out.println(name + " is now at position " + pos);
            if (pos == 100)
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
            g.setColor(Color.WHITE); // Used to set Players visible on the board
            g.fillOval(getX(pos) -10,getY(pos)-10,20,20);
            g.setColor(Color.BLACK);
            g.drawString("1", getX(pos) -5,getY(pos)+5);

            g.setColor(Color.WHITE); // Used to set Players visible on the board
            g.fillOval(25,455,40,40);
            g.setColor(Color.BLACK);
            g.drawString("1",40,475);

            JLabel lblPlayer1 = new JLabel(this.getName());
            lblPlayer1.setBounds(75,455,50,40);
            bd.add(lblPlayer1);
        }
        if (index == 1)
        {
            g.setColor(Color.RED);
            g.fillOval(getX(pos) +10,getY(pos)-10,20,20);
            g.setColor(Color.BLACK);
            g.drawString("2", getX(pos) +15,getY(pos)+5);

            g.setColor(Color.RED); // Used to set Players visible on the board
            g.fillOval(260,455,40,40);
            g.setColor(Color.BLACK);
            g.drawString("2",275,475);

            JLabel lblPlayer2 = new JLabel(this.getName());
            lblPlayer2.setBounds(310,455,50,40);
            bd.add(lblPlayer2);

        }
        if (index == 2)
        {
            g.setColor(Color.BLUE);
            g.fillOval(getX(pos) -10,getY(pos)+10,20,20);
            g.setColor(Color.BLACK);
            g.drawString("3", getX(pos) -5,getY(pos)+25);

            g.setColor(Color.BLUE); // Used to set Players visible on the board
            g.fillOval(25,520,40,40);
            g.setColor(Color.BLACK);
            g.drawString("3",40,540);

            JLabel lblPlayer1 = new JLabel(this.getName());
            lblPlayer1.setBounds(75,520,50,40);
            bd.add(lblPlayer1);
        }
        if (index == 3)
        {
            g.setColor(Color.CYAN);
            g.fillOval(getX(pos) +10,getY(pos)+10,20,20);
            g.setColor(Color.BLACK);
            g.drawString("4", getX(pos) +15,getY(pos)+25);

            g.setColor(Color.CYAN); // Used to set Players visible on the board
            g.fillOval(260,520,40,40);
            g.setColor(Color.BLACK);
            g.drawString("4",275,540);

            JLabel lblPlayer2 = new JLabel(this.getName());
            lblPlayer2.setBounds(310,520,50,40);
            bd.add(lblPlayer2);
        }
    }

}
