package com.ioopm;

public abstract class Item {
    private int inventorySpace;

    public Item(int space){
        inventorySpace = space;
    }

    public int getSpace(){
        return inventorySpace;
    }
}
