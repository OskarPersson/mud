package com.ioopm;

import java.util.ArrayList;

public class Player extends Person {
    private Inventory inventory;
    private ArrayList<Course> finishedCourses;
    private ArrayList<Course> unfinishedCourses;
    private int hp;
    private Room currentRoom;

    public Player(String name){
        super(name);
        hp = 60;
        inventory = new Inventory();
    }

    public void go(String direction) {
        Door requestedDoor = currentRoom.getDoor(direction);
        if (!(requestedDoor == null)){
            if (!requestedDoor.getLocked()) {
                setRoom(requestedDoor.getRoom());
                System.out.println(currentRoom);
            } else {
                System.out.println("Dörren är låst");
            }
        }else{
            System.out.println("Finns ingen dörr här");
        }

    }

    public void setRoom(Room room){
        this.currentRoom = room;
    }
    public Room getRoom(){
        return currentRoom;
    }
}
