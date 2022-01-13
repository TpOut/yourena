package main.config;

import java.io.File;

/**
 * Created by shengjieli on 18-10-15.<br>
 * Email address: 416756910@qq.com<br>
 */
public class ConfigConstant {

    //本机调试 : 服务器部署
    public static final boolean isDeployed = true;

    public static final String WEB_SITE = isDeployed ? "192.168.0.101:8080" : "localhost:8080";
    public static final String DATABASE_URL = "127.0.0.1";

    public static final String PROJECT_NAME = "yourena";
    public static final String DOCS_NAME = "MyDocs";
    public static final String DOC_FOOD_NAME = "my-story" + File.separator + "cook";
    public static final String DOC_FEEL_NAME = "my-story" + File.separator + "feels";
    public static final String DOC_STORY_NAME = "my-story" + File.separator + "anecdote";
    public static final String DOC_CS_BOOK_NAME = "cs" + File.separator + "book";
    public static final String DOC_CS_LANGUAGE_NAME = "cs" + File.separator + "language";
    public static final String DOC_BLOG_NAME = "blog-category";
    public static final String DOC_NOVEL_NAME = "my-story" + File.separator + "刀绅纪";

}
