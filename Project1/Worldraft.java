public class Worldraft {

    private int worldHeight;
    private int worldWidth;

    private int playerHeight;
    private int playerWidth;

    private int monsterHeight;
    private int monsterWidth;

    public Worldraft() { //initial
        this.worldHeight=4;
        this.worldWidth=6;
        this.playerHeight=1;
        this.playerWidth=1;
        this.monsterHeight=2;
        this.monsterWidth=4;
    }

    public Worldraft(int playerHeight, int playerWidth, int monsterHeight, int monsterWidth) { //update
        this.playerHeight=playerHeight;
        this.playerWidth=playerWidth;
        this.monsterHeight=monsterHeight;
        this.monsterWidth=monsterWidth;
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

    /* Setter */
    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }

    public void setMonsterHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public void setMonsterWidth(int monsterWidth) {
        this.monsterWidth = monsterWidth;
    }


    //methods
//    public void method1(String name){

//        System.out.print(name);
      public void InitialWorld(){
          String playername="jasmyn";    // need to change to player.getName()
          String monstername="Angela";   // need to change to monster.getName()

        for (int i=0; i<=worldHeight-1; i++){
            for (int j=0; j<=worldWidth-1; j++){
                if (j == playerWidth && i == playerHeight ){
                    System.out.print(playername.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight){
                    System.out.print(monstername.toLowerCase().charAt(0));
                } else {System.out.print(".");}
            }
            System.out.print("\n");
        }
    }

    //North
    public int NorthMovement(int playerHeight, int playerWidth){
        String playerMovement="w";    // need to change to Scanner
        String playername="jasmyn";    // need to change to player.getName()
        String monstername="Angela";   // need to change to monster.getName()

        //restrict in top boundary
        if (this.playerHeight == 0){
            this.playerHeight=playerHeight;
        } else {
            this.playerHeight=playerHeight-1;
        }

            for (int i = 0; i <= worldHeight - 1; i++) {
                for (int j = 0; j <= worldWidth - 1; j++) {
                    if (j == playerWidth && i == this.playerHeight) {
                        System.out.print(playername.toUpperCase().charAt(0));
                    } else if (j == monsterWidth && i == monsterHeight) {
                        System.out.print(monstername.toLowerCase().charAt(0));
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.print("\n");
            }
            return this.playerHeight;
    }

    //South
    public int SouthMovement(int playerHeight, int playerWidth){
        String playerMovement="s";    // need to change to Scanner
        String playername="jasmyn";    // need to change to player.getName()
        String monstername="Angela";   // need to change to monster.getName()

        //restrict in bottom boundary
        if (this.playerHeight == worldHeight-1){
            this.playerHeight=playerHeight;
        } else {
            this.playerHeight=playerHeight+1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == playerWidth && i == this.playerHeight) {
                    System.out.print(playername.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monstername.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerHeight;
    }

    //West
    public int WestMovement(int playerHeight, int playerWidth){
        String playerMovement="a";    // need to change to Scanner
        String playername="jasmyn";    // need to change to player.getName()
        String monstername="Angela";   // need to change to monster.getName()

        //restrict in left boundary
        if (this.playerWidth == 0){
            this.playerWidth=playerWidth;
        } else {
            this.playerWidth=playerWidth-1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == this.playerWidth && i == playerHeight) {
                    System.out.print(playername.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monstername.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerWidth;
    }

    //East
    public int EastMovement(int playerHeight, int playerWidth){
        String playerMovement="d";    // need to change to Scanner
        String playername="jasmyn";    // need to change to player.getName()
        String monstername="Angela";   // need to change to monster.getName()

        //restrict in right boundary
        if(this.playerWidth == worldWidth-1){
            this.playerWidth=playerWidth;
        } else {
            this.playerWidth=playerWidth+1;
        }

        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == this.playerWidth && i == playerHeight) {
                    System.out.print(playername.toUpperCase().charAt(0));
                } else if (j == monsterWidth && i == monsterHeight) {
                    System.out.print(monstername.toLowerCase().charAt(0));
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerWidth;
    }







}











//test
//javac Worldraft.java && java Worldraft