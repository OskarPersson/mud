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
    private Random ran;

    public World(String roomFilename, String bookFilename, String courseFilename){
        rooms    = new ArrayList<Room>();
        teachers = new ArrayList<Teacher>();
        books    = new ArrayList<Book>();
        keys = new ArrayList<Key>();
        courses  = new ArrayList<Course>();
        ran      = new Random();

        initRooms(roomFilename);
        initTeachers("res/names.txt");
        initBooks(bookFilename);

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
                int ranIdx = ran.nextInt(rooms.size());
                rooms.get(ranIdx).addItem(book);
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

    private void initTeachers(String filepath){
        ArrayList<String> teacherNames = new ArrayList<String>();
        try (InputStream inputStream = new FileInputStream("res/names.txt")){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                teacherNames.add(line);
            }

        }catch(FileNotFoundException e){
            System.out.print("File not found!");

        }catch(IOException e){
            e.printStackTrace();
        }

        int n_teachers = 10;
        for(int i = 0; i <= n_teachers; i++){
            String randName = teacherNames.get(ran.nextInt(teacherNames.size()));
            teachers.add(new Teacher(randName));
        }

        for (Teacher teacher : teachers){
            int ranIdx = ran.nextInt(rooms.size());
            rooms.get(ranIdx).addTeacher(teacher);
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
            for (int j = 0; j < keyCounter; j++) {
                rooms.get(ran.nextInt(rooms.size())).addItem(new Key());
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
            if (book.getName().equals(name)){
                return book;
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
