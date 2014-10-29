package com.ioopm;

public class Door {
    private Room room;
    private boolean locked;

    public Door(Room room, boolean locked){
        this.room = room;
        this.locked = locked;
    }

    public Room getRoom(){
        return this.room;
    }
    public boolean getLocked(){
        return this.locked;
    }
}
