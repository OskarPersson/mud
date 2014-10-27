package com.ioopm;
import java.util.Scanner;

public class Main{
	
	private Scanner scanner = new Scanner(System.in);
	private String currentInput;
	private Command command;

	private enum Command {
		GO, PICKUP, USEKEYWITH, DROP, INVENTORY,
		ENROLL, TALK, GRADUATE, TRADE;
	}

	private Main(){
		
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
		currentInput = scanner.nextLine().toLowerCase();
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

    public static void main(String[] args){
		Main main = new Main();
		main.gameLoop();
    }
}
