package com.ioopm;


import java.util.ArrayList;

public class Room {
    private String name;
    private Door NORTH;
    private Door SOUTH;
    private Door EAST;
    private Door WEST;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Item> items;
    private Sphinx sphinx;

    public Room(String name){
        this.name = name;
        items       = new ArrayList<>();
        teachers    = new ArrayList<>();
        students    = new ArrayList<>();
        sphinx      = null;
    }
    public void addSphinx(Sphinx s){
        sphinx = s;
    }

    public boolean hasSphinx(){
        return sphinx != null;
    }

    public Sphinx getSphinx(){
        return sphinx;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    public String getName(){
        return this.name;
    }

    public void setDoor(Door door, String direction){
        switch(direction.toLowerCase()){
            case "north":
                this.NORTH = door;
                break;
            case "east":
                this.EAST = door;
                break;
            case "south":
                this.SOUTH = door;
                break;
            case "west":
                this.WEST = door;
                break;
        }
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

    public Student findStudent(String name){
        for (Student student : students){
            if (student.getName().toLowerCase().equals(name.toLowerCase())){
                return student;
            }
        }
        System.out.println(name + " does not exist in this room");
        return null;
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

    public void removeItem(Item item){
        items.remove(item);
    }

    public String toString(){
        String roomDesc = this.name +
                "\nNORTH: " + ((NORTH == null) ? "X" : NORTH.otherRoom(this).getName()  + " Locked: " + NORTH.isLocked()) +
                "\nEAST: "  + ((EAST  == null) ? "X" : EAST.otherRoom(this).getName()   + " Locked: " + EAST.isLocked()) +
                "\nSOUTH: " + ((SOUTH == null) ? "X" : SOUTH.otherRoom(this).getName()  + " Locked: " + SOUTH.isLocked()) +
                "\nWEST: "  + ((WEST  == null) ? "X" : WEST.otherRoom(this).getName()   + " Locked: " + WEST.isLocked()) + "\n";

        String itemString = "\nItems: \n";
        for(Item item : items){
            itemString += item + "\n";
        }

        String teacherString = "\nTeachers: \n";
        for(Teacher t : teachers){
            teacherString += t.toString() + "\n";
        }

        String studentString = "\nStudents: \n";
        for(Student s : students){
            studentString += s.toString() + "\n";
        }
        String sphinxString = "";

        if (sphinx!=null){
           sphinxString = "\nThe sphinx is in this room";
        }

        return roomDesc + itemString + teacherString + studentString + sphinxString;
    }
}
