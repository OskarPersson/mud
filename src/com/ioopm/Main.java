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
        }else if (currentInput.equals("courses")){
            player.printCourses();
        }else if (currentInput.substring(0, 9).equals("inventory")){
            System.out.println(player.getInventory());
        }else if (currentInput.substring(0, 7).equals("pick up")){
            String rest = currentInput.substring(8);
            player.pickup(rest);
        }else if (currentInput.substring(0, 6).equals("enroll")){
            String rest = currentInput.substring(7);
            player.enroll(world.findCourse(rest));
        }else if (currentInput.substring(0, 13).equals("use key with ")){
            String rest = currentInput.substring(13);
            if (!(rest.equals("north") || rest.equals("south") || rest.equals("east") || rest.equals("west"))) {
                System.out.println("Incorrect input, try again");
                getInput();
            } else {
                player.unlock(rest);
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

    private Main(){
        world  = new World("res/world.txt", "res/books.txt", "res/courses.txt");
        player = new Player("PLAYER");
        player.setRoom(world.randRoom());
        System.out.println("Current room:\n" + player.getRoom());
    }

    public static void main(String[] args){
        Main main = new Main();
        main.gameLoop();
    }
}
