package com.yourena.tpout.settings.processing;

import static com.yourena.tpout.settings.processing.Functional.state_off;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class ProcessingBean {

    public int state = state_off;

    public static ProcessingBean sProcessingBean = new ProcessingBean();

}
