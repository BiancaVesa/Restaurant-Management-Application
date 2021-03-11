package dataLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWriter {

    public static void writeInFile(String fileOut, String text) throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileOut, "UTF-8");
        writer.print(text);
        writer.close();
    }
}
