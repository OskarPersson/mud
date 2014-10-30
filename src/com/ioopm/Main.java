package com.ioopm;
import java.util.Scanner;

public class Main{
    private Scanner scanner = new Scanner(System.in);
    private World world;
    private Player player;
    private boolean running = true;

    private void getInput(){
        System.out.println("Enter command: ");
        String currentInput = scanner.nextLine().toLowerCase();
        if (currentInput.substring(0, 3).equals("go ")) {
            String rest = currentInput.substring(3);
            if (!(rest.equals("north") || rest.equals("south") || rest.equals("east") || rest.equals("west"))) {
                System.out.println("Incorrect input, try again");
                getInput();
            } else {
                player.go(rest);
            }
        }else if (currentInput.substring(0, 4).equals("quit")){
            running = false;
        }else{
            System.out.println("Incorrect input, try again");
            getInput();
        }
    }

    private void gameLoop(){
        while(running) {
            getInput();
        }
        scanner.close();
    }

    public void init(){
        world  = new World("res/world.txt", "res/books.txt", "res/courses.txt");
        player = new Player("PLAYER");
        player.setRoom(world.randRoom());
        System.out.println("Current room:\n" + player.getRoom());
    }

    public static void main(String[] args){
        Main main = new Main();
        main.init();
        main.gameLoop();
    }
}
