/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */
public class Monster {
    private String name;
    private int maxHealth;
    private int damage;
    private int currentHealth;

    public Monster() {
        this.name = null;
        this.maxHealth = 0;
        this.damage = 0;
        this.currentHealth = 0;
    }

    /* Getter */
    public String getName() {
        return this.name;
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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
