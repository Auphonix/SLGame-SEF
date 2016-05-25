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
public class PlayerTest extends Game.SLGame{

    private Board brd = new Board();
    private Dice dice = new Dice(brd.getGraphics());
    private int tempDiceRoll = 0;
    private Player playerTest[] = new Player[2];

    @Before
    public void setUp() throws Exception {
        playerTest[0] = new Player(brd,dice,1,"playerTest1",1);
        playerTest[1] = new Player(brd,dice,1,"playerTest2",1);
        tempDiceRoll = 0;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void playerMovesNumberOfDiceSquares() throws  Exception{ // Used to see if player moves equivalent to number on dice
        tempDiceRoll = 5; //Simulates a dice roll of 5
        System.out.println(tempDiceRoll);
        playerTest[0].computePos(tempDiceRoll,0); //Compute dice roll

        assertEquals(tempDiceRoll + 1, playerTest[0].getPos(0)); //ensures that player has moved to position that is on the dice
    }

    @Test
    public void playersCanLandOnSameSquareTest() throws Exception { //First User test to ensure that players can land on same square
        tempDiceRoll = 5; //Simulates virtual roll and stores in tempVariable
        System.out.println(tempDiceRoll);
        playerTest[0].computePos(tempDiceRoll,0); //Comment testing attempt
        playerTest[1].computePos(tempDiceRoll,0);

        System.out.println(playerTest[0].getPos(0));
        System.out.println(playerTest[1].getPos(0));

        assertEquals(tempDiceRoll + 1, playerTest[0].getPos(0)); //ensures that player is still on dice roll square even though
        assertEquals(tempDiceRoll + 1, playerTest[1].getPos(0)); //two players are on that square
    }

    @Test
    public void playersDontMoveUnlessTheyRollTheDice() throws Exception{ //used to ensure only one player moves on dice roll
        tempDiceRoll = 5;
        System.out.println(tempDiceRoll);
        playerTest[0].computePos(tempDiceRoll,0);

        assertEquals(tempDiceRoll + 1,playerTest[0].getPos(0));//Ensures player 1 moves
        assertEquals(1,playerTest[1].getPos(0)); // and player 2 is on the same square
    }

    @Test
    public void playerRollsAgainOnSix() throws Exception {
        tempDiceRoll = 6;
        System.out.println(dice.roll());

        playerTest[0].move();

    }

}