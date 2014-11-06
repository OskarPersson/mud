package com.ioopm;

public abstract class Item {
    private int inventorySpace;
    private String name;

    /**
     * Creates an item with a given space and name
     * @param space the inventory space of the item
     * @param name the name of the item
     */

    public Item(int space, String name){
        inventorySpace = space;
        this.name = name;
    }

    /**
     * Gets the inventory space the item takes
     * @return the inventory space the item takes
     */

    public int getSpace(){
        return inventorySpace;
    }

    /**
     * Gets the name of the item
     * @return the name of the item
     */

    public String getName(){
        return name;
    }

    /**
     * Gets the name of the item
     * @return the name of the item
     */

    public String toString(){
        return name;
    }
}
