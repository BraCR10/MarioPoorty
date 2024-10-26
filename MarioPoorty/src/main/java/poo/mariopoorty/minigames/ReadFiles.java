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
    static String projectPath ="C:\\Users\\Brian\\Documents\\MarioPoorty\\MarioPoorty\\src\\main\\java\\poo\\mariopoorty\\files\\";

    // Method to read words from a text file
    public static void readWords(String filePath,ArrayList<String> wordsList) {
        // Path to the text file
        String path = projectPath+filePath;

        // List to store words read from the file
        FileReader file;
        BufferedReader reader;
        
        try {
            file = new FileReader(path);
            String line;
            
            if (file.ready()) {
               reader = new BufferedReader(file); 
               // Read lines from the file
               while ((line = reader.readLine()) != null) {
                   wordsList.add(line);
                }
               reader.close();
            }
            

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}