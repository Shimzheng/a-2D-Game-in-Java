/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

/**
 * A class for items, the child class of the Entity class
 */
public class Item extends Entity{

    /**
     * The type of the item
     */
    String itemType;

    /**
     * Set the type of the item
     *
     * @param itemType The type of the item
     */
    public Item(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Print the message according to the item type
     *
     * @param itemType the type of the entity
     */
    void printEntity(String itemType) {
        if (itemType.equals("+")) {
            System.out.println("Healed!");
        } else if (itemType.equals("^")){
            System.out.println("Attack up!");
        }
    }

    /**
     * A call for printing the message according to the item type
     */
    void printItemFunction(){
        this.printEntity(itemType);
    }

}
