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
}
