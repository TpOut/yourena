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

    public static void saveToFile(String pathPrefix, String info) {
        File file = new File(pathPrefix + File.separator + "temp.log");

        try (FileWriter fwriter = new FileWriter(file,true)){
            fwriter.write(info);
            fwriter.write(System.getProperty("line.separator"));

        }catch (IOException e){
            System.out.print(e.toString());
        }

    }

}
