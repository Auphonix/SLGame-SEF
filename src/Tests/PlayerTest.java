package Tests;

import Game.Board;
import Game.Dice;
import Game.Player;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

/**
 * Created by Danyon on 18/04/2016.
 */
public class PlayerTest {

    Board brd = new Board();
    Graphics g;
    Dice dice = new Dice(g);
    int tempDiceRoll;

    Player playerTest1 = new Player(brd,dice,1,0,"playerTest1");
    Player playerTest2 = new Player(brd,dice,0,0,"playerTest2");

    @Before
    public void setUp() throws Exception {
        Player playerTest1 = new Player(brd,dice,1,0,"playerTest1");
        Player playerTest2 = new Player(brd,dice,0,0,"playerTest2");
        tempDiceRoll = 0;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void playersCanLandOnSameSquareTest() throws Exception { //First User test to ensure that players can land on same square

        System.out.println(tempDiceRoll);//
        tempDiceRoll = 5; //Simulates virtual roll and stores in tempVariable
        System.out.println(tempDiceRoll);
        playerTest1.computePos(tempDiceRoll); //Comment testing attemp
        playerTest2.computePos(tempDiceRoll);

        assertEquals(tempDiceRoll, playerTest1.getPos()); //ensures that player is still on dice roll square even though
        assertEquals(tempDiceRoll, playerTest2.getPos()); //two players are on that square
    }

}