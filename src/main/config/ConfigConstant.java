package main.config;

/**
 * Created by shengjieli on 18-10-15.<br>
 * Email address: 416756910@qq.com<br>
 */
public class ConfigConstant {

    //本机调试 : 服务器部署
    public static final boolean isDeployed = false;

    public static final String WEB_SITE     = isDeployed ? "127.0.0.1:8080" : "localhost:8080";
    public static final String DATABASE_URL = "127.0.0.1";

    public static final String PROJECT_NAME   = "yourena";
    public static final String DOCS_NAME      = "MyDocs";
    public static final String DOC_FOOD_NAME  = "food-book";
    public static final String DOC_FEEL_NAME  = "feel-book";
    public static final String DOC_STORY_NAME = "story-book";
    public static final String DOC_CS_BOOK_NAME = "cs-book";
    public static final String DOC_CS_LANGUAGE_NAME = "language";
    public static final String DOC_BLOG_NAME  = "blog-category";
    public static final String DOC_NOVEL_NAME  = "刀绅纪";

}
