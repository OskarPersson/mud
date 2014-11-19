package com.ioopm;

public class Book implements Item{
    private String name;
    private int inventorySpace;
    private String author;
    private int year;

    /**
     * Creates a book with a name, author and year
     * @param name name of the book
     * @param author author of the book
     * @param year the year the book was released
     */

    public Book(String name, String author, int year){
        this.name = name;
        this.author = author;
        this.year = year;
    }

    /**
     * Creates a book with a name, author, year and inventory space
     * @param name name of the book
     * @param author author of the book
     * @param year the year the book was released
     * @param space the amount of space the book takes in the inventory
     */

    public Book(String name, String author, int year, int space){
        this.name = name;
        this.inventorySpace = space;
        this.author = author;
        this.year = year;
    }

    public int getSpace(){
        return inventorySpace;
    }

    public void setSpace(int space){
        inventorySpace = space;
    }

    public String getName(){
        return name;
    }

    public boolean equals(Item item){
        return item.getName().toLowerCase().equals(name);
    }

    /**
     * Gets a string representing of the book
     * @return a string representation of the book
     */

    public String toString(){
        return "\nBook:\nTitle: " + getName() + "\nYear: " + year + "\nAuthor: " + author + "\nSpace: " + getSpace() + "\n";
    }
}