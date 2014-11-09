package com.ioopm;

import java.util.ArrayList;

public class Player extends Person {
    private Inventory inventory;
    private ArrayList<Course> finishedCourses;
    private ArrayList<Course> unfinishedCourses;
    private int hp;
    private Room currentRoom;

    /**
     * Creates the player with a name, 60 HP, an empty inventory, a set of finished courses
     * and no unfinished courses
     * @param name The name of the player
     */
    public Player(String name, ArrayList<Course> startCourses){
        super(name);
        hp = 60;
        inventory           = new Inventory();
        finishedCourses     = startCourses;
        unfinishedCourses   = new ArrayList<>();
    }

    /**
     * Moves the player, if possible, in the given direction and prints the description of the new room.
     * If not possible, a message is printed.
     * @param direction The direction [north, east, south, or west] to go
     */

    public void go(String direction) {
        Door requestedDoor = currentRoom.getDoor(direction);
        if (!(requestedDoor == null)){
            if (!requestedDoor.isLocked()) {
                setRoom(requestedDoor.otherRoom(currentRoom));
                System.out.println(currentRoom);
            } else {
                System.out.println("That door is locked!");
            }
        }else{
            System.out.println("There is no door in this direction.");
        }
    }

    /**
     * Use a key in the inventory to unlock a door in the given direction.
     * Prints a message if there is no door in the given direction.
     * @param direction the direction of the door to unlock
     */

    public void unlock(String direction){
        if (currentRoom.getDoor(direction) != null){
            if (inventory.useKey()) {
                currentRoom.getDoor(direction).unlock();
            }
        }else{
            System.out.println("There is no door in this direction.");
        }
    }

    /**
     * Picks up an item and adds it to the inventory
     * @param name the name of the item
     */

    public void pickup(String name){
        Item item = currentRoom.findItem(name);
        if (item != null) {
            inventory.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up " + item.getName());
        }
    }

    /**
     * Drops an item and adds it to the room
     * @param name the name of the item
     */

    public void drop(String name){
        Item item = inventory.findItem(name);
        if (item != null){
            System.out.println("You dropped " + item.getName());
            inventory.removeItem(item);
            currentRoom.addItem(item);
        }
    }

    /**
     * Enroll a course
     * @param course the course to enroll
     */

    public void enroll(Course course) {
        if (course != null && !unfinishedCourses.contains(course) && !finishedCourses.contains(course)) {
            for (Teacher teacher : currentRoom.getTeachers()) {
                if (teacher.getCourse() == course) {
                    System.out.println("Enrolled " + course.getName());
                    unfinishedCourses.add(course);
                }
            }
        }
    }

    /**
     * Talk to a student or a sphinx
     * @param name name of the student or the sphinx to talk to
     */

    public void talk(String name){
        Student student = currentRoom.findStudent(name);
        if (student != null){
            Item itemToTrade = getInventory().findItem(student.getCurrentCourse().getBook().getName());
            if (itemToTrade != null){
                inventory.removeItem(itemToTrade);
                Question question = student.getCurrentCourse().getQuestion();
                System.out.println("The correct answer to \""+ question.getText() +"\" is \""+ question.getCorrectAnswer() +"\" ");
            }
        }else if (name.equals("sphinx") && currentRoom.hasSphinx()) {
            currentRoom.getSphinx().talk();
        }else{
            System.out.println("The sphinx or any student with the name " + name + " is in this room");
        }
    }

    /**
     * Give a student the book of the student's current course in exchange
     * for the book to the student's finished course
     * @param student the student to trade with
     */

    public void trade(Student student){
        Item itemToTrade = this.getInventory().findItem(student.getCurrentCourse().getBook().getName());
        if (itemToTrade != null){
            inventory.addItem(student.getFinishedCourse().getBook());
            inventory.removeItem(itemToTrade);
        }
    }

    /**
     * Graduate if the player has >= 180 HP, no unfinished courses and
     * is in the same room as the sphinx
     */

    public void graduate(){
        if (hp >= 180 && !unfinishedCourses.isEmpty() && currentRoom.hasSphinx()){
            System.out.println("You graduated!");
            System.out.println("Finished Courses:");
            for (Course course : finishedCourses) {
                System.out.println(course.getName());
            }
        }else if(!currentRoom.hasSphinx()){
            System.out.println("The sphinx is not in this room");
        }else{
            System.out.println("You don't qualify yet, you need 180 HP (you have "+hp+") and no unfinished courses");
        }
    }

    /**
     * Prints the player's courses
     */

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

    /**
     * Sets the room of the player
     * @param room the new room
     */

    public void setRoom(Room room){
        if (room != null) {
            this.currentRoom = room;
        }else{
            System.out.println("You have to be in a room.");
        }
    }

    /**
     * Gets the player's current room
     * @return current room of the player
     */

    public Room getRoom(){
        return currentRoom;
    }

    /**
     * Gets the player's HP
     * @return player's HP
     */

    public int getHP(){return hp; }

    /**
     * Checks if the player has any unfinished courses
     * @return true if the player has unfinished courses, else false
     */

    public boolean hasUnfinishedCourses(){
        return !unfinishedCourses.isEmpty();
    }

    /**
     * Gets the player's inventory
     * @return inventory of the player
     */

    public Inventory getInventory(){
        return inventory;
    }


}
