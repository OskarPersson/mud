package com.ioopm;
import java.util.Scanner;

public class Main{
    private Scanner scanner = new Scanner(System.in);
    private Command command;
    private World world;
    private Player player;

    private enum Command {
        GO, PICKUP, USEKEYWITH, DROP, INVENTORY,
        ENROLL, TALK, GRADUATE, TRADE;
    }

    private void update(){
        switch (command){
        case GO:
            System.out.println("go somewhere");
            break;
        default:
            break;
        }
    }

    private void getInput(){
        System.out.println("Enter command: ");
        String currentInput = scanner.nextLine().toLowerCase();
        if (currentInput.substring(0, 3).equals("go ")){
            String rest = currentInput.substring(3);
            if (!(rest.equals("north") || rest.equals("south") || rest.equals("east") || rest.equals("west"))){
                System.out.println("Incorrect input, try again");
                getInput();
            }else{
                command = Command.GO;
            }
        }else{
            System.out.println("Incorrect input, try again");
            getInput();
        }
    }

    private void gameLoop(){
        getInput();
        update();
    }

    public void init(){
        world  = new World("res/world.txt", "res/books.txt", "res/courses.txt");
        player = new Player("PLAYER");
        System.out.println(world);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.init();
        main.gameLoop();
    }
}
