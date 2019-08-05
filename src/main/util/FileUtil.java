package main.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private static String getAllFileName(File topDirectory) {
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
                String name = f.getName();
                int indexSuffix = name.indexOf("."); //后缀
                String[] splits = name.substring(0, indexSuffix).split("_");

                String tag = null;
                String time = null;
                String pureName = null;
                switch (splits.length) {
                    case 3:
                        time = splits[2];
                    case 2:
                        pureName = splits[1];
                        tag = splits[0];
                        break;
                    case 1:
                        pureName = splits[0];
                        break;
                }

                List<String> summary = null;
                try {
                    summary = readSeveralLines(f.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                sb.append("{");

                if (null != tag) {
                    sb.append("\"tag\":\"").append(tag).append("\"")
                            .append(",");
                }
                sb.append("\"name\":\"").append(pureName).append("\"")
                        .append(",")
                        .append("\"url\":\"").append(pathClip(f.getAbsolutePath())).append("\"")
                        .append(",")
                        .append("\"time\":\"").append(time == null ? getModifyTime(f) : time).append("\"");

                if (null != summary) {
                    sb.append(",")
                            .append("\"summary\":\"");
                    for (String sum : summary) {
                        sb.append(sum)
                                .append("\\\\n")
                        ;
                    }
                    sb.append("\"");
                }

                sb.append(",")
                        .append("\"type\":\"").append("normal").append("\"}");
            }
        }

        if (topDirectory.isDirectory()) {
            sb.append("]");
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * 参考Files.readLines
     *
     * @throws IOException
     */
    private static List<String> readSeveralLines(Path filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            List<String> result = new ArrayList<>();
            int lines = 5;
            for (int i = 0; i < lines; i++) {
                String line = reader.readLine();
                if (line == null)
                    break;
                result.add(line);
            }
            return result;
        }
    }

    private static String pathClip(String path) {
        if (null == path || path.length() == 0) {
            return "invalid path";
        }
        String[] split = path.split(Pattern.quote(File.separator));
        int length = split.length;
        StringBuilder result = new StringBuilder();
        for (int i = length - 1; i >= 0 && !split[i].equals(ConfigConstant.DOCS_NAME); i--) {
            result.insert(0, "/");
            result.insert(0, split[i]);
        }
        return result.insert(0, "/").insert(0, ConfigConstant.DOCS_NAME).substring(0, result.length() - 1);
    }

    private static String getModifyTime(File f) {
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }

}
