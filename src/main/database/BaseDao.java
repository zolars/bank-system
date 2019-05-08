package database;

import application.Main;

import java.io.*;

/**
 * BaseDao
 * 
 * @return true for create successfully, false for the file is already existed
 *         or IOException throws.
 */
public class BaseDao {

    private static final String filePath = Main.filePath;

    private static synchronized boolean getConnection(String fileName) {
        File file = new File(filePath + fileName);
        return file.exists();
    }

    public static int fileAmount() {
        return new File(filePath).listFiles().length;
    }

    public static boolean addFile(String fileName, String text) throws IOException {
        if (!getConnection(fileName)) {
            File file = new File(filePath + fileName);
            file.createNewFile();
            BufferedWriter writter = new BufferedWriter(new FileWriter(filePath + fileName));
            writter.write(text);
            writter.close();
            return true;
        } else {
            System.out.println("Error: The file \"" + fileName
                    + "\" is already existed.\n\tat database.BaseDao.addFile(BaseDao.java:27)");
        }
        return false;
    }

    public static boolean addLine(String fileName, String text) throws IOException {
        if (getConnection(fileName)) {
            StringBuffer buf = new StringBuffer();
            if (getConnection(fileName)) {
                File file = new File(filePath + fileName);
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String lineString = new String();
                while ((lineString = reader.readLine()) != null) {
                    buf.append(lineString);
                    buf.append(System.getProperty("line.separator"));
                }
                buf.append(text);
                reader.close();

                BufferedWriter writter = new BufferedWriter(new FileWriter(filePath + fileName));
                writter.write(buf.toString());
                writter.close();
                return true;
            } else {
                System.out.println("Error: The file \"" + fileName
                        + "\" doesn't exist.\n\tat database.BaseDao.addLine(BaseDao.java:40)");
            }
        }
        return false;
    }

    public static boolean replace(String fileName, String text) throws IOException {
        StringBuffer buf = new StringBuffer();
        if (getConnection(fileName)) {
            File file = new File(filePath + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String lineString = new String();
            int lineCount = 0;
            while ((lineString = reader.readLine()) != null) {
                if (lineCount == 0) {
                    lineCount++;
                    buf.append(text);
                    buf.append(System.getProperty("line.separator"));
                } else {
                    buf.append(lineString);
                    buf.append(System.getProperty("line.separator"));
                }
            }
            reader.close();

            BufferedWriter writter = null;

            writter = new BufferedWriter(new FileWriter(filePath + fileName));
            writter.write(buf.toString());
            writter.close();
            return true;
        } else {
            System.out.println("Error: The file \"" + fileName
                    + "\" doesn't exist.\n\tat database.BaseDao.replace(BaseDao.java:70)");
        }
        return false;
    }

    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}