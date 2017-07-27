package com.zhou.meituan;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/27.
 */
public class HttpServers {

    private static final String  url = "https://tech.meituan.com/";

    private static final String cookie = "__utma=230384537.654362729.1501120816.1501141237.1501146609.3; __utmb=230384537.8.10.1501146609; __utmc=230384537; __utmz=230384537.1501120816.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";

    private static final String user_Agent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";



    public static String getHtml() throws Exception{

        OkHttpClient client = new OkHttpClient();

        String html = "";

        Request request = new Request.Builder().url(url).addHeader("Cookie",cookie).addHeader("User-Agent",user_Agent).build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()){
            html=response.body().string().replace("\\", "");

            Document doc=Jsoup.parse(html);

            System.out.println(doc);

            Elements scripts = doc.select("post post-with-tags");//获取post-list 标签内容
            System.out.println(scripts.toString());
//            Elements scripts = doc.getElementsByTag("div"); //获取script标签
            //Element script=scripts.get(scripts.size()-1);  // 获取包含了网页内容的script标签

            //System.out.println(script.html());
            Pattern p=Pattern.compile("\"html\":\"");    //从该json数据格式中抽取出html内容

            String htmlstr="";
            for(Element script:scripts){
                Matcher m=p.matcher(html=script.html());
                if(m.find()){
                    String str=html.substring(m.end(),html.length()-3);
                    htmlstr+=str;
                }
            }
            html=Jsoup.parse(htmlstr).html();
        }else {
            System.out.println("Network is error");
        }
        return html;
    }

    public static void main(String[] args)throws Exception{
        String s = getHtml();
        System.out.println(s);
    }
}
