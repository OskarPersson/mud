package com.ioopm;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class World {
    private ArrayList<Room> rooms;
    private ArrayList<String> names;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Book> books;
    private ArrayList<Key> keys;
    private ArrayList<Course> courses;
    private Sphinx sphinx;
    private Random ran;

    public World(String roomFilename, String bookFilename, String courseFilename){
        rooms    = new ArrayList<Room>();
        names    = new ArrayList<String>();
        teachers = new ArrayList<Teacher>();
        students = new ArrayList<Student>();
        books    = new ArrayList<Book>();
        keys     = new ArrayList<Key>();
        courses  = new ArrayList<Course>();
        ran      = new Random();

        initRooms(roomFilename);
        initNames("res/names.txt");
        initTeachers(10, 14);
        initBooks(bookFilename);
        initCourses(courseFilename);
        initStudents(10, 14);
        initSphinx();

    }
    private void initSphinx(){
        randRoom().addSphinx(new Sphinx());
    }

    private void initCourses(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split(";", 3);
                Book courseBook = findBook(explodedString[1]);
                if (courseBook != null) {
                    Course course = new Course(explodedString[0], courseBook,
                                    Integer.parseInt(explodedString[2]));
                    int ranIdx = ran.nextInt(teachers.size());
                    teachers.get(ranIdx).setCourse(course);
                    teachers.remove(ranIdx);
                    courses.add(course);
                }
            }

            // Done with the file
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void initBooks(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split(";", 4);
                Book book = new Book(   explodedString[0], explodedString[1],
                                        Integer.parseInt(explodedString[2]),
                                        Integer.parseInt(explodedString[3]));
                randRoom().addItem(book);
                books.add(book);
            }

            // Done with the file
            buffer.close();
            inputStream.close();

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initNames(String filepath){
        try (InputStream inputStream = new FileInputStream("res/names.txt")){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                names.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.print(filepath + " not found!");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void initTeachers(int min, int max){
        int n_teachers = ran.nextInt(max - min) + min;

        while (teachers.size() <= n_teachers){
            int rand = ran.nextInt(names.size());
            teachers.add(new Teacher(names.remove(rand)));
        }

        for (Teacher teacher : teachers){
            randRoom().addTeacher(teacher);
        }

    }

    private void initStudents(int min, int max){
        int n_students = ran.nextInt(max - min) + min;

        while (students.size() <= n_students){
            int rand = ran.nextInt(names.size());
            int firstCourseId = ran.nextInt(courses.size());
            int secondCourseId = ran.nextInt(courses.size());
            while (secondCourseId == firstCourseId){
                secondCourseId = ran.nextInt(courses.size());
            }
            students.add(new Student(names.remove(rand), courses.get(firstCourseId), courses.get(secondCourseId)));
        }

        for (Student student : students){
            randRoom().addStudent(student);
        }

    }

    private void initRooms(String filepath){
        try (InputStream inputStream = new FileInputStream(filepath)){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            ArrayList<String[]> roomStrings = new ArrayList<String[]>();
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] explodedString = line.split("; ", 9);
                roomStrings.add(explodedString);
                Room room = new Room(explodedString[0]);
                rooms.add(room);
            }

            // Close file.
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
                boolean eastLocked  = roomStrings.get(i)[6].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[6]);
                boolean southLocked = roomStrings.get(i)[7].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[7]);
                boolean westLocked  = roomStrings.get(i)[8].equals("X") ? false : Boolean.valueOf(roomStrings.get(i)[8]);

                Door door;
                if (room.getDoor("north") == null) {
                    door = northRoom == null ? null : new Door(northLocked);
                    if (door != null){
                        room.setDoor(door, "north");
                        northRoom.setDoor(door, "south");
                        door.addRoom(room);
                        door.addRoom(northRoom);
                    }
                }
                if (room.getDoor("east") == null) {
                    door = eastRoom == null ? null : new Door(eastLocked);
                    if (door != null){
                        room.setDoor(door, "east");
                        eastRoom.setDoor(door, "west");
                        door.addRoom(room);
                        door.addRoom(eastRoom);
                    }
                }
                if (room.getDoor("south") == null) {
                    door = southRoom == null ? null : new Door(southLocked);
                    if (door != null){
                        room.setDoor(door, "south");
                        southRoom.setDoor(door, "north");
                        door.addRoom(room);
                        door.addRoom(southRoom);
                    }
                }
                if (room.getDoor("west") == null) {
                    door = westRoom == null ? null : new Door(westLocked);
                    if (door != null){
                        room.setDoor(door, "west");
                        westRoom.setDoor(door, "east");
                        door.addRoom(room);
                        door.addRoom(westRoom);
                    }
                }

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
                i++;
            }

            keyCounter = (int)Math.ceil(keyCounter*1.5);
            for (int j = 0; j < keyCounter; j++) {
                randRoom().addItem(new Key());
            }

        } catch (FileNotFoundException e){
            System.out.println(filepath + " not found!");
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
            if (book.getName().toLowerCase().equals(name.toLowerCase())){
                return book;
            }
        }
        return null;
    }

    public Course findCourse(String name){
        for (Course course : courses){
            if (course.getName().toLowerCase().equals(name.toLowerCase())){
                return course;
            }
        }
        return null;
    }

    public Room randRoom(){
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
