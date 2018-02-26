/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zdf.SinaSpider;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pojo.Comment;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 利用WebCollector和获取的cookie爬取新浪微博并抽取数�?
 * 搜索用户名得到官网微博地址（1）
 */
public class WeiboCrawler5c extends BreadthCrawler /*implements Runnable*/ {
	   static BufferedWriter bw = null;
	static int i=1;
static Set<Comment> set =new HashSet<Comment>();
	String cookie;
static String ss;
static int p_num=1;

    public WeiboCrawler5c() throws Exception {
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
     
		
    	for(Element weibo:weibos){
    		
    		 String com_num =  weibo.select("a.cc").text();
    		 if(com_num.contains("[")||com_num.contains("]")){ 
    			 String url =  weibo.select("a.cc").attr("href");;
    			// System.out.println(getNumber(com_num)+"==="+url);//页数
    			 
    				 if(getNumber(com_num)>=1) {
    					 if(url.contains("rl=0#cmtfrm")) url=url.replaceAll("rl=0#cmtfrm", "");
    					 String url_spider=url+" "+getNumber(com_num);
    					// System.out.println(url_spider);
							
							try {
								
								bw.write(url_spider);
								bw.newLine();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    		 }
    		
    		 }
    	}
    	        	

        }
    @SuppressWarnings("unused")
	private static int getNumber(String st){
    	int a=1;
    	int of = Integer.valueOf(st.substring(st.lastIndexOf("[")+1,st.lastIndexOf("]")));
    	
    	if(0<of&of<=10) a=1;
    	if(of==0) a=0;
    	if(of>10) a=(of/10)+1;
		return a;
    	
    	
         
    }
    public static void main(String[] args) throws Exception {
    	BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_2.txt"));
    	bw = new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_3.txt"));
    	while((ss=br.readLine())!=null){
    		String[] split = ss.split(" ");
    		;
    		WeiboCrawler5c crawler = new WeiboCrawler5c();
            crawler.setThreads(2);
           int end=200;
           int be=0;
            
            
            for(int i=be;i<end;i++){
            	p_num+=1;
           System.out.println(p_num);
            	crawler.addSeed(new CrawlDatum(split[0]+"&page="+i)
                        .meta("pageNum", i + ""));
            	
            	
            }
            crawler.start(10000000);
           
           
            
    		
    	}
    	
    	br.close();
   	 bw.close();
   
    
    }
    
    public static void start5() throws Exception {
    	BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_2.txt"));
    	bw = new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_3.txt"));
    	while((ss=br.readLine())!=null){
    		String[] split = ss.split(" ");
    		;
    		WeiboCrawler5c crawler = new WeiboCrawler5c();
            crawler.setThreads(2);
           
            for(int i=1;i<Integer.parseInt(split[1]);i++){
            	p_num++;
            	crawler.addSeed(new CrawlDatum(split[0]+"&page="+i)
                        .meta("pageNum", i + ""));
            	
            }
            crawler.start(10000000);
            
    		
    	}
    	
    	br.close();
   	 bw.close();
   
   }

	/*@Override
	public void run() {
		try {
			BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_2.txt"));
			bw = new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_3.txt"));
			while((ss=br.readLine())!=null){
				String[] split = ss.split(" ");
				WeiboCrawler5c crawler = new WeiboCrawler5c();
			    crawler.setThreads(2);
			   
			    for(int i=1;i<Integer.parseInt(split[1]);i++){
			    	p_num++;
			    	crawler.addSeed(new CrawlDatum(split[0]+"&page="+i)
			                .meta("pageNum", i + ""));
			    	
			    }
			    crawler.start(10000000);
			    
				
			}
			
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

    public static void  sta(String url,int be,int end) throws Exception{
    	bw = new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\com.txt",true));
    	WeiboCrawler5c crawler = new WeiboCrawler5c();
        crawler.setThreads(2);
       
      for(int i=be;i<=end;i++){
    	  crawler.addSeed(new CrawlDatum(url+"&page="+i)
      
                    .meta("pageNum", i + ""));
      }
       
        crawler.start(10000000);
        bw.close();
    }

}