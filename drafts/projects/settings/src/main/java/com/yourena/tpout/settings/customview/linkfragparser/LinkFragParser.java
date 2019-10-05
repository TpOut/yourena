package com.yourena.tpout.settings.customview.linkfragparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by shengjieli on 17-10-30.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class LinkFragParser {

    public static void main(String args[]) {
        getAllLinkFragName();
    }

    //发现很多嵌套的子页面，所以为了以防万一，写一个提取工具类
    //结果直接复制到proguard-app就可以了
    private static void getAllLinkFragName() {
        //
        String targetDirPath = System.getProperty("user.dir") + "/settings/src/main/res/layout";
        File file = new File(targetDirPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().startsWith("frag");
                }
            });
            int length = files.length;
            for (int i = 0; i < length; i++) {
                parseFromFile(files[i].getPath());
            }
        }

    }

    private static void parseFromFile(String filePath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            //锟斤拷锟斤拷SAXParserHandler锟斤拷锟斤拷
            SAXParserHandler handler = new SAXParserHandler();
            parser.parse(filePath, handler);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static class SAXParserHandler extends DefaultHandler {
        /**
         * 解析xml元素
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            //调用DefaultHandler类的startElement方法
            super.startElement(uri, localName, qName, attributes);
            if (qName.equals("com.yourena.tpout.settings.customview.SingleNormalView")) {
                System.out.print("-keep class " + attributes.getValue("app:nv_linkFrag") + " { *; }");
                System.out.println("\r");
            }
        }
    }

}


