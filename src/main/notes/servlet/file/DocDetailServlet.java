package main.notes.servlet.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import jakarta.servlet.http.*;

import main.notes.util.ServletUtil;
import main.util.LogUtil;

import static main.config.ConfigConstant.*;

/**
 * Created by shengjieli on 18-10-15.<br>
 * Email address: 416756910@qq.com<br>
 *
 * 为了支持本地图片，docDetail 其实就是对需求的文件，进行流返回
 */
public class DocDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ServletUtil.setCharset(resp);

//        resp.setContentType("application/octet-stream");

        //将地址中，在yourena之后的部分截断，如 webPath = /Users/lishengjie/yourena/out/artifacts/yourena_war_exploded/
        String webPath = req.getSession().getServletContext().getRealPath("");
        String projectPath = webPath.substring(0, webPath.indexOf(PROJECT_NAME) + PROJECT_NAME.length());

        //获取实际请求的路经
        StringBuffer requestURL = req.getRequestURL();
        // 服务器的形式为：http://127.0.0.1:8080/MyDocs/.../***.md
        // 本地的地址: http://localhost:8080 或者127.；根据配置来
        String request_server = server_local;

        int i = requestURL.indexOf(request_server);
        String suffix;
        if(i == -1){
            suffix = requestURL.toString();
        }else {
            suffix = requestURL.substring(i + request_server.length(), requestURL.length());
        }

        //判断格式
        if(suffix.toLowerCase().endsWith(".html")){
            resp.setContentType("text/html; UTF-8");
        }else if(suffix.toLowerCase().endsWith(".md")){
            resp.setContentType("text/markdown; UTF-8");
        }else if(suffix.toLowerCase().endsWith(".jpg")
        ||suffix.toLowerCase().endsWith(".png")){
            resp.setContentType("image/*");
        }else {
            return;
        }
        //请求日志
        LogUtil.saveToFile(projectPath, suffix);

        if(!suffix.contains("MyDocs")){
            return; //防止任意读取系统文件
        }

        //输出文章
        File file = new File(projectPath + File.separator + URLDecoder.decode(suffix, "UTF-8"));
        if(!file.exists()){
            return;
        }
        OutputStream output = resp.getOutputStream();// 得到输出流

        InputStream is = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);// 输入缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
        byte data[] = new byte[4096];// 缓冲字节数
        int size = 0;
        size = bis.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = bis.read(data);
        }
        bis.close();
        bos.flush();// 清空输出缓冲流
        bos.close();

        output.close();

    }

}
