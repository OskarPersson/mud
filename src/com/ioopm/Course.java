package com.ioopm;

public class Course {
    String name;
    private Book book;
    private int HP;
    private Question question;

    public Course(String name, Book book, int HP){
        this.name = name;
        this.book = book;
        this.HP = HP;
    }

    public void setQuestion(Question question){
        this.question = question;
    }

    public Question getQuestion(){
        return question;
    }

    public String getName(){
        return name;
    }

    public Book getBook(){
        return book;
    }

    public String toString(){
        return "Name: " + name + "\nBook: " + book + "\nHP: " + HP + "\n";
    }
}
