package com.example.selfdic;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tpout on 17-8-27.
 */

public interface BaiduTransApi {

    @FormUrlEncoded
    @POST("/api/trans/vip/translate")
    Call<BaiduTransResultBean> queryResult(@Field("q") String queryStr,
                                           @Field("from") String srcLanguage,
                                           @Field("to") String targetLanguage,
                                           @Field("appid") String appid,
                                           @Field("salt") String salt,
                                           @Field("sign") String sign
    );
}
