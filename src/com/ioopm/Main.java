package com.ioopm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
        while(running) {
            getInput();
        }
        scanner.close();
    }

    private Main(){
        world  = new World("res/world.txt", "res/books.txt", "res/courses.txt", "res/questions.txt");

        //Get all courses, shuffle the list and set the first 6 as the player's start courses
        ArrayList<Course> allCourses = world.getCourses();
        Collections.shuffle(allCourses);
        ArrayList<Course> startCourses = new ArrayList<>();
        Random ran = new Random();
        while(startCourses.size() < 6){
            startCourses.add(allCourses.remove(0));
        }
        player = new Player("PLAYER", startCourses);

        //Set the player room
        player.setRoom(world.randRoom());
        System.out.println("Current room:\n" + player.getRoom());
    }

    public static void main(String[] args){
        Main main = new Main();
        main.gameLoop();
    }
}
