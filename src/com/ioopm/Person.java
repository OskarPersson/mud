package com.ioopm;

public class Person {
    private String name;

    /**
     * A person with a name
     * @param name Name of the person
     */
    public Person(String name){
        this.name = name;
    }

    /**
     * Gets the name of the person
     * @return Name of the person
     */

    public String getName(){
        return name;
    }

    /**
     * Gets the name of the person
     * @return Name of the person
     */

    public String toString(){
        return name;
    }
}
