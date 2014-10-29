package com.ioopm;

public class Course {
    String name;
    private Book book;
    private int HP;

    public Course(String name, Book book, int HP){
        this.name = name;
        this.book = book;
        this.HP = HP;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Name: " + name + "\nBook: " + book + "\nHP: " + HP + "\n";
    }
}
