package com.ioopm;

import java.util.ArrayList;

public class Inventory {
    private int capacity;
    private ArrayList<Item> items;

    public Inventory(){
        items = new ArrayList<Item>();
        capacity = 10;
    }

    public void addItem(Item item){
        if (capacity - item.getSpace() >= 0) {
            items.add(item);
            capacity -= item.getSpace();
        }else{
            System.out.println("Inventory full");
        }
    }

    public void removeItem(Item item){
        if (items.contains(item)) {
            items.remove(item);
            capacity -= item.getSpace();
        }
    }
    
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

    public String toString(){
        String result = "\n";
        if (items.size() != 0) {
            for (Item item : items) {
                result = result.concat(item.toString() + "\n");
            }
        }else{
            return "Inventory is empty";
        }
        return result;
    }
}
