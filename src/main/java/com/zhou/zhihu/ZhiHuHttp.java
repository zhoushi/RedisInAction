package com.zhou.zhihu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhou.json.GsonUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ZhiHuHttp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZhiHuHttp.class);

    /**
     * 返回抓取内容
     * @return
     */
    public static String getContent() throws Exception{
        OkHttpClient client = new OkHttpClient();
        String contentString = "";

        Request request = new Request.Builder().url(Configuration.url).addHeader("Cookie",Configuration.cookie)
                .addHeader("User-Agent",Configuration.user_Agent).build();

        Response response = client.newCall(request).execute();


        if (response.isSuccessful()){
            contentString = response.body().string().replace("\\","");
            Document doc = Jsoup.parse(contentString);

            Element element = doc.getElementById("preloadedState");
            String[] s = element.toString().split(">");
            String[] s1 = s[1].split("<");

            System.out.println("element:"+s1[0]);
            return s1[0];
        }else {
            LOGGER.error("抓取失败");
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String s = getContent();

        Map<String,String> map = (Map<String, String>) JSONObject.parse(s);
        LOGGER.error(map.toString());
        String s1 = map.get("columnPosts");

        System.out.println(s1);
    }
}
