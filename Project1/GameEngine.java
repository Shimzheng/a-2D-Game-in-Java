/**
 * TODO: Write a comment describing your class here.
 * @author TODO: Fill in your name, university email, and student number here.
 * Name: Shiming ZHENG
 * Email: shimzheng@student.unimelb.edu.au
 * Student number: 1149897
 */
import java.util.Scanner;

public class GameEngine {

    public static void main(String[] args) {

        // TODO: Some starter code has been provided below.
        // Edit this method as you find appropriate.

        Scanner scanner = new Scanner(System.in);

        // Creates an instance of the game engine.
        GameEngine gameEngine = new GameEngine();
        Player player = new Player();
        Monster monster = new Monster();
        World world = new World();

        // Runs the main game loop.
        gameEngine.runGameLoop(player, monster);

        // All User Commands
        while(true) {
            String userCommand = scanner.nextLine();
            if (userCommand.equals("help")) {
                gameEngine.help();

            } else if (userCommand.equals("commands")) {
                gameEngine.commands();

            } else if (userCommand.equals("player")) {
                if (player.getName() == null) {
                    System.out.println("What is your character's name?");
                    String userName = scanner.nextLine();
                    player.setName(userName);
                    gameEngine.playerCreation(scanner, player, monster);
                } else if (player.getName() != null) {
                    gameEngine.existedPlayerPrint(scanner, player, monster);
                }

            } else if (userCommand.equals("monster")) {
                gameEngine.monsterCreation(scanner, player, monster);

            } else if (userCommand.equals("start")) {
                gameEngine.startGame(scanner, player, monster, world);

            } else if (userCommand.equals("exit")) {
                gameEngine.exitGame();
                break;
            }
        }

    }

    //Logic for running the main game loop.
    private void runGameLoop(Player player, Monster monster) {

        /* Main menu */
        // 1) Print out the title text.
        displayTitleText();

        // TODO: Implement your code here.

        // 2) The configured player and monster
        configPlayerMonster(player, monster);

        // 3) An initial message
        initialMessage();

    }

    // ----------------------------------------
    // Methods for Main menu
    // ----------------------------------------

    //Displays the title text.
    private void displayTitleText() {

        String titleText = " ____                        \n" +
                "|  _ \\ ___   __ _ _   _  ___ \n" +
                "| |_) / _ \\ / _` | | | |/ _ \\\n" +
                "|  _ < (_) | (_| | |_| |  __/\n" +
                "|_| \\_\\___/ \\__, |\\__,_|\\___|\n" +
                "COMP90041   |___/ Assignment ";

        System.out.println(titleText);
        System.out.println();

    }

    //Displays the configured messages.
    private void configPlayerMonster(Player player, Monster monster) {
        playerMonsterMessage(player, monster);
    }

    //Displays the initial message.
    private void initialMessage() {

        System.out.println("Please enter a command to continue.\n" +
                "Type \'help\' to learn how to get started.");

        System.out.print("\n> ");

    }

    // ----------------------------------------
    // Methods for Commands after '>'
    // ----------------------------------------

    //'help' command
    private void help() {

        System.out.println("Type 'commands' to list all available commands\n" +
                "Type 'start' to start a new game\n" +
                "Create a character, battle monsters, and find treasure!");

        //Enter to continue
        System.out.print("\n> ");

    }

    //'commands' command
    private void commands() {

        System.out.println("help\n" +
                "player\n" +
                "monster\n" +
                "start\n" +
                "exit");

        //Enter to continue
        System.out.print("\n> ");

    }

    //'player' command (1st time)
    private void playerCreation(Scanner scanner, Player player, Monster monster){

        System.out.println("Player '"+player.getName()+"' created.\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Update configPlayer
        runGameLoop(player, monster);

    }

    //'player' command (2nd time) for existed player
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

    //'monster' command
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

    //'start' command
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
            world.InitialWorld(player.getName(), monster.getName());

            System.out.print("\n> ");

            //'home' command: returning home
            String playerAction = scanner.nextLine();
            if (playerAction.equals("home")){
                home(scanner, player, monster);

            }else {
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
                            world.NorthMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        } else if (playerMoveAction.equals("s")) { //South
                            world.SouthMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        } else if (playerMoveAction.equals("a")) { //West
                            world.WestMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        } else if (playerMoveAction.equals("d")) { //East
                            world.EastMovement(world.getPlayerHeight(), world.getPlayerWidth(),
                                    player.getName(), monster.getName());
                        }

                        System.out.print("\n> ");
                    }
                }
            }

        }

    }

    //'home' command
    private void home(Scanner scanner, Player player, Monster monster){
        System.out.println("Returning home...\n");
        System.out.println("(Press enter key to return to main menu)");

        //Enter to continue
        scanner.nextLine();

        //Show existed configPlayer
        runGameLoop(player, monster);
    }

    //'exit' command
    private void exitGame(){
        System.out.println("Thank you for playing Rogue!");
    }

    //player's movement
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

    //Implements the battle loop
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

                        AttackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
                        System.out.println(player.getName() + " attacks " + monster.getName()
                                + " for " + player.getDamage() + " damage." + "\n"
                                + monster.getName() + " attacks " + player.getName()
                                + " for " + monster.getDamage() + " damage.\n");

                    }else{ //Player_CurrentHealth < Monster_Damage

                        AttackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
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

                        break;

                    }

                }else{ //Monster_CurrentHealth < Player_Damage
                    Player_CurrentHealth = Player_CurrentHealth - monster.getDamage();

                    AttackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
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

                    break;

                }
            }else if(Player_CurrentHealth >= monster.getDamage()
                    && Monster_CurrentHealth < player.getDamage()){
                Player_CurrentHealth = Player_CurrentHealth - monster.getDamage();

                AttackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
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

                break;

            }else if (Player_CurrentHealth < monster.getDamage()
                    && Monster_CurrentHealth >= player.getDamage()){

                AttackMessage(Player_CurrentHealth, Monster_CurrentHealth, player, monster);
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

                break;

            }else{break;}

        }

    }

    //Heals the player and monster
    private void healPlayerMonster(World world, Player player, Monster monster){
        //1) heal the player's position
        world.setPlayerHeight(world.getPlayerInitialHeight());
        world.setPlayerWidth(world.getPlayerInitialWidth());

        //2) heal the player
        player.setCurrentHealth(player.getMaxHealth());

        //3) heal the monster
        monster.setCurrentHealth(monster.getMaxHealth());
    }

    //Displays player and monster messages
    private void playerMonsterMessage(Player player, Monster monster){
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

    //Displays player and monster messages for attacking
    private void AttackMessage(int Player_CurrentHealth, int Monster_CurrentHealth,
                               Player player, Monster monster){

        System.out.println(player.getName() + " " +
                Player_CurrentHealth + "/" +
                player.getMaxHealth() + "  | " +
                monster.getName() + " " +
                Monster_CurrentHealth + "/" +
                monster.getMaxHealth() + "\n" );
    }

}


// test
// javac *.java && java GameEngine