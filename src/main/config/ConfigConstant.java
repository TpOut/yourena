package main.config;

/**
 * Created by shengjieli on 18-10-15.<br>
 * Email address: 416756910@qq.com<br>
 */
public class ConfigConstant {

    //本机调试 : 服务器部署
    private static final boolean isDeployed = false;

    public static final String WEB_SITE = isDeployed ? "127.0.0.1:8080" : "localhost:8080";
    public static final String DATABASE_URL = isDeployed ? "127.0.0.1" : "120.55.171.184";

    public static final String PROJECT_NAME = "yourena";
    public static final String NOTES_NAME = "notes";
    public static final String ARTICLE_NAME = "article";
    public static final String ESSAY_NAME = "essay";

}
