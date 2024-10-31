/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.mariopoorty.minigames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Brian
 */
public class ReadFiles {

    // Method to read words from a text file
    public static void readWords(String filePath,ArrayList<String> wordsList) {
 
        
       try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            ReadFiles.class.getResourceAsStream("/poo/mariopoorty/files/" + filePath)))
            ) {
            String line;
            // Leer líneas del archivo
            while ((line = reader.readLine()) != null) {
                wordsList.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}