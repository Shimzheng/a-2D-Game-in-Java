/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */
public class Player {

    private String name;
    private int level;
    private int maxHealth;
    private int damage;
    private int currentHealth;

    private final int MIN_HEALTH = 17;
    private final int HEALTH_INCREMENT = 3;

    public Player() {
        this.name = null;
        this.level = 1;
        this.maxHealth = MIN_HEALTH + this.level * HEALTH_INCREMENT;
        this.damage = 1 + this.level;
        this.currentHealth = this.maxHealth;
    }

    /* Getter */
    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }


    /* Setter */
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

}
