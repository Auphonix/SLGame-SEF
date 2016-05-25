package Game;

import java.awt.*;

public class Dice
{
    private int n = -1;
    private Board sl;
    private Graphics g;

    public Dice(Graphics g)
    {
        this.g = g;
    }

    // Draw the dice value using a series of dots using fillOval
    public void draw()
    {
        g.setColor(Color.BLACK);
        g.fill3DRect(537,510,45,45,false);
        g.setColor(Color.WHITE);
        if ( n == 1)
        {
            g.fillOval(557,530,5,5);
        }
        else if ( n == 2 )
        {
            g.fillOval(547,530,5,5);
            g.fillOval(567,530,5,5);
        }
        else if ( n == 3 )
        {
            g.fillOval(557,520,5,5);
            g.fillOval(547,540,5,5);
            g.fillOval(567,540,5,5);
        }
        else if ( n == 4 )
        {
            g.fillOval(547,520,5,5);
            g.fillOval(567,520,5,5);
            g.fillOval(547,540,5,5);
            g.fillOval(567,540,5,5);
        }
        else if ( n == 5 )
        {
            g.fillOval(547,520,5,5);
            g.fillOval(567,520,5,5);
            g.fillOval(557,530,5,5);
            g.fillOval(547,540,5,5);
            g.fillOval(567,540,5,5);
        }
        else if ( n == 6 )
        {
            g.fillOval(547,520,5,5);
            g.fillOval(567,520,5,5);
            g.fillOval(547,530,5,5);
            g.fillOval(567,530,5,5);
            g.fillOval(547,540,5,5);
            g.fillOval(567,540,5,5);
        }
    }

    // simulate the drawing of the dice
    public int roll()
    {
        int n = 0;
        for (int i=1; i<=20; i++) {
            n = getThrow();
            set(n,100);
        }
        set(n,500);
        return n;
    }

    // sets the dice value and draws it
    public void set(int val, int time)
    {
        if ( val >= 1 && val <= 6)
        {
            n = val;
            draw();
            try {
                Thread.sleep(time);
            }
            catch(Exception e) {}
        }
    }

    public static int getThrow() { return (int) (Math.random()*6) + 1;    }
}
