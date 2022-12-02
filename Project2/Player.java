/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

/**
 * A class for player, the child class of the Unit class
 */
public class Player extends Unit{

    /**
     * player's max health
     */
    private int maxHealth;

    /**
     * player's damage
     */
    private int damage;

    /**
     * player's level
     */
    private int level;

    /**
     * player's min health
     */
    private final int MIN_HEALTH = 17;

    /**
     * player's health increment
     */
    private final int HEALTH_INCREMENT = 3;

    /**
     * the default constructor of the Player class
     */
    public Player() {
        setName(null);
        this.level = 1;
        setMaxHealth(MIN_HEALTH + this.level * HEALTH_INCREMENT);
        this.damage = 1 + this.level;
        setCurrentHealth(MIN_HEALTH + this.level * HEALTH_INCREMENT);
    }

    /* Getter */

    /**
     * Get the player's level
     *
     * @return player's level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Get the player's max health
     *
     * @return player's max health
     */
    public int getMaxHealth() {
        return this.maxHealth = MIN_HEALTH + this.level * HEALTH_INCREMENT;
    }

    /**
     * Get the player's damage
     *
     * @return player's damage
     */
    public int getDamage() {
        return this.damage = 1 + this.level;
    }

    /* Setter */
    /**
     * Set the player's level
     *
     * @param level the player's level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Set the player's max health
     *
     * @param level the player's level
     */
    public void setMaxHealth(int level) {
        this.maxHealth = MIN_HEALTH +level * HEALTH_INCREMENT;
    }


}
