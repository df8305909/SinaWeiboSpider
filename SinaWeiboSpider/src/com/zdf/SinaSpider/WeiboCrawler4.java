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













import com.pojo.Comment;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 利用WebCollector和获取的cookie爬取新浪微博并抽取数�?
 * 搜索用户名得到官网微博地址（1）
 */
public class WeiboCrawler4 extends BreadthCrawler {
	static int i=1;
static Set<Comment> set =new HashSet<Comment>();
	String cookie;

    public WeiboCrawler4() throws Exception {
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
    	BufferedWriter bw=null;
    	try {
    		bw=new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_1.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int pageNum = Integer.valueOf(page.meta("pageNum"));
        /*抽取微博*/
        Element weibos = page.select("table").first(); 
    	 String span =  weibos.select("a").first().attr("href");;
    	try {
    		span="https://weibo.cn"+span;
    		//WeiboCrawler5.start5(span);
			bw.write(span);
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
        	
     
        }
    
    public static void main(String[] args) throws Exception {
    	//BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\abcd.txt"));
      	 WeiboCrawler4 crawler = new WeiboCrawler4();
           crawler.setThreads(1);
           	crawler.addSeed(new CrawlDatum("https://weibo.cn/search/user?hideSearchFrame=&keyword=联想&page=1")
                       .meta("pageNum", 1 + ""));
           crawler.start(10000000);
      
          /* Iterator<Comment> it = set.iterator();
           while(it.hasNext()){
           	Comment next = it.next();
           	if(next.getComment()!=null||next.getComment()!=""){
           		bw.write(next.getComment());
           		bw.newLine();
           	}
           }
           
           bw.close();*/
    
    }
    public static void start4(String nick) throws Exception {
    	//BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\abcd.txt"));
   	 WeiboCrawler4 crawler = new WeiboCrawler4();
        crawler.setThreads(1);
        	crawler.addSeed(new CrawlDatum("https://weibo.cn/search/user?hideSearchFrame=&keyword="+nick+"&page=1")
                    .meta("pageNum", 1 + ""));
        crawler.start(10000000);
   
        /*Iterator<Comment> it = set.iterator();
        while(it.hasNext()){
        	Comment next = it.next();
        	if(next.getComment()!=null||next.getComment()!=""){
        		bw.write(next.getComment());
        		bw.newLine();
        	}
        }
        
        bw.close();*/
   }

    

}