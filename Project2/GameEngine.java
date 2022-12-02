/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */

import java.io.*;
import java.util.*;


/**
 * A class for game engine
 */
public class GameEngine {

    /**
     * the default constructor of the game engine class
     */
    public GameEngine() {
    }

    /**
     * Game engine for playing the game via entering the commands.
     *
     * @param args An array contained the command-line arguments
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public static void main(String[] args) throws GameLevelNotFoundException {

        Scanner scanner = new Scanner(System.in);

        // Creates an instance of the game engine.
        GameEngine gameEngine = new GameEngine();
        Player player = new Player();
        Monster monster = new Monster();
        World world = new World();
        String datFileName;

        // Runs the main game loop.
        gameEngine.runGameLoop(player, monster);

        // All User Commands
        while(true) {
            String userCommand = scanner.nextLine();
            String[] userCommandArray = userCommand.split(" ");

            if (userCommandArray.length==1 && userCommandArray[0].equals("help")) {
                gameEngine.help();

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("commands")) {
                gameEngine.commands();

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("player")) {
                if (player.getName() == null) {
                    System.out.println("What is your character's name?");
                    String userName = scanner.nextLine();
                    player.setName(userName);
                    gameEngine.playerCreation(scanner, player, monster);
                } else if (player.getName() != null) {
                    gameEngine.existedPlayerPrint(scanner, player, monster);
                }

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("monster")) {
                gameEngine.monsterCreation(scanner, player, monster);

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("start")) {
                gameEngine.startGame(scanner, player, monster, world);

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("save")) {
                gameEngine.saveCommand(player);

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("load")) {
                gameEngine.loadCommand(player);

                //start dat file world(e.g 'start simple')
            } else if (userCommandArray.length==2 && userCommandArray[0].equals("start")) {

                datFileName = userCommandArray[1];

                if (player.getName()==null){
                    gameEngine.canNotLoadWorldData(scanner, player, monster);

                } else{
                    String loadResult;

                    //Check if 'Map' exists
                    try{
                        loadResult = "Yes";
                        gameEngine.mapFound(datFileName);

                        world.setDatFileName(datFileName);
                        world.initialCurrentWorld();

                    } catch(GameLevelNotFoundException exception){
                        loadResult = "No";
                        gameEngine.mapNotFound(scanner, player, monster);
                    }

                    if (!loadResult.equals("No")){
                        gameEngine.loadWorldData(scanner, player, monster, world);
                        gameEngine.startDatFileGame(scanner, player, monster, world);
                    }

                }

            } else if (userCommandArray.length==1 && userCommandArray[0].equals("exit")) {
                gameEngine.exitGame();
                break;

            } else { //All other commands

                //Check if 'Map' exists
                try{
                    gameEngine.mapFound("error");

                } catch(GameLevelNotFoundException exception){
                    gameEngine.mapNotFound(scanner, player, monster);
                }

            }
        }

    }

    /**
     * Main menu for the 'start' game.
     *
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private static void runGameLoop(Player player, Monster monster) {

        /* Main menu */
        // 1) Print out the title text.
        displayTitleText();

        // 2) The configured player and monster
        configPlayerMonster(player, monster);

        // 3) An initial message
        initialMessage();

    }

    /**
     * Main menu for the 'start datFileName' game.
     *
     * @param player the player in the game
     * @param monsterName the monster's in the game
     * @param monsterCurrentHealth the monster's current health in the game
     * @param monsterMaxHealth the monster's max health in the game
     */
    private void runGameLoopDat(Player player,
                                String monsterName,
                                int monsterCurrentHealth,
                                int monsterMaxHealth) {

        /* Main menu */
        // 1) Print out the title text.
        displayTitleText();

        // 2) The configured player and monster
        configPlayerMonsterDat(player,
                monsterName,
                monsterCurrentHealth,
                monsterMaxHealth);

        // 3) An initial message
        initialMessage();

    }

    /**
     * Displays the title text for main menu.
     */
    private static void displayTitleText() {

        String titleText = " ____                        \n" +
                "|  _ \\ ___   __ _ _   _  ___ \n" +
                "| |_) / _ \\ / _` | | | |/ _ \\\n" +
                "|  _ < (_) | (_| | |_| |  __/\n" +
                "|_| \\_\\___/ \\__, |\\__,_|\\___|\n" +
                "COMP90041   |___/ Assignment ";

        System.out.println(titleText);
        System.out.println();

    }

    /**
     * Display the configured messages for main menu.
     *
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private static void configPlayerMonster(Player player, Monster monster) {
        playerMonsterMessage(player, monster);
    }

    /**
     * Display the configured messages for datFileName world.
     *
     * @param player the player in the game
     * @param monsterName the monster's in the game
     * @param monsterCurrentHealth the monster's current health in the game
     * @param monsterMaxHealth the monster's max health in the game
     */
    private void configPlayerMonsterDat(Player player,
                                        String monsterName,
                                        int monsterCurrentHealth,
                                        int monsterMaxHealth) {
        playerMonsterMessageDat(player,
                monsterName,
                monsterCurrentHealth,
                monsterMaxHealth);
    }

    /**
     * Display the initial message for main menu.
     */
    private static void initialMessage() {

        System.out.println("Please enter a command to continue.\n" +
                "Type \'help\' to learn how to get started.");

        System.out.print("\n> ");

    }

    /**
     * Output for entering 'help' command.
     */
    private void help() {

        System.out.println("Type 'commands' to list all available commands\n" +
                "Type 'start' to start a new game\n" +
                "Create a character, battle monsters, and find treasure!");

        //Enter to continue
        System.out.print("\n> ");

    }

    /**
     * Output for entering 'commands' command.
     */
    private void commands() {

        System.out.println("help\n" +
                "player\n" +
                "monster\n" +
                "start\n" +
                "exit");

        //Enter to continue
        System.out.print("\n> ");

    }

    /**
     * Create a new player via entering 'player' command firstly.
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void playerCreation(Scanner scanner, Player player, Monster monster){

        System.out.println("Player '"+player.getName()+"' created.\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Update configPlayer
        runGameLoop(player, monster);

    }

    /**
     * Test whether the datFileName map exists.
     *
     * @param datFileName the name of the datFileName map tried to load
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private void mapFound(String datFileName) throws GameLevelNotFoundException{

        try {
            File fileName = new File(datFileName+".dat");
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            br.close();

        } catch(FileNotFoundException exception){
            throw new GameLevelNotFoundException();

        } catch (IOException exception) {
            System.out.println("An error occurred while loading the file.");
        }

    }

    /**
     * The message printed if the datFileName map doesn't exist.
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private static void mapNotFound(Scanner scanner, Player player, Monster monster){
        System.out.println("Map not found.\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        // Update configPlayer
        runGameLoop(player, monster);
    }

    /**
     * The message printed if the player has been created before when entering 'player' command.
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void existedPlayerPrint(Scanner scanner, Player player, Monster monster){

        System.out.println(player.getName() + " (Lv. " + player.getLevel() + ")\n"+
                "Damage: "+ player.getDamage()+"\n"+
                "Health: "+ player.getCurrentHealth() + "/" + player.getMaxHealth()+"\n");

        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Show existed configPlayer
        runGameLoop(player, monster);

    }

    /**
     * Create a new monster via entering 'monster' command.
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void monsterCreation(Scanner scanner, Player player, Monster monster){

        System.out.print("Monster name: ");
        String monsterName=scanner.nextLine();
        System.out.print("Monster health: ");
        int monsterHealth=Integer.parseInt(scanner.nextLine());
        System.out.print("Monster damage: ");
        int monsterDamage=Integer.parseInt(scanner.nextLine());
        monster.setName(monsterName);
        monster.setMaxHealth(monsterHealth);
        monster.setDamage(monsterDamage);
        monster.setCurrentHealth(monsterHealth);

        System.out.println("Monster '" + monster.getName() + "' created.\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Update configMonster
        runGameLoop(player, monster);

    }

    /**
     * The game started when entering 'start' command.
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param world the world map of the game
     */
    private void startGame(Scanner scanner, Player player, Monster monster, World world){

        if (player.getName() == null){
            System.out.println("No player found, please create a player with 'player' first.\n");

            System.out.println("(Press enter key to return to main menu)");

            //Enter to continue
            scanner.nextLine();

            //Show existed configPlayer
            runGameLoop(player, monster);

        }else if (player.getName() != null && monster.getName() == null){
            System.out.println("No monster found, please create a monster with 'monster' first.\n");

            System.out.println("(Press enter key to return to main menu)");

            //Enter to continue
            scanner.nextLine();

            //Show existed configPlayer
            runGameLoop(player, monster);

        } else if (player.getName() != null && monster.getName() != null){
            //Heal before going to the Game world
            healPlayerMonster(world, player, monster);

            //Enter the Game World
            world.initialWorld(player.getName(), monster.getName());

            System.out.print("\n> ");

                //Movement
                while (true) {
                    String playerMoveAction = scanner.nextLine();

                    if (playerMoveAction.equals("home")){
                        home(scanner, player, monster);
                        break;

                   } else if ((world.getPlayerWidth() == world.getMonsterWidth())
                            && ((playerMoveAction.equals("s")
                            && (world.getPlayerHeight() == (world.getMonsterHeight() - 1)))
                            || (playerMoveAction.equals("w")
                            && (world.getPlayerHeight() == (world.getMonsterHeight() + 1))))) {
                        playerMovement(player, monster); //movement
                        battleLoop(scanner, player, monster);  //battle
                        break;
                    } else if ((world.getPlayerHeight() == world.getMonsterHeight())
                            && ((playerMoveAction.equals("a")
                            && (world.getPlayerWidth() == (world.getMonsterWidth() + 1)))
                            || (playerMoveAction.equals("d")
                            && (world.getPlayerWidth() == (world.getMonsterWidth() - 1))))) {
                        playerMovement(player, monster); //movement
                        battleLoop(scanner, player, monster); //battle
                        break;
                    } else {
                        if (playerMoveAction.equals("w")) { //North
                            world.northMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        } else if (playerMoveAction.equals("s")) { //South
                            world.southMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        } else if (playerMoveAction.equals("a")) { //West
                            world.westMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        } else if (playerMoveAction.equals("d")) { //East
                            world.eastMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        }

                        System.out.print("\n> ");

                }
            }

        }

    }

    /**
     * The game started when entering 'start datFileName' command.
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param world the world map of the game
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private void startDatFileGame(Scanner scanner, Player player, Monster monster, World world)
            throws GameLevelNotFoundException {

        //initialize player Damage in world
        world.playerDamageInitial(player);

        //initialize the world before playing
        world.setInitialCurrentWorld();

            //Movement
            while (true) {
                String playerMoveAction = scanner.nextLine();

                //Take the map indicator
                int mapWidth = Integer.parseInt(
                        world.getCurrentWorld().get(world.getCurrentWorld().size()-2).get(0));
                int mapHeight = Integer.parseInt(
                        world.getCurrentWorld().get(world.getCurrentWorld().size()-2).get(1));

                //Take the player's info
                int pWidth = Integer.parseInt(
                        world.getCurrentWorld().get(world.getCurrentWorld().size() - 1).get(0));
                int pHeight = Integer.parseInt(
                        world.getCurrentWorld().get(world.getCurrentWorld().size() - 1).get(1));

                //Record the numbers of monster and item numbers
                int monsterNumInitial = 0;
                int itemNumInitial = 0;

                //The coordinates of the end point @
                int endItemWidth = 0;
                int endItemHeight = 0;

                for (int i = 0; i < world.getCurrentWorld().size(); i++) {
                    char firstChar = world.getCurrentWorld().get(i).get(0).charAt(0);
                    if (firstChar == 'm') {
                        monsterNumInitial++;
                    } else if (firstChar == 'i') {
                        itemNumInitial++;

                        if (world.getCurrentWorld().get(i).get(3).equals("@")) {
                            endItemWidth = Integer.parseInt(world.getCurrentWorld().get(i).get(1));
                            endItemHeight = Integer.parseInt(world.getCurrentWorld().get(i).get(2));
                        }
                    }
                }

                //Move according to the command
                   //1) 'home' command
                if (playerMoveAction.equals("home")) {

                    //The Damage bonus given by the ability "^" will now be reset
                    world.resetDamage(player.getDamage());

                    home(scanner, player, monster);
                    break;

                   //2) Teat whether the player will reach end '@' in the next step
                    // Judge whether the 'on (pHeight-1)" walk can meet the end @
                } else if (playerMoveAction.equals("w") && pHeight > 0) {

                    if (testNorthWest(scanner,
                            player,
                            monster,
                            world,
                            playerMoveAction,
                            pHeight - 1,
                            endItemWidth,
                            endItemHeight,
                            pWidth))
                        break;

                    // Judge whether the 'down (pHeight+1)' walk can meet end @
                } else if (playerMoveAction.equals("s") && pHeight < mapHeight-1) {

                    if (testSouthEast(scanner,
                            player,
                            monster,
                            world,
                            playerMoveAction,
                            pWidth,
                            endItemWidth,
                            endItemHeight,
                            pHeight + 1))
                        break;

                    // Determine if the 'left(pWidth-1)' walk can meet the end @
                } else if (playerMoveAction.equals("a") && pWidth > 0) {

                    if (testNorthWest(scanner,
                            player,
                            monster,
                            world,
                            playerMoveAction,
                            pHeight,
                            endItemWidth,
                            endItemHeight,
                            pWidth - 1))
                        break;

                    // Determine if the 'right(pWidth+1)' walk can meet end @
                } else if (playerMoveAction.equals("d") && pWidth < mapWidth-1) {

                    if (testSouthEast(scanner,
                            player,
                            monster,
                            world,
                            playerMoveAction,
                            pWidth + 1,
                            endItemWidth,
                            endItemHeight,
                            pHeight))
                        break;

                   //3) player move command
                    // playing the game
                } else {

                    /*
                     *  Case 1: Enter the command for player's movement (s/w/a/d) successfully.
                     */
                    // Determine if each monster can move;
                    // Judge that the player encounters monster, and if so, enter the battle loop;
                    // Test whether the player is ultimately defeated.
                    world.monsterMovement(player,
                            playerMoveAction,
                            player.getName(),
                            world.getUpDamage(), // update player damage by item
                            player.getCurrentHealth(),
                            player.getMaxHealth(),
                            world.getCurrentWorld());

                    // According to the w/s/a/d command for movement direction
                    // of the user input, check whether the player can move;
                    // Determine if the player encounters item
                    world.movementTest(player,
                            playerMoveAction,
                            world.getCurrentWorld(),
                            world.getUpDamage(), // update player damage by item
                            player.getCurrentHealth(),
                            player.getMaxHealth());

                    // Test the player's final health if < 0 after battling with the monster.
                    String monsterName=null;
                    int monsterDamage=0;
                    int monsterCurrentHealth = 0;
                    int monsterMaxHealth = 0;

                    /*
                     * Case 2: Enter the unrecognized command/empty command for player's movement.
                     */
                    // Whether the player encounters each monster when it is not moving successfully.
                    for (int i = 0; i < world.monsterCurrentNum(world.getCurrentWorld()); i++) {

                        // Take monster's info
                        int mmWidth = Integer.parseInt(
                                world.getCurrentWorld().get(mapHeight + i).get(1));

                        int mmHeight = Integer.parseInt(
                                world.getCurrentWorld().get(mapHeight + i).get(2));

                        String mName = world.getCurrentWorld().get(mapHeight + i).get(3);

                        int mDamage = Integer.parseInt(
                                world.getCurrentWorld().get(mapHeight + i).get(5));

                        int mMaxHealth = Integer.parseInt(
                                world.getCurrentWorld().get(mapHeight + i).get(4));

                        int mCurrentHealth = mMaxHealth;


                        //the player encounters a monster
                        if (pHeight == mmHeight && pWidth == mmWidth) {
                            monsterName=mName;
                            monsterDamage=mDamage;
                            monsterCurrentHealth=mCurrentHealth;
                            monsterMaxHealth= mMaxHealth;

                            // Determine if each monster can move;
                            // Judge that the player encounters monster, and
                            // if so, enter the battle loop;
                            // Test whether the player is ultimately defeated.
                            world.encounterMonster(player.getName(),
                                    world.getUpDamage(), //  update player damage by item
                                    player.getCurrentHealth(),
                                    player.getMaxHealth(),
                                    monsterName,
                                    monsterDamage,
                                    monsterCurrentHealth,
                                    monsterMaxHealth);

                            //battle and record the player's HealthResult
                            int pHealthResult=world.battleLoop(player.getName(),
                                    world.getUpDamage(), //  update player damage by item
                                    player.getCurrentHealth(),
                                    player.getMaxHealth(),
                                    mName,
                                    monsterDamage,
                                    monsterCurrentHealth,
                                    monsterMaxHealth).get(0);

                            //Delete this monster that has finished the battle with the player
                            world.updateCurrentWorld(world.getCurrentWorld(),
                                    mapHeight + i);

                            // Update player's current health
                            // (i.e. The max health when encountering the next monster)
                            player.setCurrentHealth(pHealthResult);

                            // The message printed if the player fails
                            if (pHealthResult<0){
                                //Enter to continue
                                scanner.nextLine();

                                //Update Main menu
                                runGameLoopDat(player,
                                        null,
                                        0,
                                        0);
                                break;
                            }
                        }
                    }

                    // If the player's current health is > 0, print the current map
                    if (player.getCurrentHealth()>=0){

                        world.printResultMap(player.getName(), world.getCurrentWorld());

                        System.out.print("\n> ");

                    } else{
                        break;
                    }

                }
            }
    }


    /**
     * An auxiliary function when
     * judging whether the 'down (pHeight+1)' walk can meet end @ and
     * determining if the 'right(pWidth+1)' walk can meet end @
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param world the world map of the game
     * @param playerMoveAction the command for player's movement direction entered by user
     * @param pWidth the X of player
     * @param endItemWidth the X of the end item @
     * @param endItemHeight the Y of the end item @
     * @param change The change in X or Y of the player according to the player's movement direction
     * @return a boolean object represented whether the player arrives the end item @
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private boolean testSouthEast(Scanner scanner,
                                  Player player,
                                  Monster monster,
                                  World world,
                                  String playerMoveAction,
                                  int pWidth,
                                  int endItemWidth,
                                  int endItemHeight,
                                  int change) throws GameLevelNotFoundException {

        return testEndIterm(scanner,
                player,
                monster,
                world,
                playerMoveAction,
                change,
                endItemWidth,
                endItemHeight,
                pWidth);
    }

    /**
     * An auxiliary function when
     * judging whether the 'on (pHeight-1)" walk can meet the end @ and
     * determining if the 'left(pWidth-1)' walk can meet the end @
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param world the world map of the game
     * @param playerMoveAction the command for player's movement direction entered by user
     * @param pHeight the Y of player
     * @param endItemWidth the X of the end item @
     * @param endItemHeight the Y of the end item @
     * @param change The change in X or Y of the player according to the player's movement direction
     * @return a boolean object represented whether the player arrives the end item @
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private boolean testNorthWest(Scanner scanner,
                                  Player player,
                                  Monster monster,
                                  World world,
                                  String playerMoveAction,
                                  int pHeight,
                                  int endItemWidth,
                                  int endItemHeight,
                                  int change) throws GameLevelNotFoundException {

        return testEndIterm(scanner,
                player,
                monster,
                world,
                playerMoveAction,
                pHeight,
                endItemWidth,
                endItemHeight,
                change);
    }

    /**
     * Test whether the player arrives the end item @ and
     * the updates if player arrives the end item @
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param world the world map of the game
     * @param playerMoveAction the command for player's movement direction entered by user
     * @param pHeight the Y of the player
     * @param endItemWidth the X of the end item @
     * @param endItemHeight the Y of the end item @
     * @param change The change in X or Y of the player according to the player's movement direction
     * @return a boolean object represented whether the player arrives the end item @
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private boolean testEndIterm(Scanner scanner, Player player, Monster monster,
                                 World world, String playerMoveAction, int pHeight,
                                 int endItemWidth, int endItemHeight, int change)
            throws GameLevelNotFoundException {


        if (pHeight == endItemHeight && change == endItemWidth) {

            // The player's level + 1
            player.setLevel(player.getLevel()+1);
            endItem(scanner, player, monster);

        } else {
            // Judge if the monster will move
            // Record the final health of the player if battling the monsters
            int pH = judgeMonsterMovePlayerFight(player, world, playerMoveAction);

             // player fails
            if (pH < 0){
                playerFail(world, player, scanner, pH);
                return true;

             // player wins
            }else{
                playerWin(world, scanner, player, monster, playerMoveAction);

            }
        }

        return false;

    }

    /**
     * Judge if the monster will move and
     * record the final health of the player if battling the monsters
     *
     * @param player the player in the game
     * @param world the world map of the game
     * @param playerMoveAction the command for player's movement direction entered by user
     * @return player's current health after fighting
     */
    private int judgeMonsterMovePlayerFight(Player player, World world, String playerMoveAction){
        ArrayList<String> pmFightingResult = new ArrayList<String>();
        pmFightingResult.add("null");
        pmFightingResult.add("null");
        pmFightingResult.add("null");
        pmFightingResult.add("null");

        ArrayList<String> pmFightingArrayResult = world.monsterMovement(player,
                playerMoveAction,
                player.getName(),
                player.getDamage(),
                player.getCurrentHealth(),
                player.getMaxHealth(),
                world.getCurrentWorld());

        //[playerHealthResult, monsterName, monsterCurrenHealth, monsterMaxHealth]
        pmFightingResult.set(0, pmFightingArrayResult.get(0));
        pmFightingResult.set(1, pmFightingArrayResult.get(1));
        pmFightingResult.set(2, pmFightingArrayResult.get(2));
        pmFightingResult.set(3, pmFightingArrayResult.get(3));

        //player's health after fighting with monster
        int pH = Integer.parseInt(pmFightingResult.get(0));

        // monster's info after fighting
        String monsterName = pmFightingResult.get(1);
        int monsterCurrentHealth = Integer.parseInt(pmFightingResult.get(2));
        int monsterMaxHealth = Integer.parseInt(pmFightingResult.get(3));

        return pH;
    }

    /**
     * The message printed if player fails after fighting with the monster
     *
     * @param world the world map of the game
     * @param player the player in the game
     * @param scanner a scanner object entered by user
     * @param pH player's currentHealth after fighting with the monster
     */
    private void playerFail(World world, Player player, Scanner scanner, int pH){

        //The Damage bonus given by the ability "^" will now be reset
        world.resetDamage(player.getDamage());

        //Enter to continue
        scanner.nextLine();

        //Update Main menu
        player.setCurrentHealth(pH); //update player's currentHealth

        runGameLoopDat(player,
                null,
                0,
                0);
    }

    /**
     * The action implemented if player wins after fighting with the monster
     *
     * @param world the world map of the game
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param playerMoveAction the command for player's movement direction entered by user
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private void playerWin(World world, Scanner scanner,
                           Player player,
                           Monster monster, String playerMoveAction)
            throws GameLevelNotFoundException {

        // According to the w/s/a/d command for movement direction
        // of the user input, check whether the player can move;
        // Determine if the player encounters item
        world.movementTest(player,
                playerMoveAction,
                world.getCurrentWorld(),
                player.getDamage(),
                player.getCurrentHealth(),
                player.getMaxHealth());

        //print the current map
        world.printResultMap(player.getName(), world.getCurrentWorld());

        System.out.print("\n> ");

    }

    /**
     * The message printed if entering 'home' command
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void home(Scanner scanner, Player player, Monster monster){
        System.out.println("Returning home...\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Show existed configPlayer
        runGameLoop(player, monster);
    }

    /**
     * The message printed if the player arrives end item @
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void endItem(Scanner scanner, Player player, Monster monster){
        System.out.println("World complete! (You leveled up!)\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Show existed configPlayer
        runGameLoop(player, monster);
    }

    /**
     * The message printed if entering 'exit' command
     */
    private void exitGame(){
        System.out.println("Thank you for playing Rogue!");
    }

    /**
     * Save the new player's name and level if entering 'save' command
     *
     * @param player the player in the game
     */
    private void saveCommand(Player player){
        if (player.getName()==null){
            System.out.println("No player data to save.");
        }else{
            System.out.println("Player data saved.");
            savePlayerData(player);
        }

        System.out.print("\n> ");
    }

    /**
     * An auxiliary function for saving the new player's name and level if entering 'save' command
     *
     * @param player the player in the game
     */
    public void savePlayerData(Player player){
        try{
            FileOutputStream fileOS = new FileOutputStream("player.dat");
            BufferedOutputStream bufferOS = new BufferedOutputStream(fileOS);
            ObjectOutputStream objectOS = new ObjectOutputStream(bufferOS);

            PlayerStorage ps = new PlayerStorage();
            ps.playerName = player.getName();
            ps.playerLevel = player.getLevel();

            objectOS.writeObject(ps);
            objectOS.close();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Load the existed player's name and level if entering 'load' command
     *
     * @param player the player in the game
     */
    private void loadCommand(Player player){

        loadPlayerData(player);

        System.out.print("\n> ");
    }

    /**
     * An auxiliary function for loading the existed player's name
     * and level if entering 'load' command
     *
     * @param player the player in the game
     */
    public void loadPlayerData(Player player){
        try{
            FileInputStream fileIS = new FileInputStream("player.dat");
            BufferedInputStream bufferIS = new BufferedInputStream(fileIS);
            ObjectInputStream objectIS = new ObjectInputStream(bufferIS);

            PlayerStorage ps = (PlayerStorage)objectIS.readObject();

            player.setName(ps.playerName);
            player.setLevel(ps.playerLevel);
            player.setMaxHealth(player.getLevel());

            //player heal to the maxHealth
            player.setCurrentHealth(player.getMaxHealth());

            System.out.println("Player data loaded.");

            objectIS.close();
        }
        catch(IOException | ClassNotFoundException exception){
            System.out.println("No player data found.");
        }

    }

    /**
     * Print the initial map if the player exists
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     * @param world the world map of the game
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    private void loadWorldData(Scanner scanner,
                               Player player,
                               Monster monster,
                               World world) throws GameLevelNotFoundException {

        if (player.getName() != null) {
            //print world map only
            world.printInitialMapOnly(player.getName());

            System.out.print("\n> ");
        }
    }

    /**
     * The message printed if we can not print the map since the player doesn't exist
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void canNotLoadWorldData(Scanner scanner, Player player, Monster monster) {

            System.out.println("No player found, please create a player with 'player' first.\n");

            System.out.println("(Press enter key to return to main menu)");

            //Enter to continue
            scanner.nextLine();

            //Show existed configPlayer
            runGameLoop(player, monster);
    }

    /**
     * The first message printed when the player encounters a monster
     *
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void playerMovement(Player player, Monster monster) {
        System.out.println(player.getName() + " encountered a " + monster.getName() + "!\n");

        System.out.println(player.getName() + " " +
                player.getCurrentHealth() + "/" +
                player.getMaxHealth() + "  | " +
                monster.getName() + " " +
                monster.getCurrentHealth() + "/" +
                monster.getMaxHealth()+"\n");

        System.out.println(player.getName() + " attacks " + monster.getName()
                + " for " + player.getDamage() + " damage."+"\n"
                + monster.getName() + " attacks " + player.getName()
                + " for " + monster.getDamage() + " damage.\n");
    }

    /**
     * The implementation for the battle loop
     *
     * @param scanner a scanner object entered by user
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void battleLoop(Scanner scanner, Player player, Monster monster) {
        int Player_CurrentHealth = player.getCurrentHealth();
        int Monster_CurrentHealth = monster.getCurrentHealth();

        while(true) {
            if(Player_CurrentHealth >= monster.getDamage()
                    && Monster_CurrentHealth >= player.getDamage()){
                Monster_CurrentHealth = Monster_CurrentHealth - player.getDamage();

                if(Monster_CurrentHealth >= player.getDamage()){
                    Player_CurrentHealth = Player_CurrentHealth - monster.getDamage();

                    if(Player_CurrentHealth >= monster.getDamage()){

                        attackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
                        System.out.println(player.getName() + " attacks " + monster.getName()
                                + " for " + player.getDamage() + " damage." + "\n"
                                + monster.getName() + " attacks " + player.getName()
                                + " for " + monster.getDamage() + " damage.\n");

                    }else{ //Player_CurrentHealth < Monster_Damage

                        printMonsterWinMessage(Player_CurrentHealth,
                                Monster_CurrentHealth,
                                player,
                                monster,
                                scanner);
                        break;

                    }

                }else{ //Monster_CurrentHealth < Player_Damage
                    printPlayerWinMessage(Player_CurrentHealth,
                            Monster_CurrentHealth,
                            player,
                            monster,
                            scanner);
                    break;

                }

            }else if(Player_CurrentHealth >= monster.getDamage()
                    && Monster_CurrentHealth < player.getDamage()){

                printPlayerWinMessage(Player_CurrentHealth,
                        Monster_CurrentHealth,
                        player,
                        monster,
                        scanner);
                break;

            }else if (Player_CurrentHealth < monster.getDamage()
                    && Monster_CurrentHealth >= player.getDamage()){

                printMonsterWinMessage(Player_CurrentHealth,
                        Monster_CurrentHealth,
                        player,
                        monster,
                        scanner);
                break;

            }else{break;}

        }

    }

    /**
     * The final message printed if the player wins in the battle loop.
     *
     * @param Player_CurrentHealth player's current health
     * @param Monster_CurrentHealth monster's current health
     * @param player the player in the game
     * @param monster the monster in the game
     * @param scanner a scanner object entered by user
     */
    private void printPlayerWinMessage(int Player_CurrentHealth,
                                       int Monster_CurrentHealth,
                                       Player player,
                                       Monster monster,
                                       Scanner scanner){

        Player_CurrentHealth = Player_CurrentHealth - monster.getDamage();

        attackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
        System.out.println(player.getName() + " attacks " + monster.getName()
                + " for " + player.getDamage() + " damage." + "\n"
                + player.getName() + " wins!\n");

        System.out.println("(Press enter key to return to main menu)");
        Monster_CurrentHealth = Monster_CurrentHealth - player.getDamage();

        player.setCurrentHealth(Player_CurrentHealth);
        monster.setCurrentHealth(Monster_CurrentHealth);

        //Enter to continue
        scanner.nextLine();

        //Update Main menu
        runGameLoop(player, monster);
    }

    /**
     * The final message printed if the monster wins in the battle loop.
     *
     * @param Player_CurrentHealth player's current health
     * @param Monster_CurrentHealth monster's current health
     * @param player the player in the game
     * @param monster the monster in the game
     * @param scanner a scanner object entered by user
     */
    private void printMonsterWinMessage(int Player_CurrentHealth,
                                        int Monster_CurrentHealth,
                                        Player player,
                                        Monster monster,
                                        Scanner scanner){

        attackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
        System.out.println(monster.getName() + " attacks " + player.getName()
                + " for " + monster.getDamage() + " damage." + "\n"
                + monster.getName() + " wins!\n");

        System.out.println("(Press enter key to return to main menu)");
        Player_CurrentHealth = Player_CurrentHealth - monster.getDamage();

        player.setCurrentHealth(Player_CurrentHealth);
        monster.setCurrentHealth(Monster_CurrentHealth);

        //Enter to continue
        scanner.nextLine();

        //Update Main menu
        runGameLoop(player, monster);
    }

    /**
     * Heal the player and monster before 'start' the game
     *
     * @param world the world map of the game
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void healPlayerMonster(World world, Player player, Monster monster){
        //1) heal the player's position
        world.setPlayerHeight(world.getPlayerInitialHeight());
        world.setPlayerWidth(world.getPlayerInitialWidth());

        //2) heal the player
        player.setCurrentHealth(player.getMaxHealth());

        //3) heal the monster
        monster.setCurrentHealth(monster.getMaxHealth());
    }

    /**
     * Display player and monster messages into main menu after the battle for 'start' game
     *
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private static void playerMonsterMessage(Player player, Monster monster){
        //exist player and monster
        if (player.getName() != null && monster.getName() != null){
            System.out.println(
                    "Player: " + player.getName() + " " +
                            player.getCurrentHealth() + "/" +
                            player.getMaxHealth() + "  | " +
                            "Monster: " + monster.getName() + " " +
                            monster.getCurrentHealth() + "/" +
                            monster.getMaxHealth()+"\n");
        }
        //exist player but no monster
        else if(player.getName() != null && monster.getName() == null){
            System.out.println("Player: " + player.getName() + " " +
                    player.getCurrentHealth() + "/" +
                    player.getMaxHealth() + "  | " + "Monster: " + "[None]"+"\n");
        }
        //no player but exist monster
        else if(player.getName() == null && monster.getName() != null){
            System.out.println(
                    "Player: " + "[None]" + "  | " +
                            "Monster: " + monster.getName() + " " +
                            monster.getCurrentHealth() + "/" +
                            monster.getMaxHealth() + "\n");
        }
        //no player and monster
        else if(player.getName() == null && monster.getName() == null){
            System.out.println("Player: " + "[None]" + "  | "+ "Monster: " + "[None]"+"\n");
        }
    }

    /**
     * Display player and monster messages into main menu
     * after the battle for 'start datFileName' game
     *
     * @param player the player in the game
     * @param monsterName the current monster's name
     * @param monsterCurrentHealth the current monster's current health
     * @param monsterMaxHealth the current monster's max health
     */
    private void playerMonsterMessageDat(Player player,
                                         String monsterName,
                                         int monsterCurrentHealth,
                                         int monsterMaxHealth){
        //exist player and monster
        if (player.getName() != null && monsterName != null){
            System.out.println(
                    "Player: " + player.getName() + " " +
                            player.getCurrentHealth() + "/" +
                            player.getMaxHealth() + "  | " +
                            "Monster: " + monsterName + " " +
                            monsterCurrentHealth + "/" +
                            monsterMaxHealth+"\n");
        }
        //exist player but no monster
        else if(player.getName() != null && monsterName == null){
            System.out.println("Player: " + player.getName() + " " +
                    player.getCurrentHealth() + "/" +
                    player.getMaxHealth() + "  | " + "Monster: " + "[None]"+"\n");
        }
        //no player but exist monster
        else if(player.getName() == null && monsterName != null){
            System.out.println(
                    "Player: " + "[None]" + "  | " +
                            "Monster: " + monsterName + " " +
                            monsterCurrentHealth + "/" +
                            monsterMaxHealth + "\n");
        }
        //no player and monster
        else if(player.getName() == null && monsterName == null){
            System.out.println("Player: " + "[None]" + "  | "+ "Monster: " + "[None]"+"\n");
        }
    }

    /**
     * Display player and monster messages for attacking
     *
     * @param Player_CurrentHealth player's current health
     * @param Monster_CurrentHealth monster's current health
     * @param player the player in the game
     * @param monster the monster in the game
     */
    private void attackMessage(int Player_CurrentHealth,
                               int Monster_CurrentHealth,
                               Player player,
                               Monster monster){

        System.out.println(player.getName() + " " +
                Player_CurrentHealth + "/" +
                player.getMaxHealth() + "  | " +
                monster.getName() + " " +
                Monster_CurrentHealth + "/" +
                monster.getMaxHealth() + "\n" );
    }

    /**
     * Read and save the dat file into an ArrayList
     *
     * @param datFileName the name of the dat file.
     * @return an ArrayList of the world map
     * @throws GameLevelNotFoundException the loaded file does not exist
     */
    public static ArrayList<String> tryReadDatFile(String datFileName)
            throws GameLevelNotFoundException {

        ArrayList<String> worldMap = new ArrayList<String>();

        try {
            File fileName = new File( datFileName + ".dat");
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            //read entire line as string
            String eachLine = br.readLine();
            while (eachLine != null) {
                worldMap.add(eachLine);
                eachLine = br.readLine();
            }
            br.close();

        } catch(FileNotFoundException exception) {
            throw new GameLevelNotFoundException();

        }
        catch (IOException exception) {
            System.out.println("An error occurred while loading the file.");
        }

        return worldMap;

    }

}