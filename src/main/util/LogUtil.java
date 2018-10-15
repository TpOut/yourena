package main.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by shengjieli on 18-10-10.<br>
 * Email address: 416756910@qq.com<br>
 */
public class LogUtil {

    public static void printL(String info) {
        System.out.println(info);
    }

    public static void saveToFile(String info) {
        File file = new File("temp.log");
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(file);
            fwriter.write(info);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
