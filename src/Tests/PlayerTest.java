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
    Dice dice = new Dice(brd.getGraphics());
    int tempDiceRoll = 0;
    private Player playerTest[] = new Player[2];

    @Before
    public void setUp() throws Exception {
        playerTest[0] = new Player(brd,dice,1,1,"playerTest1");
        playerTest[1] = new Player(brd,dice,0,2,"playerTest2");
        tempDiceRoll = 0;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void playerMovesNumberOfDiceSquares() throws  Exception{ // Used to see if player moves equivalent to number on dice
        tempDiceRoll = 5; //Simulates a dice roll of 5
        System.out.println(tempDiceRoll);
        playerTest[0].computePos(tempDiceRoll); //Compute dice roll

        assertEquals(tempDiceRoll + 1, playerTest[0].getPos()); //ensures that player has moved to position that is on the dice
    }

    @Test
    public void playersCanLandOnSameSquareTest() throws Exception { //First User test to ensure that players can land on same square
        tempDiceRoll = 5; //Simulates virtual roll and stores in tempVariable
        System.out.println(tempDiceRoll);
        playerTest[0].computePos(tempDiceRoll); //Comment testing attempt
        playerTest[1].computePos(tempDiceRoll);

        System.out.println(playerTest[0].getPos());
        System.out.println(playerTest[1].getPos());

        assertEquals(tempDiceRoll + 1, playerTest[0].getPos()); //ensures that player is still on dice roll square even though
        assertEquals(tempDiceRoll + 1, playerTest[1].getPos()); //two players are on that square
    }

    @Test
    public void playersDontMoveUnlessTheyRollTheDice() throws Exception{ //used to ensure only one player moves on dice roll
        tempDiceRoll = 5;
        System.out.println(tempDiceRoll);
        playerTest[0].computePos(tempDiceRoll);

        assertEquals(tempDiceRoll + 1,playerTest[0].getPos());//Ensures player 1 moves
        assertEquals(1,playerTest[1].getPos()); // and player 2 is on the same square
    }

    @Test
    public void playerRollsAgainOnSix() throws Exception {



    }

}