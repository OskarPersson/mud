package com.ioopm;

import java.util.ArrayList;

public class Inventory {
    private int capacity;
    private ArrayList<Item> items;

    /**
     * Creates an empty inventory with a capacity of 10
     */

    public Inventory(){
        items = new ArrayList<>();
        capacity = 10;
    }

    /**
     * Adds an item (if it fits) to the inventory
     * @param item the item to add
     */

    public void addItem(Item item){
        if (capacity - item.getSpace() >= 0) {
            items.add(item);
            capacity -= item.getSpace();
        }else{
            System.out.println("Your inventory is full");
        }
    }

    /**
     * Removes an item from the inventory
     * @param itemToRemove the item to remove
     */

    public void removeItem(Item itemToRemove){
        for (Item item : items){
            if (item.equals(itemToRemove)){
                items.remove(item);
                capacity -= item.getSpace();
                break;
            }
        }
    }

    /**
     * Finds an item with the given name in the inventory
     * @param name name of the item
     * @return the item with the given name, null if it doesn't exist
     */

    public Item findItem(String name){
        for (Item item : items){
            if (item.getName().toLowerCase().equals(name.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    /**
     * Uses the key and removes it
     * @return true if the key is used, else false
     */
    
    public boolean useKey(){
        for (Item item : items){
            if (item instanceof Key){
                System.out.println("Unlocked door");
                removeItem(item);
                return true;
            }
        }
        System.out.println("You have no keys");
        return false;
    }

    /**
     * Lists the items in the inventory
     * @return a string representation of the inventory
     */

    public String toString(){
        String result = "\n";
        if (items.size() != 0) {
            for (Item item : items) {
                result = result.concat(item + "\n");
            }
        }else{
            return "Inventory is empty";
        }
        return result;
    }
}
