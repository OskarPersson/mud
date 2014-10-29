package com.ioopm;

public class Book extends Item{
    private String name;
    private String author;
    private int year;

    public Book(String name, String author, int year, int space){
        super(space);
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Title: " + name + "\nYear: " + year + "\nAuthor: " + author + "\nSpace: " + getSpace() + "\n";
    }
}
