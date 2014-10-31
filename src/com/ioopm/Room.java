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
    private ArrayList<Item> items;

    public Room(String name){
        this.name = name;
        items = new ArrayList<Item>();
        teachers = new ArrayList<Teacher>();
    }

    public void setDoors(Door north, Door east, Door south, Door west){
        this.NORTH = north;
        this.EAST = east;
        this.SOUTH = south;
        this.WEST = west;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public String getName(){
        return this.name;
    }

    public Door getDoor(String direction){
        switch(direction.toLowerCase()){
            case "north":
                return NORTH;
            case "east":
                return EAST;
            case "south":
                return SOUTH;
            case "west":
                return WEST;
            default:
                return null;
        }
    }

    public Item findItem(String name){
        for (Item item: items){
            if (item.getName().toLowerCase().equals(name.toLowerCase())){
                return item;
            }
        }
        System.out.println(name + " does not exist in this room");
        return null;
    }

    public String toString(){
        String roomDesc = this.name +
                "\nNORTH: " + ((NORTH == null) ? "X" : NORTH.getRoom().getName()  + " Locked: " + NORTH.getLocked()) +
                "\nEAST: "  + ((EAST  == null) ? "X" : EAST.getRoom().getName()  + " Locked: " + EAST.getLocked()) +
                "\nSOUTH: " + ((SOUTH == null) ? "X" : SOUTH.getRoom().getName() + " Locked: " + SOUTH.getLocked()) +
                "\nWEST: "  + ((WEST  == null) ? "X" : WEST.getRoom().getName()  + " Locked: " + WEST.getLocked()) + "\n";

        String itemString = "\nItems: \n";
        for(Item item : items){
            itemString += item + "\n";
        }

        String teacherString = "\nTeachers: \n";
        for(Teacher t : teachers){
            teacherString += t.toString() + "\n";
        }
        return roomDesc + itemString + teacherString;
    }
}
