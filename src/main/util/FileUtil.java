package main.util;

import java.io.File;
import java.util.regex.Pattern;

import main.config.ConfigConstant;

/**
 * Created by shengjieli on 18-10-10.<br>
 * Email address: 416756910@qq.com<br>
 */
public class FileUtil {

    public static String getAllFileName(String topDirectory) {
        File file = new File(topDirectory);
        return getAllFileName(file);
    }

    /**
     * 将文件夹遍历，获得{name: , url: , sub: []} 的对象
     *
     * @param topDirectory 遍历起始文件夹
     * @return 起始文件夹下面的所有文件（包括文件夹）的名字和链接
     */
    public static String getAllFileName(File topDirectory) {
        if (null == topDirectory) {
            return "";
        }

        File[] files = topDirectory.listFiles();
        if (null == files) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{").append("\"name\":\"").append(topDirectory.getName()).append("\"")
                .append(",\"url\":\"").append(pathClip(topDirectory.getAbsolutePath())).append("\"")
                .append(",\"type\":\"").append("directory").append("\"");
        if (topDirectory.isDirectory()) {
            sb.append(",\"sub\":[");
        }

        int length = files.length;
        for (int i = 0; i < length; i++) {
            File f = files[i];

            if (i != 0) {
                sb.append(",");
            }

            if (f.isDirectory()) {
                sb.append(getAllFileName(f));
            } else {
                sb.append("{").append("\"name\":\"").append(f.getName()).append("\"")
                        .append(",\"url\":\"").append(pathClip(f.getAbsolutePath())).append("\"")
                        .append(",\"type\":\"").append("normal").append("\"}");
            }
        }

        if (topDirectory.isDirectory()) {
            sb.append("]");
        }

        sb.append("}");
        return sb.toString();
    }

    public static String pathClip(String path) {
        if (null == path || path.length() == 0) {
            return "invalid path";
        }
        String[] split = path.split(Pattern.quote(File.separator));
        int length = split.length;
        StringBuilder result = new StringBuilder();
        for (int i = length - 1; i >= 0 && !split[i].equals(ConfigConstant.NOTES_NAME); i--) {
            result.insert(0, "/");
            result.insert(0, split[i]);
        }
        return result.insert(0, "/").insert(0, ConfigConstant.NOTES_NAME).substring(0, result.length() - 1);
    }

}
