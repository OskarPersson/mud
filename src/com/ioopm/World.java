package com.ioopm;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class World {
    private ArrayList<Room> rooms;
    private ArrayList<Teacher> teachers;
    private ArrayList<Book> books;
    private ArrayList<Key> keys;
    private ArrayList<Course> courses;

    public World(String roomFilename, String bookFilename, String courseFilename){

        rooms = new ArrayList<Room>();
        teachers = new ArrayList<Teacher>();
        books = new ArrayList<Book>();
        keys = new ArrayList<Key>();
        courses = new ArrayList<Course>();

        BufferedReader buffer;
        String line;
        Random ran = new Random();
        int ranIdx;

        //Create rooms
        try (InputStream inputStream = new FileInputStream(roomFilename)){

            buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            ArrayList<String[]> roomStrings = new ArrayList<String[]>();
            while ((line = buffer.readLine()) != null) {

                String[] explodedString = line.split("; ", 9);
                roomStrings.add(explodedString);
                Room room = new Room(explodedString[0]);
                rooms.add(room);
            }

            // Done with the file
            buffer.close();
            inputStream.close();

            int i = 0;
            int keyCounter = 0;
            for (Room room : rooms){
                Room northRoom  = findRoom(roomStrings.get(i)[1]);
                Room eastRoom   = findRoom(roomStrings.get(i)[2]);
                Room southRoom  = findRoom(roomStrings.get(i)[3]);
                Room westRoom   = findRoom(roomStrings.get(i)[4]);

                boolean northLocked = roomStrings.get(i)[5].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[5]);
                boolean eastLocked = roomStrings.get(i)[6].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[6]);
                boolean southLocked = roomStrings.get(i)[7].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[7]);
                boolean westLocked = roomStrings.get(i)[8].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[8]);

                Door north = northRoom == null ? null : new Door(northRoom, northLocked);
                Door east = eastRoom == null ? null :new Door(eastRoom, eastLocked);
                Door south = southRoom == null ? null :new Door(southRoom, southLocked);
                Door west = westRoom == null ? null :new Door(westRoom, westLocked);

                if (northLocked){
                    keyCounter++;
                }
                if (eastLocked){
                    keyCounter++;
                }
                if (southLocked){
                    keyCounter++;
                }
                if (westLocked){
                    keyCounter++;
                }

                room.setDoors(north, east, south, west);
                i++;
            }

            keyCounter = (int)Math.ceil(keyCounter*1.5);
            for (int j = 0; j <= keyCounter; j++) {
                rooms.get(ran.nextInt(rooms.size())).addKey(new Key());
            }


        } catch (FileNotFoundException e){
            System.out.println(roomFilename + " not found!");
        } catch (IOException e){
            e.printStackTrace();
        }

        //Create teachers
        Teacher teacherOne = new Teacher("Oskar");
        Teacher teacherTwo = new Teacher("Stackel");
        teachers.add(teacherOne);
        teachers.add(teacherTwo);

        for (Teacher teacher : teachers){
            ranIdx = ran.nextInt(rooms.size());
            rooms.get(ranIdx).addTeacher(teacher);
        }

        //Create books
        try (InputStream inputStream = new FileInputStream(bookFilename)){

            buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split(";", 4);
                Book book = new Book(   explodedString[0], explodedString[1],
                                        Integer.parseInt(explodedString[2]),
                                        Integer.parseInt(explodedString[3]));
                ranIdx = ran.nextInt(rooms.size());
                rooms.get(ranIdx).addBook(book);
                books.add(book);
            }

            // Done with the file
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(bookFilename + " not found!");
        } catch (IOException e){
            e.printStackTrace();
        }

        //Create courses
        try (InputStream inputStream = new FileInputStream(courseFilename)){

            buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split(";", 3);
                Book courseBook = findBook(explodedString[1]);
                if (courseBook != null) {
                    Course course = new Course(explodedString[0], courseBook,
                            Integer.parseInt(explodedString[2]));
                    ranIdx = ran.nextInt(teachers.size());
                    teachers.get(ranIdx).setCourse(course);
                    teachers.remove(ranIdx);
                    courses.add(course);
                }
            }

            // Done with the file
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(courseFilename + " not found!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Room findRoom(String name){
        for (Room room : rooms){
            if (room.getName().equals(name)){
                return room;
            }
        }
        return null;
    }

    public Book findBook(String name){
        for (Book book : books){
            if (book.getName().equals(name)){
                return book;
            }
        }
        return null;
    }

    public Room randRoom(){
        Random ran = new Random();
        return rooms.get(ran.nextInt(rooms.size()));
    }

    public String toString(){
        String result = "";
        for (Room room : rooms) {
            result += room + "\n-----------\n";
        }
        return result;
    }
}
