import java.util.Scanner;

public class WorldMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Worldraft world=new Worldraft();
        Player player=new Player();
        Monster monster=new Monster();
//      world.method1(player.getName());
        world.InitialWorld();

        System.out.print("\n>");


        //movement
        while(true){
            String playerMoveAction=scanner.nextLine();

            if ((world.getPlayerWidth()==world.getMonsterWidth())
                    && ((playerMoveAction.equals("s") && (world.getPlayerHeight() == (world.getMonsterHeight()-1)))
                    || (playerMoveAction.equals("w") && (world.getPlayerHeight() == (world.getMonsterHeight()+1))))){
                WorldMain.playerMovement(player, monster); //movement
                WorldMain.battleLoop(player, monster);  //battle
                break;
            }
            else if ((world.getPlayerHeight()==world.getMonsterHeight())
                    && ((playerMoveAction.equals("a") && (world.getPlayerWidth() == (world.getMonsterWidth()+1)))
                    || (playerMoveAction.equals("d") && (world.getPlayerWidth() == (world.getMonsterWidth()-1))))){
                WorldMain.playerMovement(player, monster); //movement
                WorldMain.battleLoop(player, monster); //battle
                break;
            } else{
//                WorldMain.playerMovement(scanner, world);
                if (playerMoveAction.equals("w")){ //North
                    world.NorthMovement(world.getPlayerHeight(), world.getPlayerWidth());
                }else if (playerMoveAction.equals("s")){ //South
                    world.SouthMovement(world.getPlayerHeight(), world.getPlayerWidth());
                }else if (playerMoveAction.equals("a")) { //West
                    world.WestMovement(world.getPlayerHeight(), world.getPlayerWidth());
                }else if (playerMoveAction.equals("d")) { //East
                    world.EastMovement(world.getPlayerHeight(), world.getPlayerWidth());
                }

                System.out.print("\n>");
            }
        }


        //For Test!!!!!!!!!!
//        System.out.println("Player Height = "+world.getPlayerHeight());
//        System.out.println("Player Width = "+world.getPlayerWidth());
//        System.out.println("Monster Height = "+world.getMonsterHeight());
//        System.out.println("Monster Width = "+world.getMonsterWidth());

    }

    private static void playerMovement(Player player, Monster monster) {
        System.out.println(player.getName() + " encountered a " + monster.getName() + "!\n");

        System.out.println(player.getName() + " " +
                player.getCurrentHealth() + "/" +
                player.getMaxHealth() + " | " +
                monster.getName() + " " +
                monster.getCurrentHealth() + "/" +
                monster.getMaxHealth()+"\n"+
                player.getName() + " attacks " + monster.getName() + " for "+player.getDamage()+ " damage."+"\n"
                +monster.getName() + " attacks " + player.getName() + " for "+monster.getDamage()+ " damage.\n");
    }

    //Battle loop
    private static void battleLoop(Player player, Monster monster) { ////need to check!!!!
        //for test
        String playername="jasmyn";    // need to change to player.getName()
        String monstername="Angela";   // need to change to monster.getName()

        int Player_Damage=2;
        int Monster_Damage=2;

        int Player_MaxHealth=20;
        int Monster_MaxHealth=15;

        int Player_CurrentHealth=Player_MaxHealth;
        int Monster_CurrentHealth=Monster_MaxHealth;

        while(true) {
            if(Player_CurrentHealth >= Monster_Damage && Monster_CurrentHealth >= Player_Damage){
                Monster_CurrentHealth = Monster_CurrentHealth - Player_Damage;

                if(Monster_CurrentHealth >= Player_Damage){
                    Player_CurrentHealth = Player_CurrentHealth - Monster_Damage;

                    if(Player_CurrentHealth >= Monster_Damage){
                        System.out.println(playername + " " +
                                Player_CurrentHealth + "/" +
                                Player_MaxHealth + " | " +
                                monstername + " " +
                                Monster_CurrentHealth + "/" +
                                Monster_MaxHealth + "\n" +
                                playername + " attacks " + monstername + " for " + Player_Damage + " damage." + "\n"
                                + monstername + " attacks " + playername + " for " + Monster_Damage + " damage.\n");

                    }else{ //Player_CurrentHealth < Monster_Damage
                        System.out.println(playername + " " +
                                Player_CurrentHealth + "/" +
                                Player_MaxHealth + " | " +
                                monstername + " " +
                                Monster_CurrentHealth + "/" +
                                Monster_MaxHealth + "\n" +
                                monstername + " attacks " + playername + " for " + Monster_Damage + " damage." + "\n"
                                +  monstername + " wins!\n");

                        System.out.println("(Press enter key to return to main menu)");
                        Player_CurrentHealth = Player_CurrentHealth - Monster_Damage;

                        //for test
//                        System.out.println(playername+"'s current health="+Player_CurrentHealth);
//                        System.out.println(monstername+"'s current health="+Monster_CurrentHealth);
                        break;
                    }

                }else{ //Monster_CurrentHealth < Player_Damage
                        Player_CurrentHealth = Player_CurrentHealth - Monster_Damage;
                        System.out.println(playername + " " +
                                Player_CurrentHealth + "/" +
                                Player_MaxHealth + " | " +
                                monstername + " " +
                                Monster_CurrentHealth + "/" +
                                Monster_MaxHealth + "\n" +
                                playername + " attacks " + monstername + " for " + Player_Damage + " damage." + "\n"
                                +  playername + " wins!\n");

                        System.out.println("(Press enter key to return to main menu)");
                        Monster_CurrentHealth = Monster_CurrentHealth - Player_Damage;

                        //for test
//                        System.out.println(playername+"'s current health="+Player_CurrentHealth);
//                        System.out.println(monstername+"'s current health="+Monster_CurrentHealth);
                        break;

                }
            }else if(Player_CurrentHealth >= Monster_Damage && Monster_CurrentHealth < Player_Damage){
                Player_CurrentHealth = Player_CurrentHealth - Monster_Damage;
                System.out.println(playername + " " +
                        Player_CurrentHealth + "/" +
                        Player_MaxHealth + " | " +
                        monstername + " " +
                        Monster_CurrentHealth + "/" +
                        Monster_MaxHealth + "\n" +
                        playername + " attacks " + monstername + " for " + Player_Damage + " damage." + "\n"
                        +  playername + " wins!\n");

                System.out.println("(Press enter key to return to main menu)");
                Monster_CurrentHealth = Monster_CurrentHealth - Player_Damage;

                //for test
//                System.out.println(playername+"'s current health="+Player_CurrentHealth);
//                System.out.println(monstername+"'s current health="+Monster_CurrentHealth);
                break;

            }else if (Player_CurrentHealth < Monster_Damage && Monster_CurrentHealth >= Player_Damage){
                System.out.println(playername + " " +
                        Player_CurrentHealth + "/" +
                        Player_MaxHealth + " | " +
                        monstername + " " +
                        Monster_CurrentHealth + "/" +
                        Monster_MaxHealth + "\n" +
                        monstername + " attacks " + playername + " for " + Monster_Damage + " damage." + "\n"
                        +  monstername + " wins!\n");

                System.out.println("(Press enter key to return to main menu)");
                Player_CurrentHealth = Player_CurrentHealth - Monster_Damage;

                //for test
//                System.out.println(playername+"'s current health="+Player_CurrentHealth);
//                System.out.println(monstername+"'s current health="+Monster_CurrentHealth);
                break;
            }else{break;}


        }


    }



}


//test
//javac WorldMain.java && java WorldMain