package com.ioopm;

public class Teacher extends Person {
    private Course course;

    /**
     * Creates a teacher with a name
     * @param name Name of the teacher
     */

    public Teacher(String name){
        super(name);
    }

    /**
     * Sets the teacher's course
     * @param course The new course
     */

    public void setCourse(Course course){
        this.course = course;
    }

    /**
     * Gets the teacher's course
     * @return the teacher's course
     */

    public Course getCourse(){
        return course;
    }

    /**
     * Gets the name and course (if any) of the teacher
     * @return a string representing the name and course of the teacher
     */

    public String toString(){
        return "Name: " + super.toString() + "\nCourse: " + ((course == null) ? " none" : course.getName()) + "\n";
    }

}
