package com.ioopm;

public class Key implements Item {
    private String name;
    private int inventorySpace;
    private boolean used;

    /**
     * Creates a key with inventory space set to 1 and the name set to "Key"
     */

    public Key(){
        name = "Key";
        inventorySpace = 0;
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
        return item.getName().toLowerCase().equals("key");
    }

    public String toString(){
        return getName();
    }


}
