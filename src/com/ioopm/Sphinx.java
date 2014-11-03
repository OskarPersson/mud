package com.ioopm;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Sphinx extends Person {
    private ArrayList<String> responses;
    public Sphinx(){
        super("The Sphinx");
        initResponses();
    }
    public void talk(){
        int idx = new Random().nextInt(responses.size());
        System.out.print(responses.get(idx));
    }

    private void initResponses(){
        responses = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream("res/sphinx.txt")){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                responses.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.print("res/sphinx.txt not found!");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
