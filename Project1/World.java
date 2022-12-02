/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */
public class World {

    private int worldHeight;
    private int worldWidth;

    private int playerHeight;
    private int playerWidth;

    private int playerInitialHeight = 1;
    private int playerInitialWidth = 1;

    private int monsterHeight;
    private int monsterWidth;

    private final int WORLD_HEIGHT_INITIAL = 4;
    private final int WORLD_WIDTH_INITIAL = 6;

    private final int MONSTER_HEIGHT_INITIAL = 2;
    private final int MONSTER_WIDTH_INITIAL = 4;

    public World() { //initial
        this.worldHeight = WORLD_HEIGHT_INITIAL;
        this.worldWidth = WORLD_WIDTH_INITIAL;
        this.playerHeight = playerInitialHeight;
        this.playerWidth = playerInitialWidth;
        this.monsterHeight = MONSTER_HEIGHT_INITIAL;
        this.monsterWidth = MONSTER_WIDTH_INITIAL;
    }

    /* Getter */
    public int getPlayerHeight() {
        return this.playerHeight;
    }

    public int getPlayerWidth() {
        return this.playerWidth;
    }

    public int getMonsterHeight() {
        return this.monsterHeight;
    }

    public int getMonsterWidth() {
        return this.monsterWidth;
    }

    public int getPlayerInitialHeight() {
        return this.playerInitialHeight;
    }

    public int getPlayerInitialWidth() {
        return this.playerInitialWidth;
    }

    /* Setter */
    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }


    //Displays the Initial World
    public void InitialWorld(String playerName, String monsterName){

        for (int i = 0; i <= worldHeight-1; i++){
            for (int j = 0; j <= worldWidth-1; j++){
                if (j == playerWidth && i == playerHeight ){
                    System.out.print(playerName.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight){
                    System.out.print(monsterName.toLowerCase().charAt(0));
                } else {System.out.print(".");}
            }
            System.out.print("\n");
        }
    }

    //Displays North movement
    public int NorthMovement(int playerHeight, int playerWidth,
                             String playerName, String monsterName){

        //restrict in top boundary
        if (this.playerHeight == 0){
            this.playerHeight = playerHeight;
        } else {
            this.playerHeight = playerHeight-1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == playerWidth && i == this.playerHeight) {
                    System.out.print(playerName.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monsterName.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerHeight;
    }

    //Displays South movement
    public int SouthMovement(int playerHeight, int playerWidth,
                             String playerName, String monsterName){

        //restrict in bottom boundary
        if (this.playerHeight == worldHeight-1){
            this.playerHeight = playerHeight;
        } else {
            this.playerHeight = playerHeight+1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == playerWidth && i == this.playerHeight) {
                    System.out.print(playerName.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monsterName.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerHeight;
    }

    //Displays West movement
    public int WestMovement(int playerHeight, int playerWidth,
                            String playerName, String monsterName){

        //restrict in left boundary
        if (this.playerWidth == 0){
            this.playerWidth = playerWidth;
        } else {
            this.playerWidth = playerWidth-1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == this.playerWidth && i == playerHeight) {
                    System.out.print(playerName.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monsterName.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerWidth;
    }

    //Displays East movement
    public int EastMovement(int playerHeight, int playerWidth,
                            String playerName, String monsterName){

        //restrict in right boundary
        if(this.playerWidth == worldWidth-1){
            this.playerWidth = playerWidth;
        } else {
            this.playerWidth = playerWidth+1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == this.playerWidth && i == playerHeight) {
                    System.out.print(playerName.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monsterName.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerWidth;
    }

}
