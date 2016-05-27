package Game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import Game.SLGame;
/**
 * Created by Danyon on 26/05/2016.
 */
public class SnakePlayer extends Snake{

    private int head;
    private int tail;
    private int parentID;
    public int snakePlayerID;

    public SnakePlayer(int id){
        this.head = super.getHead();
        this.tail = super.getTail();
        this.parentID = super.getId();
        this.snakePlayerID = id;
    }
}
