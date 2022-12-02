/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

import java.io.Serializable;

/**
 * A class for the storage of the player (name and level)
 */
public class PlayerStorage implements Serializable{

    /**
     * the default constructor of the PlayerStorage class
     */
    public PlayerStorage(){
    }

    /**
     * the name of the player
     */
    String playerName;

    /**
     * the level of the player
     */
    int playerLevel;

}
