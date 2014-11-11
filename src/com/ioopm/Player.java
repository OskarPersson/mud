package com.ioopm;

import java.util.ArrayList;

public class Player extends Person {
    private Inventory inventory;
    private ArrayList<Course> finishedCourses;
    private ArrayList<Course> unfinishedCourses;
    private int hp;

    /**
     * Creates the player with a name, 60 HP, an empty inventory, a set of finished courses,
     * no unfinished courses and a start room
     * @param name The name of the player
     */
    public Player(String name, ArrayList<Course> startCourses, Room startRoom){
        super(name);
        hp = 60;
        inventory           = new Inventory();
        finishedCourses     = startCourses;
        unfinishedCourses   = new ArrayList<>();
        setRoom(startRoom);
    }

    /**
     * Moves the player, if possible, in the given direction, prints the description of the new room and the teachers
     * in the room may ask questions.
     * If not possible, a message is printed.
     * @param direction The direction [north, east, south, or west] to go
     */

    public void go(String direction) {
        Door requestedDoor = getRoom().getDoor(direction);
        if (!(requestedDoor == null)){
            if (!requestedDoor.isLocked()) {
                setRoom(requestedDoor.otherRoom(getRoom()));
                System.out.println(getRoom());
                getRoom().askQuestions(this);
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
        if (getRoom().getDoor(direction) != null){
            if (inventory.useKey()) {
                getRoom().getDoor(direction).unlock();
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
        Item item = getRoom().findItem(name);
        if (item != null) {
            inventory.addItem(item);
            getRoom().removeItem(item);
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
            getRoom().addItem(item);
        }
    }

    /**
     * Enroll a course
     * @param course the course to enroll
     */

    public void enroll(Course course) {
        if (course != null && !unfinishedCourses.contains(course) && !finishedCourses.contains(course)) {
            for (Teacher teacher : getRoom().getTeachers()) {
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
        Student student = getRoom().findStudent(name);
        if (student != null){
            Item itemToTrade = getInventory().findItem(student.getCurrentCourse().getBook().getName());
            if (itemToTrade != null){
                inventory.removeItem(itemToTrade);
                Question question = student.getCurrentCourse().getQuestion();
                System.out.println("The correct answer to \""+ question.getText() +"\" is \""+ question.getCorrectAnswer() +"\" ");
            }
        }else if (name.equals("sphinx") && getRoom().hasSphinx()) {
            getRoom().getSphinx().talk();
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
        if (hp >= 180 && !unfinishedCourses.isEmpty() && getRoom().hasSphinx()){
            System.out.println("You graduated!");
            System.out.println("Finished Courses:");
            for (Course course : finishedCourses) {
                System.out.println(course.getName());
            }
        }else if(!getRoom().hasSphinx()){
            System.out.println("The sphinx is not in this room");
        }else{
            System.out.println("You don't qualify yet, you need 180 HP (you have "+hp+") and no unfinished courses");
        }
    }

    /**
     * Gets the player's finished courses
     * @return the player's finished courses
     */

    public ArrayList<Course> getFinishedCourses(){
        return finishedCourses;
    }

    /**
     * Gets the player's unfinished courses
     * @return the player's unfinished courses
     */

    public ArrayList<Course> getUnfinishedCourses(){
        return unfinishedCourses;
    }

    /**
     * Finishes a player's course and increases the player's HP with the course HP
     * @param course the course to move
     */

    public void finishCourse(Course course){
        unfinishedCourses.remove(course);
        finishedCourses.add(course);
        hp += course.getHP();
    }

    /**
     * Moves a player's finished course to the unfinished courses and removes the player's HP with the course HP
     * @param course the course to move
     */

    public void removeFinishedCourse(Course course){
        finishedCourses.remove(course);
        unfinishedCourses.add(course);
        hp -= course.getHP();
    }

    /**
     * Prints the player's courses
     */

    public void printCourses(){
        System.out.println("HP: " + hp);
        System.out.println("Unfinished Courses:");
        for (Course course : unfinishedCourses) {
            System.out.println(course.getName() + " (" + course.getHP() + "HP)");
        }
        System.out.println("\nFinished Courses:");
        for (Course course : finishedCourses) {
            System.out.println(course.getName() + " (" + course.getHP() + "HP)");
        }
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
