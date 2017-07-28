package com.zhou.meituan;


import com.google.common.collect.Lists;
import com.zhou.json.GsonUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by Administrator on 2017/7/27.
 * 爬取美团技术点评文章
 */
public class HttpServers {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServers.class);

    private static final String  url = "https://tech.meituan.com/";

    private static final String cookie = "__utma=230384537.654362729.1501120816.1501141237.1501146609.3; __utmb=230384537.8.10.1501146609; __utmc=230384537; __utmz=230384537.1501120816.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";

    private static final String user_Agent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";



    public static List<Content> getHtml() throws Exception{

        OkHttpClient client = new OkHttpClient();

        String html = "";

        Request request = new Request.Builder().url(url).addHeader("Cookie",cookie).addHeader("User-Agent",user_Agent).build();

        Response response = client.newCall(request).execute();
        List<Content> contentList = Lists.newArrayList();

        if (response.isSuccessful()){
            html=response.body().string().replace("\\", "");

            Document doc=Jsoup.parse(html);

            //抓取文字标题和连接
            Elements script1 = doc.select("header").select("a");
            //抓取作者
            Elements scriptAuthor = doc.getElementsByClass("post-meta-author");
            //抓起发布日期
            Elements scriptDate = doc.getElementsByClass("post-meta-ctime");
            //抓取发布简介
            Elements scriptInto = doc.getElementsByClass("post-abstract");
            //抓取标签
            Elements scriptTag = doc.getElementsByClass("post-tags");

            script1.stream().forEach(element -> {
                Content content = new Content();
                content.setTitle(element.html());
                String elementString = element.toString();
                char c = '"';
                String[] s = elementString.split(String.valueOf(c));
                content.setHref("https://tech.meituan.com/"+s[1]);
                contentList.add(content);
            });

            int i = 0;
            for (Element element:scriptAuthor){
                Content content = contentList.get(i);
                content.setAuthor(element.html());
                i++;
            }
            int j = 0;
            for (Element element:scriptDate){
                Content content = contentList.get(j);
                content.setPostTime(element.html());
                j++;
            }
            int n = 0;
            for (Element element:scriptInto){
                Content content = contentList.get(n);
                content.setIntroduction(element.html());
                n++;
            }

            int z = 0;
            for (Element element:scriptTag){
                List<Tag> tagList = Lists.newArrayList();

                Elements elements = element.select("a");
                for (Element element1:elements){
                    Tag tag = new Tag();
                    tag.setTag(element1.html());
                    tagList.add(tag);
                }
                Content content = contentList.get(z);
                content.setTag(tagList);
                z++;
            }



            return contentList;
        }else {
            LOGGER.error("content:"+contentList.toString());
        }
        return contentList;
    }

    public static void main(String[] args)throws Exception{
        List<Content> s = getHtml();

        System.out.println(GsonUtils.objectToJson(s.get(1)));
//        System.out.println(s);
    }
}
