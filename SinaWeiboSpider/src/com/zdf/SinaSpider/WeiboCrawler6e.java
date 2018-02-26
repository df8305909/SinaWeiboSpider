/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zdf.SinaSpider;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;












import test.DBUtil;

import com.pojo.Comment;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 利用WebCollector和获取的cookie爬取新浪微博并抽取数�?
 * 获取用户评论以及主页地址和uid
 */
public class WeiboCrawler6e extends BreadthCrawler {
	static int i=1;
static Set<Comment> set =new HashSet<Comment>();
	String cookie;
public  static BufferedWriter bw = null;
    public WeiboCrawler6e() throws Exception {
        super("weibo_crawler", false);
        /*获取新浪微博的cookie，账号密码以明文形式传输，请使用小号*/
        //补全
        cookie="";
     
    }

    @Override
    public Page getResponse(CrawlDatum crawlDatum) throws Exception {
		HttpRequest request = new HttpRequest(crawlDatum);
		request.setCookie(cookie);
		return request.responsePage();
	}
    
    public void visit(Page page, CrawlDatums next) {
    	
    	
        int pageNum = Integer.valueOf(page.meta("pageNum"));
        /*抽取微博*/
        Elements weibos = page.select("div.c"); 
        
     for (Element weibo : weibos) {
    	 Elements span =  weibo.select("span.ctt");
        	String str = span.text();
        	String comment = Tool.filterEmoji(str);
        	Elements a = weibo.select("a.nk");
        	String href = a.attr("href");
        	String uid = a.text();
        	
        	Util.saveToSql(new Comment(uid, comment, href, "", "联想"));
        	Util.DelNull();
        	if(comment!=null||comment!=""){
        		try {
					bw.write(comment);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        	}
        	
        	}
     
        }
    
    public static void main(String[] args) throws Exception {
    	BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\test.txt"));
    	 WeiboCrawler crawler = new WeiboCrawler();
         crawler.setThreads(1);
         
         for (int i =1; i <=3; i++) {
         	crawler.addSeed(new CrawlDatum("https://weibo.cn/search/mblog?hideSearchFrame=&keyword=联想电脑&page=" + i)
                     .meta("pageNum", i + ""));
         }
         crawler.start(10000000);
       Set<Comment> set2 = Util.removeDuplicate(set);
		Iterator<Comment> it = set2.iterator();
		while(it.hasNext()){
			Comment next = it.next();
			System.out.println(next.getComment());
			/*Util.saveToSql(next);//存入数据库
			
			bw.write(next.getComment());
			bw.newLine();*/
			
		}
		bw.close();
    }
public static void sta(String url,int be,int end) throws Exception{
	bw = new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\comment.txt",true));
	WeiboCrawler6e crawler = new WeiboCrawler6e();
    crawler.setThreads(2);
   
  for(int i=be;i<=end;i++){
	  crawler.addSeed(new CrawlDatum(url+"&page="+i)
  
                .meta("pageNum", i + ""));
  }
   
    crawler.start(10000000);
    bw.close();
}

}
    

