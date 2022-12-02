/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

/**
 * A class for monster, the child class of the Unit class
 */
public class Monster extends Unit{
    /**
     * monster's max health
     */
    private int maxHealth;

    /**
     * monster's damage
     */
    private int damage;

    /**
     * the default constructor of the Monster class
     */
    public Monster() {
        setName(null);
        this.maxHealth = 0;
        this.damage = 0;
        setCurrentHealth(0);
    }

    /* Getter */

    /**
     * Get the monster's max health
     *
     * @return monster's max health
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Get the monster's damage
     *
     * @return monster's damage
     */
    public int getDamage() {
        return this.damage;
    }

    /* Setter */
    /**
     * Set the monster's max health
     *
     * @param maxHealth monster's max health
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Set the monster's damage
     * @param damage monster's damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

}
