/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zdf.SinaSpider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

/**
 * 利用WebCollector和获取的cookie爬取新浪微博并抽取数据
 * 抽取用户的微博数等信息
 * 
 */
public class WeiboCrawler2 extends BreadthCrawler {

    String cookie;

    public WeiboCrawler2(String crawlPath, boolean autoParse) throws Exception {
        super(crawlPath, autoParse);
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
    	String uid = page.meta("uid");
    	Elements zils = page.select("div.ut");
    	String url = "";
    	for(Element zil:zils){
    		String html = zil.html();
        	Pattern p1 = Pattern.compile("(?<=\").*?(?=\">资料<)");//这个会将引号也包括进来\"[^\"]*\"，尝试：(?<=\").*?(?=\")
        	Matcher m1 = p1.matcher(html);
        	if(m1.find())url = m1.group();
        	System.out.println(url);
        	//SinaDAO.insertdata_url(url,uid);
    	}
        Elements weibos = page.select("div.tip2");
        for (Element weibo : weibos) {
        	String str =  weibo.text();
        	System.out.println(str);
            Pattern p = Pattern.compile("(?<=\\[)(.+?)(?=\\])");
        	Matcher m = p.matcher(str);
        	int[] num = new int[4];
        	int i=0;
        	while(m.find()){
        		num[i] = Integer.valueOf(m.group());
        		i++;
        	}
        	System.out.println(num[0]+"--"+num[1]+"--"+num[2]+"--"+num[3]);
        	//SinaDAO.insertWeibo(num,uid);
        }
    }

    public static void main(String[] args) throws Exception {
        WeiboCrawler2 crawler = new WeiboCrawler2("weibo_crawler", false);
        crawler.setThreads(1);
        /*Set<Comment> list = SinaDAO.getUrl();
        for (int i = 0;i<list.size();i++) {
        	Comment c = list.get(i);
        	crawler.addSeed(new CrawlDatum(c.getUser_url())
                    .meta("uid", c.getUid()));
        }*/       
        crawler.start(1);
    }

}