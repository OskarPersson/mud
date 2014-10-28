package com.ioopm;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class World {
    private ArrayList<Room> rooms;

    public World(String filename){

        rooms = new ArrayList<Room>();

        BufferedReader buffer;
        String line;

        try (InputStream inputStream = new FileInputStream(filename)){

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
            for (Room room : rooms){
                Room northRoom  = findRoom(roomStrings.get(i)[1]);
                Room eastRoom   = findRoom(roomStrings.get(i)[2]);
                Room southRoom  = findRoom(roomStrings.get(i)[3]);
                Room westRoom   = findRoom(roomStrings.get(i)[4]);

                Door north = northRoom == null ? null : new Door(northRoom, Boolean.valueOf(roomStrings.get(i)[5]));
                Door east = eastRoom == null ? null :new Door(eastRoom, Boolean.valueOf(roomStrings.get(i)[6]));
                Door south = southRoom == null ? null :new Door(southRoom, Boolean.valueOf(roomStrings.get(i)[7]));
                Door west = westRoom == null ? null :new Door(westRoom, Boolean.valueOf(roomStrings.get(i)[8]));

                room.setDoors(north, east, south, west);
                i++;
            }

        } catch (FileNotFoundException e){
            System.out.println("File not found!");
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

    public String toString(){
        String result = "";
        for (Room room : rooms) {
            result += room + "\n-----------\n";
        }
        return result;
    }
}
