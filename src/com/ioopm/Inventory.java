package com.ioopm;

import java.util.ArrayList;

public class Inventory<T extends Item> {
    private int capacity;
    private ArrayList<T> items;

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
     * @return true if item was added, false otherwise.
     */

    public void addItem(T item) throws InventoryFullException{
        if (capacity - item.getSpace() < 0){
            throw new InventoryFullException("Inventory full");
        }else{
            items.add(item);
            capacity -= item.getSpace();
            System.out.println("You picked up " + item.getName());
        }
    }

    /**
     * Removes an item from the inventory
     * @param itemToRemove the item to remove
     */

    public void removeItem(T itemToRemove){
        items.remove(itemToRemove);
    }

    /**
     * Finds an item with the given name in the inventory
     * @param name name of the item
     * @return the item with the given name, null if it doesn't exist
     */

    public T findItem(String name){
        for (T item : items){
            if (item.getName().toLowerCase().equals(name.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if the item is in the inventory
     * @param item the item to check for
     * @return true if the item is in the inventory, else false
     */
    public boolean contains(T item){
        return items.contains(item);
    }

    /**
     * Uses the key and removes it
     * @return true if the key is used, else false
     */
    
    public boolean useKey(){
        for (T item : items){
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
            for (T item : items) {
                result = result.concat(item + "\n");
            }
        }else{
            return "Inventory is empty";
        }
        return result;
    }
}
