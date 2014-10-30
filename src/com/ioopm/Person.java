package com.ioopm;

public class Person {
    private String name;
    private Room currentRoom;

    public Person(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
    public void setRoom(Room room){
        this.currentRoom = room;
    }
    public Room getRoom(){
        return currentRoom;
    }
}
