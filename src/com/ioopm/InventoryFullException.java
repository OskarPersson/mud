package com.ioopm;

public class InventoryFullException extends Exception{

    public InventoryFullException(){
        super();
    }

    public InventoryFullException(String message){
        super(message);
    }
}
