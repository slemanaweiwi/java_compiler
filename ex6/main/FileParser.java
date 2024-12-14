package oop.ex6.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

    public final ArrayList<String> codeLines = new ArrayList<>();

    FileParser(BufferedReader fileReader) throws sJavaExceptions, IOException {

        String line;


        // clean white spaces and remove comments
        while ((line = fileReader.readLine()) != null) {


            if (!line.isEmpty() && !line.startsWith("//")) {


                codeLines.add(line.replaceAll("\\s+", " ").trim());
            }


        }

    }


}
