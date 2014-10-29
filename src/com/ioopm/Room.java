package com.ioopm;


import java.util.ArrayList;

public class Room {
    private String name;
    private Door NORTH;
    private Door SOUTH;
    private Door EAST;
    private Door WEST;
    private ArrayList<Student> student;
    private ArrayList<Teacher> teachers;
    private ArrayList<Book> books;
    private ArrayList<Key> keys;

    public Room(String name){
        this.name = name;
    }

    public void setDoors(Door north, Door east, Door south, Door west){
        this.NORTH = north;
        this.EAST = east;
        this.SOUTH = south;
        this.WEST = west;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name +
                "\nNORTH: " + ((NORTH == null) ? "X" : NORTH.getRoom().getName()  + " Locked: " + NORTH.getLocked()) +
                "\nEAST: "  + ((EAST  == null) ? "X" : EAST.getRoom().getName()  + " Locked: " + EAST.getLocked()) +
                "\nSOUTH: " + ((SOUTH == null) ? "X" : SOUTH.getRoom().getName() + " Locked: " + SOUTH.getLocked()) +
                "\nWEST: "  + ((WEST  == null) ? "X" : WEST.getRoom().getName()  + " Locked: " + WEST.getLocked());
    }
}
