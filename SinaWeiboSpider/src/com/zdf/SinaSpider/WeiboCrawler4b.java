/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zdf.SinaSpider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import com.pojo.Comment;

/**
 * 利用WebCollector和获取的cookie爬取新浪微博并抽取数�?
 * 搜索用户名得到官网微博地址（1）
 */
public class WeiboCrawler4b extends BreadthCrawler {
	static String sss=null;
	static int i=1;
static Set<Comment> set =new HashSet<Comment>();
	String cookie;

    public WeiboCrawler4b() throws Exception {
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
      
        BufferedWriter bw = null;
		try {
			bw=new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_2.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  String weibos = page.select("div.pa").select("div").text();
    	
    				System.out.println(getNumber(weibos));
    		try {
				bw.write(sss+" "+getNumber(weibos));
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	
    	        	
    
        }
    @SuppressWarnings("unused")
	private static int getNumber(String st){
    	int of = Integer.valueOf(st.substring(st.lastIndexOf("/")+1,st.lastIndexOf("页")));
    	
    	
		return of;
    	
    	
         
    }
    public static void main(String[] args) throws Exception {
    	 WeiboCrawler4b crawler = new WeiboCrawler4b();
         crawler.setThreads(1);
         	crawler.addSeed(new CrawlDatum("https://weibo.cn/lenovo?page="+1)
                     .meta("pageNum", 1 + ""));
         crawler.start(10000000);
    
    }
    
    public static void start4b() throws Exception {
    	BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\url_1.txt"));
    	
    	while((sss=br.readLine())!=null){
    		
    		 WeiboCrawler4b crawler = new WeiboCrawler4b();
    	        crawler.setThreads(1);
    	        
    	        	crawler.addSeed(new CrawlDatum(sss+"&page="+1)
    	                    .meta("pageNum", 1 + ""));
    	        
    	        crawler.start(10000000);
    		
    	}
   	
		br.close();
   
   }

    

}