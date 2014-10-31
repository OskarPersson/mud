package com.ioopm;

public abstract class Item {
    private int inventorySpace;
    private String name;

    public Item(int space, String name){
        inventorySpace = space;
        this.name = name;
    }

    public int getSpace(){
        return inventorySpace;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }
}
