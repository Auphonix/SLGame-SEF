package Game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
 * Created by Danyon on 26/05/2016.
 */
public class SnakePlayer extends Player{

    public int snakeID;

    public SnakePlayer(Board bd, Dice dice, int index, String name, int pieces){
        super(bd, dice, index, name, pieces);
        this.snakeID = 0;
    }
}
