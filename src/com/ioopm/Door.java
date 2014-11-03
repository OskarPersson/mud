package com.ioopm;

import java.util.ArrayList;

public class Door {
    private boolean locked;
    private ArrayList<Room> rooms;

    public Door(boolean locked){
        this.locked = locked;
        rooms = new ArrayList<>();
    }

    public boolean getLocked(){
        return this.locked;
    }

    public void addRoom(Room room){
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public Room otherRoom(Room room){
        return rooms.get(0) == room ? rooms.get(1) : rooms.get(0);
    }

    public void unlock(){
        this.locked = false;
    }
}
