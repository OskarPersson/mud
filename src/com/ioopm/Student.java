package com.ioopm;

public class Student extends Person{
    private Course currentCourse;
    private Course finishedCourse;

    public Student(String name, Course currentCourse, Course finishedCourse){
        super(name);
        this.currentCourse = currentCourse;
        this.finishedCourse = finishedCourse;
    }

    public String toString(){
        return "\n" + super.toString() + "\nFinished Course: " + finishedCourse.getName() + "\nCurrent Course: " + currentCourse.getName();
    }
}
