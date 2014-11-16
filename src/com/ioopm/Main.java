package com.ioopm;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    private Scanner scanner = new Scanner(System.in);
    private World world;
    private Player player;
    private boolean running = true;

    private void getInput(){
        System.out.println("Enter command: ");
        String currentInput = scanner.nextLine().toLowerCase();
        try {
            if (currentInput.startsWith("go")) {
                String rest = currentInput.substring(3);
                if (!(rest.startsWith("north") || rest.startsWith("south") || rest.startsWith("east") || rest.startsWith("west"))) {
                    System.out.println("Incorrect input, try again");
                    getInput();
                } else {
                    player.go(rest);
                }
            } else if (currentInput.equals("courses")) {
                player.printCourses();
            } else if (currentInput.equals("inventory")) {
                System.out.println(player.getInventory());
            } else if (currentInput.startsWith("pick up")) {
                String rest = currentInput.substring(8);
                player.pickup(rest);
            } else if (currentInput.startsWith("drop")) {
                String rest = currentInput.substring(5);
                player.drop(rest);
            } else if (currentInput.equals("room")) {
                System.out.println(player.getRoom());
            } else if (currentInput.startsWith("trade")) {
                String rest = currentInput.substring(6);
                player.trade(player.getRoom().findStudent(rest));
            } else if (currentInput.startsWith("enroll")) {
                String rest = currentInput.substring(7);
                player.enroll(world.findCourse(rest));
            } else if (currentInput.equals("graduate")) {
                player.graduate();
            } else if (currentInput.startsWith("talk")) {
                String rest = currentInput.substring(5);
                player.talk(rest);
            } else if (currentInput.startsWith("use key with")) {
                String rest = currentInput.substring(13);
                if (!(rest.startsWith("north") || rest.startsWith("south") || rest.startsWith("east") || rest.startsWith("west"))) {
                    System.out.println("Incorrect input, try again");
                    getInput();
                } else {
                    player.unlock(rest);
                }
            } else if (currentInput.equals("quit")) {
                running = false;
            } else {
                System.out.println("Incorrect input, try again");
                getInput();
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("Incorrect input, try again");
            getInput();
        }
    }

    private void gameLoop(){
        int i = 1;
        while(running) {
            getInput();
            if (i == 4){
                world.movePersons(player);
                i = 1;
            }else{
                i++;
            }
        }
        scanner.close();
    }

    private Main(){
        world  = new World("res/world.txt", "res/names.txt", "res/books.txt", "res/courses.txt", "res/questions.txt");

        //Get 6 first courses and set as the player's start courses
        ArrayList<Course> allCourses = world.getCourses();
        ArrayList<Course> startCourses = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            startCourses.add(allCourses.get(i));
        }
        
        player = new Player("PLAYER", startCourses, world.randRoom());
        System.out.println("Current room:\n" + player.getRoom());
        player.getRoom().askQuestions(player);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.gameLoop();
    }
}
