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
        inventory           = new Inventory();
        finishedCourses     = new ArrayList<Course>();
        unfinishedCourses   = new ArrayList<Course>();
    }

    public void go(String direction) {
        Door requestedDoor = currentRoom.getDoor(direction);
        if (!(requestedDoor == null)){
            if (!requestedDoor.getLocked()) {
                setRoom(requestedDoor.otherRoom(currentRoom));
                System.out.println(currentRoom);
            } else {
                System.out.println("Dörren är låst");
            }
        }else{
            System.out.println("Finns ingen dörr här");
        }
    }

    public void unlock(String direction){
        if (currentRoom.getDoor(direction) != null){
            if (inventory.useKey()) {
                currentRoom.getDoor(direction).unlock();
            }
        }else{
            System.out.println("Finns ingen dörr här");
        }
    }

    public void pickup(String name){
        Item item = currentRoom.findItem(name);
        if (item != null) {
            inventory.addItem(item);
            currentRoom.removeItem(item);
        }
    }

    public void drop(String name){
        Item item = inventory.findItem(name);
        if (item != null){
            inventory.removeItem(item);
        }
        currentRoom.addItem(item);
    }

    public void enroll(Course course) {
        if (course != null && !unfinishedCourses.contains(course) && !finishedCourses.contains(course)) {
            for (Teacher teacher : currentRoom.getTeachers()) {
                if (teacher.getCourse() == course) {
                    unfinishedCourses.add(course);
                }
            }
        }
    }

    public void trade(Student student){
        Item itemToTrade = this.getInventory().findItem(student.getCurrentCourse().getBook().getName());
        if (itemToTrade != null){
            inventory.addItem(student.getFinishedCourse().getBook());
            inventory.removeItem(itemToTrade);
        }
    }

    public void printCourses(){
        System.out.println("Unfinished Courses:");
        for (Course course : unfinishedCourses) {
            System.out.println(course.getName());
        }
        System.out.println("\nFinished Courses:");
        for (Course course : finishedCourses) {
            System.out.println(course.getName());
        }
    }

    public void setRoom(Room room){
        if (room != null) {
            this.currentRoom = room;
        }else{
            System.out.println("Du måste vara i ett rum");
        }
    }

    public Room getRoom(){
        return currentRoom;
    }
    public int getHP(){return hp; }
    public boolean hasUnfinishedCourses(){
        return !unfinishedCourses.isEmpty();
    }
    public Inventory getInventory(){
        return inventory;
    }


}
