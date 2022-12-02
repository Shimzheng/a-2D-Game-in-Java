/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

import java.util.ArrayList;

/**
 * A World class
 */
public class World {

    /**
     * the height of the world map
     */
    private int worldHeight;

    /**
     * the width of the world map
     */
    private int worldWidth;

    /**
     * the height of the player
     */
    private int playerHeight;

    /**
     * the width of the player
     */
    private int playerWidth;

    /**
     * the initial height of the player
     */
    private int playerInitialHeight = 1;

    /**
     * the initial width of the player
     */
    private int playerInitialWidth = 1;

    /**
     * the initial height of the monster
     */
    private int monsterHeight;

    /**
     * the initial width of the monster
     */
    private int monsterWidth;


    /**
     * the initial height of the map
     */
    private final int WORLD_HEIGHT_INITIAL = 4;

    /**
     * the initial width of the map
     */
    private final int WORLD_WIDTH_INITIAL = 6;

    /**
     * the initial height of the monster
     */
    private final int MONSTER_HEIGHT_INITIAL = 2;

    /**
     * the initial width of the monster
     */
    private final int MONSTER_WIDTH_INITIAL = 4;

    /**
     * the datFileName of the dat world map
     */
    private String datFileName;

    /**
     * the 2D arraylist of the current world
     */
    private ArrayList<ArrayList<String>> currentWorld = new ArrayList<ArrayList<String>>();

    /**
     * the damage of the player
     */
    private int playerDamage;

    /**
     * The default constructor of the World class
     */
    public World() { //initial
        this.worldHeight = WORLD_HEIGHT_INITIAL;
        this.worldWidth = WORLD_WIDTH_INITIAL;
        this.playerHeight = playerInitialHeight;
        this.playerWidth = playerInitialWidth;
        this.monsterHeight = MONSTER_HEIGHT_INITIAL;
        this.monsterWidth = MONSTER_WIDTH_INITIAL;
    }

    /**
     * Initialize the current world: empty map and initialization indicators of entities
     *
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public void initialCurrentWorld() throws GameLevelNotFoundException {

        for (int i = 0; i < datInitialWorld().size(); i++) {
            this.currentWorld.add(new ArrayList<String>());
        }

        for (int i = 0; i < datInitialWorld().size(); i++) {
            this.currentWorld.set(i, datInitialWorld().get(i));
        }
    }

    /**
     * Initialize the world before playing the dat world game
     *
     * @return the initial world
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public ArrayList<ArrayList<String>> setInitialCurrentWorld() throws GameLevelNotFoundException {
        return this.currentWorld = datInitialWorld();
    }

    /**
     * Initialize player's damage
     *
     * @param player the player in the game
     */
    public void playerDamageInitial(Player player){
        this.playerDamage = player.getDamage();
    }

    /* Getter */
    /**
     * Get the Y of the player
     *
     * @return the Y of the player
     */
    public int getPlayerHeight() {
        return this.playerHeight;
    }

    /**
     * Get the X of the player
     *
     * @return the X of the player
     */
    public int getPlayerWidth() {
        return this.playerWidth;
    }

    /**
     * Get the Y of the monster
     *
     * @return the Y of the monster
     */
    public int getMonsterHeight() {
        return this.monsterHeight;
    }

    /**
     * Get the X of the monster
     *
     * @return the X of the monster
     */
    public int getMonsterWidth() {
        return this.monsterWidth;
    }

    /**
     * Get the initial Y of the player
     *
     * @return the initial Y of the player
     */
    public int getPlayerInitialHeight() {
        return this.playerInitialHeight;
    }

    /**
     * Get the initial X of the player
     *
     * @return the initial X of the player
     */
    public int getPlayerInitialWidth() {
        return this.playerInitialWidth;
    }

    /**
     * Get the current world
     *
     * @return the current world
     */
    public ArrayList<ArrayList<String>> getCurrentWorld() {
        return this.currentWorld;
    }

    /* Setter */
    /**
     * Set the Y of the player
     *
     * @param playerHeight the Y of the player
     */
    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    /**
     * Set the X of the player
     *
     * @param playerWidth the X of the player
     */
    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }


    /**
     * Set the DatFileName of the world
     *
     * @param datFileName the file name of the dat world map
     */
    public void setDatFileName(String datFileName){
        this.datFileName = datFileName;
    }

    /**
     * Update the current world after removing the monster failed in the battle
     *
     * @param currentWorld the current world of the map before updating
     * @param monsterIndex the index of the monster failed in the battle
     * @return the current world after updating
     */
    public ArrayList<ArrayList<String>> updateCurrentWorld(ArrayList<ArrayList<String>> currentWorld,
                                                           int monsterIndex){

        currentWorld.remove(monsterIndex);

        return this.currentWorld = currentWorld;

    }

    /**
     * Print the first char of player's name
     *
     * @param playerName the name of the player
     */
    private void playerFirstChar(String playerName){
        Unit unitHere=new Unit();
        unitHere.setName(playerName);
        unitHere.setUnitType("player");
        unitHere.printUnitFunction();
    }

    /**
     * Print the first char of monster's name
     *
     * @param monsterName the name of the current monster
     */
    private void monsterFirstChar(String monsterName){
        Unit unitHere=new Unit();
        unitHere.setName(monsterName);
        unitHere.setUnitType("monster");
        unitHere.printUnitFunction();
    }

    /**
     * Display the initial world
     *
     * @param playerName the name of the player
     * @param monsterName monsterName the name of the current monster
     */
    public void initialWorld(String playerName, String monsterName){

        for (int i = 0; i <= worldHeight-1; i++){
            for (int j = 0; j <= worldWidth-1; j++){
                if (j == playerWidth && i == playerHeight){
                    playerFirstChar(playerName);
                } else if (j == monsterWidth && i == monsterHeight){
                    monsterFirstChar(monsterName);
                } else {System.out.print(".");}
            }
            System.out.print("\n");
        }
    }

    /**
     * Display north movement for 'start' game
     *
     * @param playerHeight the Y of the player
     * @param playerWidth the X of the player
     * @param playerName the name of the player
     * @param monsterName the name of the monster
     * @return the Y of the player
     */
    public int northMovement(int playerHeight, int playerWidth,
                             String playerName, String monsterName){

        //restrict in top boundary
        if (this.playerHeight == 0){
            this.playerHeight = playerHeight;
        } else {
            this.playerHeight = playerHeight-1;
        }

        return printNorthSouthMoveWorld(playerWidth, playerName, monsterName);
    }

    /**
     * Display south movement for 'start' game
     *
     * @param playerHeight the Y of the player
     * @param playerWidth the X of the player
     * @param playerName the name of the player
     * @param monsterName the name of the monster
     * @return the Y of the player
     */
    public int southMovement(int playerHeight, int playerWidth,
                             String playerName, String monsterName){

        //restrict in bottom boundary
        if (this.playerHeight == worldHeight-1){
            this.playerHeight = playerHeight;
        } else {
            this.playerHeight = playerHeight+1;
        }

        return printNorthSouthMoveWorld(playerWidth, playerName, monsterName);
    }

    /**
     * Display west movement for 'start' game
     *
     * @param playerHeight the Y of the player
     * @param playerWidth the X of the player
     * @param playerName the name of the player
     * @param monsterName the name of the monster
     * @return the X of the player
     */
    public int westMovement(int playerHeight, int playerWidth,
                            String playerName, String monsterName){

        //restrict in left boundary
        if (this.playerWidth == 0){
            this.playerWidth = playerWidth;
        } else {
            this.playerWidth = playerWidth-1;
        }

        return printEastWestMoveWorld(playerHeight, playerName, monsterName);

    }

    /**
     * Display west movement for 'start' game
     *
     * @param playerHeight the Y of the player
     * @param playerWidth the X of the player
     * @param playerName the name of the player
     * @param monsterName the name of the monster
     * @return the X of the player
     */
    //Displays East movement
    public int eastMovement(int playerHeight, int playerWidth,
                            String playerName, String monsterName){

        //restrict in right boundary
        if(this.playerWidth == worldWidth-1){
            this.playerWidth = playerWidth;
        } else {
            this.playerWidth = playerWidth+1;
        }

        return printEastWestMoveWorld(playerHeight, playerName, monsterName);
    }

    /**
     * Print player, monster, and world when the direction of movement of player is north/south
     *
     * @param playerWidth the X of the player
     * @param playerName the name of the player
     * @param monsterName the name of the monster
     * @return the Y of the player
     */
    private int printNorthSouthMoveWorld(int playerWidth, String playerName, String monsterName) {
        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == playerWidth && i == this.playerHeight) {
                    playerFirstChar(playerName);

                } else if (j == monsterWidth && i == monsterHeight) {
                    monsterFirstChar(monsterName);

                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerHeight;
    }

    /**
     * Print player, monster, and world when the direction of movement of player is east/west
     *
     * @param playerHeight the Y of the player
     * @param playerName the name of the player
     * @param monsterName the name of the monster
     * @return the X of the player
     */
    private int printEastWestMoveWorld(int playerHeight, String playerName, String monsterName) {
        for (int i = 0; i <= worldHeight - 1; i++) {
            for (int j = 0; j <= worldWidth - 1; j++) {
                if (j == this.playerWidth && i == playerHeight) {
                    playerFirstChar(playerName);

                } else if (j == monsterWidth && i == monsterHeight) {
                    monsterFirstChar(monsterName);

                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        return this.playerWidth;
    }

    /**
     * Get a map of dat file without any entities
     *
     * @return A 2D arraylist of the initial map of dat file without entities printed
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public ArrayList<ArrayList<String>> datInitialWorld()
            throws GameLevelNotFoundException {

        ArrayList<ArrayList<String>> wholeArrayList = new ArrayList<ArrayList<String>>();

            //Get the infos in the dat file
            ArrayList<String> worldMap = GameEngine.tryReadDatFile(datFileName);

            //store the data in arraylist to array
            String[] array = worldMap.toArray(new String[0]);

            //worldMap's Width and Height
            String[] Str = array[0].split(" ", 2);
            int worldMapWidth = Integer.parseInt(Str[0]);
            int worldMapHeight = Integer.parseInt(Str[1]);

            //player's Width and Height
            String[] pStr = array[worldMapHeight + 1].split(" ", 3);
            int pWidth = Integer.parseInt(pStr[1]);
            int pHeight = Integer.parseInt(pStr[2]);

            //monster's info
            int monsterNum = getMonsterNum(array);
            String[][] monsterArray = getMonster2DArray(monsterNum, array, worldMapHeight);

            //item's info
            int itemNum = getItemNum(array);
            String[][] itemArray = getItem2DArray(itemNum, monsterNum, array, worldMapHeight);

            //print the worldMap
            ArrayList<ArrayList<String>> worldMapArrayList = getWorldMapArrayList(worldMapHeight,
                    worldMapWidth,
                    worldMap);

            //get whole map array list and Add map info and player info below the map
            getWholeMapArrayList(worldMapHeight,
                    monsterNum,
                    itemNum,
                    worldMapWidth,
                    pWidth,
                    pHeight,
                    wholeArrayList,
                    worldMapArrayList,
                    monsterArray,
                    itemArray);

        return wholeArrayList;
    }

    /**
     * Display the Initial World from dat file
     *
     * @param playerName the name of the player
     * @return A 2D arraylist of the initial map of dat file with entities printed
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public ArrayList<ArrayList<String>> datInitialWorld(String playerName)
            throws GameLevelNotFoundException {

        ArrayList<ArrayList<String>> wholeArrayList = new ArrayList<ArrayList<String>>();

            //Get the infos in the dat file
            ArrayList<String> worldMap = GameEngine.tryReadDatFile(datFileName);

            //store the data in arraylist to array
            String[] array = worldMap.toArray(new String[0]);

            //worldMap's Width and Height
            String[] Str = array[0].split(" ", 2);
            int worldMapWidth = Integer.parseInt(Str[0]);
            int worldMapHeight = Integer.parseInt(Str[1]);

            //player's Width and Height
            String[] pStr = array[worldMapHeight + 1].split(" ", 3);
            int pWidth = Integer.parseInt(pStr[1]);
            int pHeight = Integer.parseInt(pStr[2]);

            //monster's info
            int monsterNum = getMonsterNum(array);
            String[][] monsterArray = getMonster2DArray(monsterNum, array, worldMapHeight);

            //item's info
            int itemNum = getItemNum(array);
            String[][] itemArray = getItem2DArray(itemNum, monsterNum, array, worldMapHeight);

            //print the worldMap
            ArrayList<ArrayList<String>> worldMapArrayList = getWorldMapArrayList(worldMapHeight,
                    worldMapWidth,
                    worldMap);

            /*
            Print entities into world map
             */
            //1) print player into world map
            worldMapArrayList.get(pHeight).set(pWidth,
                    String.valueOf(playerName.toUpperCase().charAt(0)));

            //2) print monsters into world map
            for (int m = 0; m < monsterNum; m++) {
                worldMapArrayList.get(Integer.parseInt(
                        monsterArray[m][2])).set(Integer.parseInt(monsterArray[m][1]),
                        String.valueOf(monsterArray[m][3].toLowerCase().charAt(0)));
            }

            //3) print items into world map
            for (int i = 0; i < itemNum; i++) {
                worldMapArrayList.get(Integer.parseInt(
                        itemArray[i][2])).set(Integer.parseInt(itemArray[i][1]),
                        String.valueOf(itemArray[i][3]));
            }

            //get whole map array list and Add map info and player info below the map
            getWholeMapArrayList(worldMapHeight,
                    monsterNum,
                    itemNum,
                    worldMapWidth,
                    pWidth,
                    pHeight,
                    wholeArrayList,
                    worldMapArrayList,
                    monsterArray,
                    itemArray);

        return wholeArrayList;
    }

    /**
     * Get the total number of the monsters in the dat file
     *
     * @param array an array of the dat world map
     * @return the total number of the monsters in the dat file
     */
    private int getMonsterNum(String[] array){

        int monsterNum = 0;

        for (String s : array) {
            char firstChar = s.charAt(0);
            if (firstChar == 'm') {
                monsterNum = monsterNum + 1;
            }
        }

        return monsterNum;

    }

    /**
     * Put all monsters of the dat file world into a 2D array
     *
     * @param monsterNum the total number of the monsters
     * @param array an array of the dat world map
     * @param worldMapHeight The height of the dat world map
     * @return a 2D array contained all monsters
     */
    private String[][] getMonster2DArray(int monsterNum, String[] array, int worldMapHeight){

        String[][] monsterArray = new String[monsterNum][6];

        for (int i = 0; i < monsterNum; i++) {
            String[] mStr = array[worldMapHeight + 2 + i].split(" ", 6);
            for (int j = 0; j < 6; j++) {
                monsterArray[i][j] = mStr[j];
            }
        }

        return monsterArray;

    }

    /**
     * Get the total number of the items in the dat file
     *
     * @param array an array of the dat world map
     * @return the total number of the items in the dat file
     */
    private int getItemNum(String[] array){

        int itemNum = 0;

        for (String s : array) {
            char firstChar = s.charAt(0);
            if (firstChar == 'i') {
                itemNum = itemNum + 1;
            }
        }

        return itemNum;

    }

    /**
     * Put all items of the dat file world into a 2D array
     *
     * @param itemNum the total number of the items
     * @param monsterNum the total number of the monsters
     * @param array an array of the dat world map
     * @param worldMapHeight The height of the dat world map
     * @return a 2D array contained all items
     */
    private String[][] getItem2DArray(int itemNum,
                                      int monsterNum,
                                      String[] array,
                                      int worldMapHeight){

        String[][] itemArray = new String[itemNum][4];

        for (int i = 0; i < itemNum; i++) {
            String[] iStr = array[worldMapHeight + 2 + monsterNum + i].split(" ", 4);
            for (int j = 0; j < 4; j++) {
                itemArray[i][j] = iStr[j];
            }
        }

        return itemArray;

    }

    /**
     * Get an array list of the dat world map
     *
     * @param worldMapHeight the height of the world map
     * @param worldMapWidth the width of the world map
     * @param worldMap an array of the dat world map
     * @return an array list of the dat world map
     */
    private ArrayList<ArrayList<String>> getWorldMapArrayList(int worldMapHeight,
                                                              int worldMapWidth,
                                                              ArrayList<String> worldMap){

        ArrayList<ArrayList<String>> worldMapArrayList = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < worldMapHeight; i++) {
            worldMapArrayList.add(new ArrayList<String>());
        }

        for (int i = 1; i < worldMapHeight + 1; i++) {
            for (int j = 0; j < worldMapWidth; j++) {
                worldMapArrayList.get(i - 1).add(j, String.valueOf(worldMap.get(i).charAt(j)));
            }
        }

        return worldMapArrayList;

    }

    /**
     * Get whole map array list and
     * add other infos (map's infos, monsters' infos, items' infos, and player's infos)
     * below the map
     *
     * @param worldMapHeight the height of the world map
     * @param monsterNum the total number of the monsters
     * @param itemNum the total number of the items
     * @param worldMapWidth the width of the world map
     * @param pWidth the X of the player
     * @param pHeight the Y of the player
     * @param wholeArrayList an initial 2D array list used to
     *                       set the infos and contained the other infos.
     * @param worldMapArrayList an initial 2D array list used to
     *                          set the infos without the other infos.
     * @param monsterArray the array contained all monsters
     * @param itemArray the array contained all items
     */
    private void getWholeMapArrayList(int worldMapHeight,
                                      int monsterNum,
                                      int itemNum,
                                      int worldMapWidth,
                                      int pWidth,
                                      int pHeight,
                                      ArrayList<ArrayList<String>> wholeArrayList,
                                      ArrayList<ArrayList<String>>  worldMapArrayList,
                                      String[][] monsterArray,
                                      String[][] itemArray){

        int lineNumInDat = worldMapHeight+monsterNum+itemNum+1+1;

        for (int i = 0; i < lineNumInDat; i++) {
            wholeArrayList.add(new ArrayList<String>());
        }

        //map
        for (int i = 0; i < worldMapArrayList.size(); i++) {
            for (int j = 0; j < worldMapArrayList.get(0).size(); j++) {
                wholeArrayList.get(i).add(j, worldMapArrayList.get(i).get(j));
            }
        }

        //Add monsters' info below the map
        for (int i = 0; i < monsterNum; i++) {
            for (int j = 0; j < monsterArray[0].length; j++) {
                wholeArrayList.get(worldMapHeight+i).add(j, monsterArray[i][j]);
            }
        }

        //Add items' info below the map
        for (int i = 0; i < itemNum; i++) {
            for (int j = 0; j < itemArray[0].length; j++) {
                wholeArrayList.get(worldMapHeight+monsterNum+i).add(j, itemArray[i][j]);
            }
        }

        //Add map's info below the map (Width, Height)
        wholeArrayList.get(worldMapHeight+monsterNum+itemNum).add(0,
                String.valueOf(worldMapWidth));
        wholeArrayList.get(worldMapHeight+monsterNum+itemNum).add(1,
                String.valueOf(worldMapHeight));

        //Add player info below the map (width, height, level, damage, currentHealth, maxHealth)
        wholeArrayList.get(worldMapHeight+monsterNum+itemNum+1).add(0,
                String.valueOf(pWidth));
        wholeArrayList.get(worldMapHeight+monsterNum+itemNum+1).add(1,
                String.valueOf(pHeight));
    }

    /**
     * Print initial map with the entities only
     *
     * @param playerName the name of the player
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public void printInitialMapOnly(String playerName) throws GameLevelNotFoundException {
        int mapWidth = 0;
        int mapHeight = 0;
        mapWidth = Integer.parseInt(
                datInitialWorld(playerName).get(datInitialWorld(playerName).size()-2).get(0));

        mapHeight = Integer.parseInt(
                datInitialWorld(playerName).get(datInitialWorld(playerName).size()-2).get(1));

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                System.out.print(datInitialWorld(playerName).get(i).get(j));
            }
            System.out.print("\n");
        }

    }

    /**
     * According to the w/s/a/d command for movement direction
     * of the user input, check whether the player can move;
     * Determine if the player encounters item
     *
     * @param player the player in the game
     * @param moveDirection the command for player's movement direction entered by user
     * @param currentWorld the current world map
     * @param playerDamage the player's damage
     * @param playerCurrentHealth the player's current health
     * @param playerMaxHealth the player's max health
     * @return a 2D array list of the current world map
     */
    public ArrayList<ArrayList<String>> movementTest(Player player,
                                                     String moveDirection,
                                                     ArrayList<ArrayList<String>> currentWorld,
                                                          int playerDamage,
                                                          int playerCurrentHealth,
                                                          int playerMaxHealth){

        //1) Deep copy currentWorld to this.currentWorld
        for (int i = 0; i < currentWorld.size(); i++) {
            this.currentWorld.set(i, currentWorld.get(i));
        }

        //2) Take the map's info
        int mapWidth = Integer.parseInt(currentWorld.get(currentWorld.size()-2).get(0));
        int mapHeight = Integer.parseInt(currentWorld.get(currentWorld.size()-2).get(1));

        //3) Take the num of monsters and items
        int monsterNumInitial=0;
        int itemNumInitial=0;
        for (ArrayList<String> strings : currentWorld) {
            char firstChar = strings.get(0).charAt(0);
            if (firstChar == 'm') {
                monsterNumInitial++;
            } else if (firstChar == 'i') {
                itemNumInitial++;
            }
        }

        //4) Update the Entities' indicators based on the movement
        // Take the player's info
        int pWidth=Integer.parseInt(currentWorld.get(currentWorld.size()-1).get(0));
        int pHeight=Integer.parseInt(currentWorld.get(currentWorld.size()-1).get(1));


        // Determine the move direction of player is north/south/West/East
        // Check if the player can move
            //1) The move direction is north
            if (moveDirection.equals("w")) {
                int heightChange;

                //player cannot move because of out of map boundaries
                if (pHeight == 0) {
                    heightChange = 0;

                //player cannot move because of obstacle #/~
                } else if (currentWorld.get(pHeight - 1).get(pWidth).equals("#")
                        || currentWorld.get(pHeight - 1).get(pWidth).equals("~")) {
                    heightChange = 0;

                // player can move (height change by north movement)
                } else {
                    heightChange = -1;
                }

                //update player's info into the map
                pHeight = Integer.parseInt(
                        currentWorld.get(currentWorld.size() - 1).get(1)) + heightChange;
                this.currentWorld.get(currentWorld.size() - 1).set(1, String.valueOf(pHeight));

              //2) The move direction is south
            } else if (moveDirection.equals("s")) {
                int heightChange;

                //player cannot move because of out of map boundaries
                if (pHeight == mapHeight - 1) {
                    heightChange = 0;

                //player cannot move because of obstacle #/~
                } else if (currentWorld.get(pHeight + 1).get(pWidth).equals("#")
                        || currentWorld.get(pHeight + 1).get(pWidth).equals("~")) {
                    heightChange = 0;

                // player can move (height change by south movement)
                } else {
                    heightChange = 1;
                }

                //update player's info into the map
                pHeight = Integer.parseInt(
                        currentWorld.get(currentWorld.size() - 1).get(1)) + heightChange;
                this.currentWorld.get(currentWorld.size() - 1).set(1, String.valueOf(pHeight));

            //3) The move direction is west
            } else if (moveDirection.equals("a")) {
                int widthChange;

                //player cannot move because of out of map boundaries
                if (pWidth == 0) {
                    widthChange = 0;

                //player cannot move because of obstacle #/~
                } else if (currentWorld.get(pHeight).get(pWidth - 1).equals("#")
                        || currentWorld.get(pHeight).get(pWidth - 1).equals("~")) {
                    widthChange = 0;

                // player can move (width change by west movement)
                } else {
                    widthChange = -1;
                }

                //update player's info into the map
                pWidth = Integer.parseInt(
                        currentWorld.get(currentWorld.size() - 1).get(0)) + widthChange;
                this.currentWorld.get(currentWorld.size() - 1).set(0, String.valueOf(pWidth));

              //4) The move direction is east
            } else if (moveDirection.equals("d")) {
                int widthChange;

                //player cannot move because of out of map boundaries
                if (pWidth == mapWidth - 1) {
                    widthChange = 0;

                //player cannot move because of obstacle #/~
                } else if (currentWorld.get(pHeight).get(pWidth + 1).equals("#")
                        || currentWorld.get(pHeight).get(pWidth + 1).equals("~")) {
                    widthChange = 0;

                // player can move (width change by east movement)
                } else {
                    widthChange = 1;
                }

                //update player's info into the map
                pWidth = Integer.parseInt(
                        currentWorld.get(currentWorld.size() - 1).get(0)) + widthChange;
                this.currentWorld.get(currentWorld.size() - 1).set(0, String.valueOf(pWidth));

            }

        //Determine if the player encounters an item
        for (int i = mapHeight + monsterNumInitial;
             i < mapHeight + monsterNumInitial + itemCurrentNum(currentWorld);
             i++) {

            int itemWidth = Integer.parseInt(currentWorld.get(i).get(1));
            int itemHeight = Integer.parseInt(currentWorld.get(i).get(2));
            String itemChar = currentWorld.get(i).get(3);

            if (pWidth == itemWidth && pHeight == itemHeight) {
                if (itemChar.equals("^")) {             //player encounters ^
                    Item itemHere=new Item(itemChar);
                    itemHere.printItemFunction();
                    setUpDamage(playerDamage); // playerDamage++;

                } else if (itemChar.equals("+")) {      //player encounters +
                    Item itemHere=new Item(itemChar);
                    itemHere.printItemFunction();
                    playerCurrentHealth = playerMaxHealth;
                    player.setCurrentHealth(player.getMaxHealth());
                }

                currentWorld.remove(i);

            }
        }

        return this.currentWorld;

    }


    /**
     * Get the updated player damage after encountering the item ^
     *
     * @return the player's damage
     */
    public int getUpDamage(){
        return this.playerDamage;
    }

    /**
     * Increase a unit for player's damage after encountering the item ^
     *
     * @param playerDamage the player's current damage
     */
    public void setUpDamage(int playerDamage){
        this.playerDamage = playerDamage + 1;
    }

    /**
     * Reset the player's damage after entering 'home' command
     *
     * @param playerDamage the player's current damage
     */
    public void resetDamage(int playerDamage){
        this.playerDamage = playerDamage;
    }

    /**
     * Calculate the latest number of monsters in the current world
     *
     * @param currentWorld the current world
     * @return the latest number of monsters
     */
    public int monsterCurrentNum(ArrayList<ArrayList<String>> currentWorld){

        int monsterNum=0;

        for (ArrayList<String> strings : currentWorld) {
            char firstChar = strings.get(0).charAt(0);
            if (firstChar == 'm') {
                monsterNum++;
            }
        }
        return monsterNum;
    }

    /**
     * Calculate the latest number of items in the current world
     *
     * @param currentWorld the current world
     * @return the latest number of items
     */
    public int itemCurrentNum(ArrayList<ArrayList<String>> currentWorld){

        int itemNum=0;

        for (ArrayList<String> strings : currentWorld) {
            char firstChar = strings.get(0).charAt(0);
            if (firstChar == 'i') {
                itemNum++;
            }
        }
        return itemNum;
    }

    /**
     * Determine if each monster can move;
     * Judge that the player encounters monster, and if so, enter the battle loop;
     * Test whether the player is ultimately defeated.
     *
     * @param player the player in the game
     * @param moveDirection the command for player's movement direction entered by user
     * @param playerName the name of the player
     * @param playerDamage the player's damage
     * @param playerCurrentHealth the player's current health
     * @param playerMaxHealth the player's max health
     * @param currentWorld the array list of the current world
     * @return An array contained playerHealthResult, monsterName,
     * monsterCurrenHealth, and monsterMaxHealth
     */
    public ArrayList<String> monsterMovement(Player player, String moveDirection,
                                String playerName,
                                int playerDamage,
                                int playerCurrentHealth,
                                int playerMaxHealth,
                                ArrayList<ArrayList<String>> currentWorld){

        //Take the map indicator
        int mapWidth = Integer.parseInt(currentWorld.get(currentWorld.size()-2).get(0));
        int mapHeight = Integer.parseInt(currentWorld.get(currentWorld.size()-2).get(1));

        //Take the player's info
        int pWidth=Integer.parseInt(currentWorld.get(currentWorld.size()-1).get(0));
        int pHeight=Integer.parseInt(currentWorld.get(currentWorld.size()-1).get(1));

        int playerHealthResult=0;

        //Create an array for return: [playerHealthResult, monsterName,
        // monsterCurrenHealth, monsterMaxHealth]
        ArrayList<String> pmCurrentResult = new ArrayList<String>();
        pmCurrentResult.add("null");
        pmCurrentResult.add("null");
        pmCurrentResult.add("null");
        pmCurrentResult.add("null");

        // Take the coordinates of each monster separately to determine whether the monster can move
        // if it can, update the coordinates
        for (int i = 0; i < monsterCurrentNum(currentWorld); i++) {

            //Take the coordinates of the current monster
            int mWidth = Integer.parseInt(currentWorld.get(mapHeight + i).get(1));
            int mHeight = Integer.parseInt(currentWorld.get(mapHeight + i).get(2));

            //1) monster determines that the player is within 2 blocks of its 'left' side
            // (and within 2 blocks of top and bottom)
            //monster determines that the player is within 2 blocks of its 'left' side
            // (and within 2 blocks of top and bottom)
            if ((mWidth <= pWidth + 2  &&  pWidth < mWidth)
                    && ((mHeight <= pHeight + 2 && pHeight <= mHeight)
                    || (mHeight <= pHeight && pHeight <= mHeight + 2))) {

                //the monster 'left' movement is blocked by #/~
                if (currentWorld.get(mHeight).get(mWidth - 1).equals("#")
                        || currentWorld.get(mHeight).get(mWidth - 1).equals("~")){

                    //determine if it can move upwards
                    mHeight = getLeftRightMonsterHeight(currentWorld,
                            mapHeight,
                            pHeight,
                            i,
                            mWidth,
                            mHeight);

                 //the monster can make 'left' movement
                }else{
                    mWidth--;
                    this.currentWorld.get(mapHeight + i).set(1, String.valueOf(mWidth));
                }

              //2) monster determines that the player is within 2 blocks on its 'right' side
            } else if ((mWidth < pWidth && pWidth <= mWidth + 2)
                    && ((mHeight <= pHeight + 2 && pHeight <= mHeight)
                    || (mHeight <= pHeight && pHeight <= mHeight + 2))) {

                //the monster 'right' movement is blocked by #/~
                if (currentWorld.get(mHeight).get(mWidth + 1).equals("#")
                        || currentWorld.get(mHeight).get(mWidth + 1).equals("~")){

                    //determine if it can move upwards
                    mHeight = getLeftRightMonsterHeight(currentWorld,
                            mapHeight,
                            pHeight,
                            i,
                            mWidth,
                            mHeight);

                 //the monster can make 'right' movement
                }else{
                    mWidth++;
                    this.currentWorld.get(mapHeight + i).set(1, String.valueOf(mWidth));
                }


                //3) monster determines that the player is within 2 blocks of its 'up' square
            } else if (mHeight <= pHeight + 2 && pHeight < mHeight && mWidth == pWidth) {

                mHeight = getUpMonsterHeight(currentWorld,
                        mapHeight,
                        i,
                        mWidth,
                        mHeight);

                //4) monster determines that the player is within 2 blocks of its 'down' side
            } else if (mHeight < pHeight && pHeight <= mHeight + 2 && mWidth == pWidth) {

                mHeight = getDownHeight(currentWorld,
                        mapHeight,
                        i,
                        mWidth,
                        mHeight);

            }

            //Take monster info
            String monsterName = currentWorld.get(mapHeight + i).get(3);
            int monsterDamage = Integer.parseInt(currentWorld.get(mapHeight + i).get(5));
            int monsterMaxHealth = Integer.parseInt(currentWorld.get(mapHeight + i).get(4));
            int monsterCurrentHealth = monsterMaxHealth;

            pmCurrentResult.set(0, String.valueOf(0));
            pmCurrentResult.set(1, monsterName);
            pmCurrentResult.set(2, String.valueOf(monsterMaxHealth));
            pmCurrentResult.set(3, String.valueOf(monsterMaxHealth));

            // Determine whether the player encounters a monster after moving, and
            // if so, enter the battle loop
            if((moveDirection.equals("w") && pHeight==mHeight+1 && pWidth==mWidth)
                    || (moveDirection.equals("s") && pHeight+1==mHeight && pWidth==mWidth)
                    || (moveDirection.equals("a") && pHeight==mHeight && pWidth==mWidth+1)
                    || (moveDirection.equals("d") && pHeight==mHeight && pWidth+1==mWidth)) {

                playerHealthResult = changeAfterPlayerEncounterMonster(playerName,
                        playerCurrentHealth,
                        playerMaxHealth,
                        monsterName,
                        monsterDamage,
                        monsterMaxHealth,
                        monsterCurrentHealth,
                        playerHealthResult,
                        pmCurrentResult,
                        mapHeight,
                        i,
                        player);

                //Check if the player fails
                if (playerHealthResult < 0) {

                    // The damage reward given by the skill "^" will be reset
                    resetDamage(playerDamage);
                    break;
                }
            }

        }

        // Contains [playerHealthResult, monsterName, monsterCurrenHealth, monsterMaxHealth]
        return pmCurrentResult;

    }

    /**
     * Determine if the monster can move upwards if the monster can not make left/right movement
     *
     * @param currentWorld the 2D array list of the current world
     * @param mapHeight the height of the map
     * @param pHeight the Y of the player
     * @param i the index of the monster
     * @param mWidth the X of the monster
     * @param mHeight the Y of the monster
     * @return the height of the current monster
     */
    private int getLeftRightMonsterHeight(ArrayList<ArrayList<String>> currentWorld,
                                          int mapHeight,
                                          int pHeight,
                                          int i,
                                          int mWidth,
                                          int mHeight) {

        if (mHeight <= pHeight + 2 && pHeight < mHeight){ //p左侧有#/~, 在m上方
            mHeight = getUpMonsterHeight(currentWorld, mapHeight, i, mWidth, mHeight);

            //判断 monster '左'移动有#/~后是否能向下移动
        }else if (mHeight < pHeight && pHeight <= mHeight + 2){ //p左侧有#/~, 在m下方
            mHeight = getDownHeight(currentWorld, mapHeight, i, mWidth, mHeight);
        }

        this.currentWorld.get(mapHeight + i).set(1, String.valueOf(mWidth));
        return mHeight;
    }

    /**
     * Determine if the monster can move upwards
     *
     * @param currentWorld the 2D array list of the current world
     * @param mapHeight the height of the map
     * @param i the index of the monster
     * @param mWidth the X of the monster
     * @param mHeight the Y of the monster
     * @return the height of the current monster
     */
    private int getUpMonsterHeight(ArrayList<ArrayList<String>> currentWorld,
                                   int mapHeight,
                                   int i,
                                   int mWidth,
                                   int mHeight) {

        if (currentWorld.get(mHeight - 1).get(mWidth).equals("#")
                || currentWorld.get(mHeight - 1).get(mWidth).equals("~")){
            this.currentWorld.get(mapHeight + i).set(1, String.valueOf(mWidth));
        }else{
            mHeight--;
            this.currentWorld.get(mapHeight + i).set(2, String.valueOf(mHeight));
        }
        return mHeight;
    }

    /**
     * Determine if the monster can move downwards
     *
     * @param currentWorld the 2D array list of the current world
     * @param mapHeight the height of the map
     * @param i the index of the monster
     * @param mWidth the X of the monster
     * @param mHeight the Y of the monster
     * @return the height of the current monster
     */
    private int getDownHeight(ArrayList<ArrayList<String>> currentWorld,
                              int mapHeight,
                              int i,
                              int mWidth,
                              int mHeight) {

        if (currentWorld.get(mHeight + 1).get(mWidth).equals("#")
                || currentWorld.get(mHeight + 1).get(mWidth).equals("~")){
            this.currentWorld.get(mapHeight + i).set(1, String.valueOf(mWidth));
        }else{
            mHeight++;
            this.currentWorld.get(mapHeight + i).set(2, String.valueOf(mHeight));
        }
        return mHeight;
    }

    /**
     * Infos updates after the battle loop
     *
     * @param playerName the name of the player
     * @param playerCurrentHealth the player's current health
     * @param playerMaxHealth the player's max health
     * @param monsterName the name of the monster
     * @param monsterDamage the monster's damage
     * @param monsterMaxHealth  the monster's max health
     * @param monsterCurrentHealth the monster's current health
     * @param playerHealthResult a variable used to save the return value
     * @param pmCurrentResult  an array contains playerHealthResult, monsterName,
     *                         monsterCurrenHealth, and monsterMaxHealth
     * @param mapHeight the height of the map
     * @param i the index of the monster
     * @param player the player in the game
     * @return the player's health after the battle
     */
    public int changeAfterPlayerEncounterMonster(String playerName,
                                                 int playerCurrentHealth,
                                                 int playerMaxHealth,
                                                 String monsterName,
                                                 int monsterDamage,
                                                 int monsterMaxHealth,
                                                 int monsterCurrentHealth,
                                                 int playerHealthResult,
                                                 ArrayList<String> pmCurrentResult,
                                                 int mapHeight,
                                                 int i,
                                                 Player  player){
        encounterMonster(playerName,
                getUpDamage(), //  update player damage by item
                playerCurrentHealth,
                playerMaxHealth,
                monsterName,
                monsterDamage,
                monsterMaxHealth, // CurrentHealth initialization
                monsterMaxHealth);

        //battle
        ArrayList<Integer> pmCurrentHealthArray=battleLoop(playerName,
                getUpDamage(), // update player damage by item
                playerCurrentHealth,
                playerMaxHealth,
                monsterName,
                monsterDamage,
                monsterCurrentHealth,
                monsterMaxHealth);

        playerHealthResult=pmCurrentHealthArray.get(0);
        monsterCurrentHealth=pmCurrentHealthArray.get(1);

        //Record the return value
        pmCurrentResult.set(0, String.valueOf(playerHealthResult));
        pmCurrentResult.set(2, String.valueOf(monsterCurrentHealth));

        //Remove the monster encountered
        currentWorld.remove(mapHeight + i);

        //Update the player's current health
        player.setCurrentHealth(playerHealthResult);

        return playerHealthResult;
    }


    /**
     * Print the map after movement of all entities
     *
     * @param playerName the name of the player
     * @param currentWorld the 2D array list of the current world
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public void printResultMap(String playerName,
                               ArrayList<ArrayList<String>> currentWorld)
            throws GameLevelNotFoundException {

        // map's info
        int mapWidth = Integer.parseInt(currentWorld.get(currentWorld.size()-2).get(0));
        int mapHeight = Integer.parseInt(currentWorld.get(currentWorld.size()-2).get(1));

        // player's info
        int pWidth=Integer.parseInt(currentWorld.get(currentWorld.size()-1).get(0));
        int pHeight=Integer.parseInt(currentWorld.get(currentWorld.size()-1).get(1));

        //Create an empty map without entities according to the map indicator
        ArrayList<ArrayList<String>> worldMapCopy = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < mapHeight; i++) {
            worldMapCopy.add(new ArrayList<String>());
        }

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                worldMapCopy.get(i).add(j, datInitialWorld().get(i).get(j));
            }
        }

        //The entities will be printed in an empty map
        // according to the new entities' indicator from the current world
        int monsterNum=0;
        int itemNum=0;
        for (ArrayList<String> strings : currentWorld) {
            char firstChar = strings.get(0).charAt(0);
            if (firstChar == 'm') {
                monsterNum = monsterNum + 1;
            } else if (firstChar == 'i') {
                itemNum = itemNum + 1;
            }
        }

        //1) print items into world map
        for (int i = 0; i < itemNum; i++) {
            int iWidth=Integer.parseInt(currentWorld.get(mapHeight+monsterNum+i).get(1));
            int iHeight=Integer.parseInt(currentWorld.get(mapHeight+monsterNum+i).get(2));
            String mChar=currentWorld.get(mapHeight+monsterNum+i).get(3);
            worldMapCopy.get(iHeight).set(iWidth, String.valueOf(mChar));
        }

        //2) print monsters into world map
        //Add the 1st monster
        int mmmWidthFirst=Integer.parseInt(currentWorld.get(mapHeight).get(1));
        int mmmHeightFirst=Integer.parseInt(currentWorld.get(mapHeight).get(2));
        char mCharFirst=currentWorld.get(mapHeight).get(3).toLowerCase().charAt(0);
        worldMapCopy.get(mmmHeightFirst).set(mmmWidthFirst, String.valueOf(mCharFirst));

        //Add the 2nd and other monsters
        // (only the first one will be shown
        // if the coordinate of the 2nd and other monsters are the same as the first one)
        for (int m = 1; m < monsterNum ; m++) {

            //current monster
            int mmmWidth=Integer.parseInt(currentWorld.get(mapHeight+m).get(1));
            int mmmHeight=Integer.parseInt(currentWorld.get(mapHeight+m).get(2));
            char mChar=currentWorld.get(mapHeight+m).get(3).toLowerCase().charAt(0);

            worldMapCopy.get(mmmHeight).set(mmmWidth, String.valueOf(mChar));

            //current monster vs. the monster before the current monster
            for (int k = 0; k<=m-1 ; k++){
                int mmmWidthBefore=Integer.parseInt(currentWorld.get(mapHeight+k).get(1));
                int mmmHeightBefore=Integer.parseInt(currentWorld.get(mapHeight+k).get(2));
                char mCharBefore=currentWorld.get(mapHeight+k).get(3).toLowerCase().charAt(0);

                if (mmmWidth == mmmWidthBefore && mmmHeight == mmmHeightBefore) {
                    worldMapCopy.get(mmmHeightBefore).set(mmmWidthBefore,
                            String.valueOf(mCharBefore));
                }

            }

        }

        //3) print player into world map
        char pChar=playerName.toUpperCase().charAt(0);
        worldMapCopy.get(pHeight).set(pWidth, String.valueOf(pChar));

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                System.out.print(worldMapCopy.get(i).get(j));
            }
            System.out.print("\n");
        }
    }

    /**
     * Determine if each monster can move;
     * Judge that the player encounters monster, and if so, enter the battle loop;
     * Test whether the player is ultimately defeated.
     *
     * @param playerName the name of the player
     * @param playerDamage the player's damage
     * @param playerCurrentHealth the player's current health
     * @param playerMaxHealth the player's max health
     * @param monsterName the name of the monster
     * @param monsterDamage the monster's damage
     * @param monsterCurrentHealth the monster's current health
     * @param monsterMaxHealth the monster's max health
     */
    public void encounterMonster(String playerName,
                                int playerDamage,
                                int playerCurrentHealth,
                                int playerMaxHealth,
                                String monsterName,
                                int monsterDamage,
                                int monsterCurrentHealth,
                                int monsterMaxHealth) {

        System.out.println(playerName + " encountered a " + monsterName + "!\n");

        System.out.println(playerName + " " +
                playerCurrentHealth + "/" +
                playerMaxHealth + " | " +
                monsterName + " " +
                monsterCurrentHealth + "/" +
                monsterMaxHealth);

        System.out.println(playerName + " attacks " + monsterName
                + " for " + playerDamage + " damage."+"\n"
                + monsterName + " attacks " + playerName
                + " for " + monsterDamage + " damage.\n");
    }


    /**
     * The implementation for the battle loop
     *
     * @param playerName the name of the player
     * @param playerDamage the player's damage
     * @param playerCurrentHealth the player's current health
     * @param playerMaxHealth the player's max health
     * @param monsterName the name of the monster
     * @param monsterDamage the monster's damage
     * @param monsterCurrentHealth the monster's current health
     * @param monsterMaxHealth the monster's max health
     * @return An array list contains the current health of player and monster
     */
    public ArrayList<Integer> battleLoop(String playerName,
                                         int playerDamage,
                                        int playerCurrentHealth,
                                        int playerMaxHealth,
                                        String monsterName,
                                        int monsterDamage,
                                        int monsterCurrentHealth,
                                        int monsterMaxHealth) {

        int Player_CurrentHealth = playerCurrentHealth;
        int Monster_CurrentHealth = monsterCurrentHealth;

        while(true) {
            if(Player_CurrentHealth >= monsterDamage
                    && Monster_CurrentHealth >= playerDamage){
                Monster_CurrentHealth = Monster_CurrentHealth - playerDamage;

                if(Monster_CurrentHealth >= playerDamage){
                    Player_CurrentHealth = Player_CurrentHealth - monsterDamage;

                    if(Player_CurrentHealth >= monsterDamage){

                        attackMessage(playerName,
                                Player_CurrentHealth,
                                playerMaxHealth,
                                monsterName,
                                Monster_CurrentHealth,
                                monsterMaxHealth);

                        System.out.println(playerName + " attacks " + monsterName
                                + " for " + playerDamage + " damage." + "\n"
                                + monsterName + " attacks " + playerName
                                + " for " + monsterDamage + " damage.\n");

                    }else{ //Player_CurrentHealth < Monster_Damage

                        attackMessage(playerName,
                                Player_CurrentHealth,
                                playerMaxHealth,
                                monsterName,
                                Monster_CurrentHealth,
                                monsterMaxHealth);

                        System.out.println(playerName + " attacks " + monsterName
                                + " for " + playerDamage + " damage." + "\n"
                                + monsterName + " attacks " + playerName
                                + " for " + monsterDamage + " damage." + "\n"
                                + monsterName + " wins!\n");

                        System.out.println("(Press enter key to return to main menu)");
                        Player_CurrentHealth = Player_CurrentHealth - monsterDamage;

                        break;

                    }

                }else{ //Monster_CurrentHealth < Player_Damage
                    Player_CurrentHealth = Player_CurrentHealth - monsterDamage;

                    attackMessage(playerName,
                            Player_CurrentHealth,
                            playerMaxHealth,
                            monsterName,
                            Monster_CurrentHealth,
                            monsterMaxHealth);

                    System.out.println(playerName + " attacks " + monsterName
                            + " for " + playerDamage + " damage." + "\n"
                            + playerName + " wins!\n");

                    Monster_CurrentHealth = Monster_CurrentHealth - playerDamage;

                    break;

                }
            }else if(Player_CurrentHealth >= monsterDamage
                    && Monster_CurrentHealth < playerDamage){
                Player_CurrentHealth = Player_CurrentHealth - monsterDamage;

                attackMessage(playerName,
                        Player_CurrentHealth,
                        playerMaxHealth,
                        monsterName,
                        Monster_CurrentHealth,
                        monsterMaxHealth);

                System.out.println(playerName + " attacks " + monsterName
                        + " for " + playerDamage + " damage." + "\n"
                        + playerName + " wins!\n");

                Monster_CurrentHealth = Monster_CurrentHealth - playerDamage;

                break;

            }else if (Player_CurrentHealth < monsterDamage
                    && Monster_CurrentHealth >= playerDamage){

                attackMessage(playerName,
                        Player_CurrentHealth,
                        playerMaxHealth,
                        monsterName,
                        Monster_CurrentHealth,
                        monsterMaxHealth);

                System.out.println(monsterName + " attacks " +playerName
                        + " for " + monsterDamage + " damage." + "\n"
                        + monsterName + " wins!\n");

                System.out.println("(Press enter key to return to main menu)");
                Player_CurrentHealth = Player_CurrentHealth - monsterDamage;

                break;

            }else{break;}

        }

        ArrayList<Integer> playerMonsterCurrentHealth = new ArrayList<Integer>();
        playerMonsterCurrentHealth.add(0);
        playerMonsterCurrentHealth.add(0);

        playerMonsterCurrentHealth.set(0, Player_CurrentHealth);
        playerMonsterCurrentHealth.set(1, Monster_CurrentHealth);

        return playerMonsterCurrentHealth;

    }

    /**
     * Display player and monster messages for attacking in datFileName world
     *
     * @param playerName the name of the player
     * @param playerCurrentHealth the player's current health
     * @param playerMaxHealth the player's max health
     * @param monsterName the name of the monster
     * @param monsterCurrentHealth  the monster's current health
     * @param monsterMaxHealth the monster's max health
     */
    private void attackMessage(String playerName,
                               int playerCurrentHealth,
                               int playerMaxHealth,
                               String monsterName,
                               int monsterCurrentHealth,
                               int monsterMaxHealth){

        System.out.println(playerName + " " +
                playerCurrentHealth + "/" +
                playerMaxHealth + " | " +
                monsterName + " " +
                monsterCurrentHealth + "/" +
                monsterMaxHealth );
    }

}
