package com.ioopm;

public class Sphinx extends Person {
    public Sphinx(){
        super("The Sphinx");
    }

    public void graduate(Player player){
        if (player. getHP() >= 180 && !player.hasUnfinishedCourses()){
            System.out.print("GRADUATE HEHE");
        }
    }

}
