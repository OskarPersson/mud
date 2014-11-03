package com.ioopm;

public class Book extends Item{
    private String author;
    private int year;

    public Book(String name, String author, int year, int space){
        super(space, name);
        this.author = author;
        this.year = year;
    }

    public String toString(){
        return "Title: " + getName() + "\nYear: " + year + "\nAuthor: " + author + "\nSpace: " + getSpace() + "\n";
    }
}