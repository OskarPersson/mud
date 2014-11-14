package com.ioopm;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    World world = new World("res/world.txt", "res/books.txt", "res/courses.txt", "res/questions.txt");
    Room room = world.findRoom("Corridor 2");
    Course course = world.findCourse("IOOPM");
    Book book = new Book("Funktionellt, bitte!", "Tjark Weber", 1992);
    Teacher teacher = new Teacher("Tobias Wrigstad", course);
    ArrayList<Course> courses = new ArrayList<>();
    Player player = new Player("Oskar", courses, room);

    @Test
    public void testGo() throws Exception {
        player.go("north");
        assertEquals(player.getRoom(), world.findRoom("Room 5"));
    }

    @Test
    public void testUnlock() throws Exception {
        player.unlock("south");
        assertEquals(player.getRoom().getDoor("south").isLocked(), true);

    }

    @Test
    public void testPickup() throws Exception {
        player.getRoom().addItem(book);
        player.pickup("Funktionellt, bitte!");
        assertEquals(player.getInventory().findItem("Funktionellt, bitte!") != null &&
                player.getRoom().findItem("Funktionellt, bitte!") == null, true);
        player.drop("Funktionellt, bitte!");
        player.getRoom().removeItem(book);
    }

    @Test
    public void testDrop() throws Exception {
        player.getRoom().addItem(book);
        player.pickup("Funktionellt, bitte!");
        player.drop("Funktionellt, bitte!");

        assertEquals(player.getInventory().findItem("Funktionellt, bitte!") == null &&
                player.getRoom().findItem("Funktionellt, bitte!") != null, true);
        player.getRoom().removeItem(book);
    }

    @Test
    public void testEnroll() throws Exception {
        room.addTeacher(teacher);
        player.enroll(course);
        assertEquals(player.getUnfinishedCourses().contains(course), true);
    }
}