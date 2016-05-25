package Tests;

import Game.Dice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Created by Danyon on 18/04/2016.
 */
public class DiceTest {
    //Create instance of dice object
    public Graphics g;
    public Dice d = new Dice(g);
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void draw() throws Exception {

    }

    @Test
    public void testDiceRoll() throws  Exception{
        d.roll();
    }

    @Test
    public void set() throws Exception {

    }

    @Test
    public void getThrow() throws Exception {

    }

}