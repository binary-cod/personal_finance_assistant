package repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class FileRepo {

    String fileName;

    public FileRepo(){
        this.fileName = "/home/wild/projects/binarycod/fin_app_cl/per_fin_assistant.txt";
    }

    public void printDataFromFile(){

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (NoSuchFileException ex ){
          System.out.println("I can not find the file : " + fileName
                  + " please contact administrator! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
