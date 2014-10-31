package com.ioopm;

public class Teacher extends Person {
    private Course course;

    public Teacher(String name){
        super(name);
    }

    public void setCourse(Course course){
        this.course = course;
    }
    public Course getCourse(){
        return course;
    }

    public String toString(){
        return "Name: " + super.toString() + "\nCourse: " + ((course == null) ? null : course.getName()) + "\n";
    }

}
