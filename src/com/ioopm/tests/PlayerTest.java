package com.ioopm.tests;

import com.ioopm.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    World world = new World("res/testWorld.txt", "res/names.txt", "res/books.txt", "res/courses.txt", "res/questions.txt");
    Room room = world.findRoom("Corridor 1");
    Course course = world.findCourse("IOOPM");
    Book book = new Book("Funktionellt, bitte!", "Tjark Weber", 1992);
    Teacher teacher = new Teacher("Tobias Wrigstad", course);
    ArrayList<Course> courses = new ArrayList<>();
    Player player = new Player("Oskar", courses, room);

    @Test
    public void testGo() throws Exception {

        player.go("north");
        System.out.println(player.getRoom());
        assertEquals(player.getRoom(), world.findRoom("Room 2"));
        player.go("south");
        assertEquals(player.getRoom(), room);

        player.go("east");
        assertEquals(player.getRoom(), world.findRoom("Corridor 2"));
        player.go("west");
        assertEquals(player.getRoom(), room);

        player.go("west");
        assertEquals(player.getRoom(), world.findRoom("Room 1"));
        player.go("east");
        assertEquals(player.getRoom(), room);

        player.go("south");
        assertEquals(player.getRoom(), world.findRoom("Room 3"));
        player.go("north");
        assertEquals(player.getRoom(), room);
    }

    @Test
    public void testUnlock() throws Exception {
        player.go("east");
        player.unlock("south");
        assertEquals(player.getRoom().getDoor("south").isLocked(), true);

        player.getInventory().addItem(new Key());
        player.unlock("south");
        assertEquals(player.getRoom().getDoor("south").isLocked(), false);
        player.go("south");
        assertEquals(player.getRoom(), world.findRoom("Room 6"));
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