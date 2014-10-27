package com.ioopm;


import java.util.ArrayList;

public class Room {
    private String name;
    private RoomConnection NORTH;
    private RoomConnection SOUTH;
    private RoomConnection EAST;
    private RoomConnection WEST;
    private ArrayList<Student> student;
    private ArrayList<Teacher> teachers;
    private ArrayList<Book> books;
    private ArrayList<Key> keys;
}
