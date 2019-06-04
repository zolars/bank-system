package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Init
 */
public class Init {

    private static final String filePath = Main.filePath;

    public static void main(String[] args) {
        String fileName = "accounts.txt";
        File fp = new File(filePath);
        File f = new File(filePath + fileName);
        try {
            if (!fp.exists()) {
                fp.mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();

                if (fileName.equals("accounts.txt")) {
                    BufferedWriter writter = new BufferedWriter(
                            new FileWriter(filePath + fileName));
                    String str = "";
                    writter.write(str.toString());
                    writter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
