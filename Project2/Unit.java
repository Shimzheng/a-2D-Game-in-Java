/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

/**
 * A class for units, the child class of the Entity class
 */
public class Unit extends Entity{

    /**
     * the name of the unit
     */
    private String name;

    /**
     * the current health of the unit
     */
    private int currentHealth;

    /**
     * the type of the unit
     */
    private String unitType;

    /**
     * the default constructor of the Unit class
     */
    public Unit(){}

    /* Getter */

    /**
     * Get the name of the unit
     * @return the name of the unit
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the current health of the unit
     * @return the current health of the unit
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /* Setter */
    /**
     * Set the name of the unit
     * @param name the name of the unit
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the current health of the unit
     * @param currentHealth the current health of the unit
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Set the type of the unit
     * @param unitType the type of the unit
     * @return the type of the unit
     */
    public String setUnitType(String unitType){
        return this.unitType = unitType;
    }

    /**
     * Function to print the first Char of the unit
     *
     * @param unitType the type of the unit
     * @param name the name of the unit
     */
    void printEntity(String unitType, String name) {
        if (unitType.equals("player")) {
            System.out.print(name.toUpperCase().charAt(0));
        } else if (unitType.equals("monster")){
            System.out.print(name.toLowerCase().charAt(0));
        }
    }

    /**
     * A call for printing the first Char of the unit
     */
    void printUnitFunction(){
        this.printEntity(unitType, name);
    }

}
