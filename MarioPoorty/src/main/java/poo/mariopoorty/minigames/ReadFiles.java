/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brian
 */
public class ReadFiles {
    // Method to read words from a text file
    public static void readWords() {
        // Path to the text file
        String globalPath ="C:\\Users\\Brian\\Documents\\MarioPoorty\\MarioPoorty\\src\\main\\java\\poo\\mariopoorty\\files\\";
        String filePath = globalPath+"words.txt";

        // List to store words read from the file
        List<String> wordsList = new ArrayList<>();
        FileReader file;
        BufferedReader reader;
        
        try {
            file = new FileReader(filePath);
            String line;
            
            if (file.ready()) {
               reader = new BufferedReader(file); 
               // Read lines from the file
               while ((line = reader.readLine()) != null) {
                   wordsList.add(line);
                }
               reader.close();
            }
            
            for (String string : wordsList) {
                System.out.println(string);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}