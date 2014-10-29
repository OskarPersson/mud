package com.ioopm;

import java.util.ArrayList;

public class Player extends Person {
    private Inventory inventory;
    private ArrayList<Course> finishedCourses;
    private ArrayList<Course> unfinishedCourses;
    private int hp;

    public Player(String name){
        super(name);
        hp = 60;
        inventory = new Inventory();
    }
}
