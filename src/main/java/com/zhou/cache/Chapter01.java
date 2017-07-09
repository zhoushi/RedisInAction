package com.zhou.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * Created by zhou on 2017/7/9.
 */
public class Chapter01 {

    private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final int VOTE_SCORE = 432;
    private static final int ARTICLES_PER_PAGE = 25;

    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1");


    }

    public void run(){
        Jedis conn = new Jedis("127.0.0.1");
        conn.select(15);

    }


    /**
     * 存放文章
     * @param conn
     * @param user
     * @param title
     * @param link
     * @return
     */
    private String postArticle(Jedis conn,String user,String title,String link){
        String articleId = String.valueOf(conn.incr("article:"));
        String voted = "voted:"+articleId;
        conn.sadd(voted,user);
        conn.expire(voted,ONE_WEEK_IN_SECONDS);

        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        conn.hmset(article, articleData);
        conn.zadd("score:", now + VOTE_SCORE, article);
        conn.zadd("time:", now, article);

        return articleId;
    }

    /**
     * 投票
     * @param conn
     * @param user
     * @param article
     */
    private void articleVote(Jedis conn,String user,String article){
        long cutoff = (System.currentTimeMillis()/1000)-ONE_WEEK_IN_SECONDS;
        if (conn.zscore("time:",article)<cutoff){
            return;
        }

        String articleId = article.substring(article.indexOf(':')+1);
        if (conn.sadd("voted"+articleId,user) == 1){
            conn.zincrby("score:", VOTE_SCORE, article);
            conn.hincrBy(article, "votes", 1l);
        }
    }
}
