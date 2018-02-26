package com.zdf.SinaSpider;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

/**
 * 爬去用户的微博信息
 */
public class WeiboCrawler3 extends BreadthCrawler {

	String cookie;
	
	static String file_url = "H:\\研究生\\1-毕设\\15-第二点数据\\爬虫数据_微博\\";
	static BufferedReader br = null;
	//static BufferedWriter bw = null;
	static HashMap<String,String> map = null;

	public WeiboCrawler3(String crawlPath, boolean autoParse) throws Exception {
		super(crawlPath, autoParse);
		/* 获取新浪微博的cookie，账号密码以明文形 式传输，请使用小号 */
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
		System.out.println("--------------------------");
		String uid = page.meta("uid");
		/* 抽取微博 */
		Elements weibos = page.select("div.c");
		for (Element weibo : weibos) {
			String str = weibo.text();
			System.out.println(str);
			if(str.equals("设置:皮肤.图片.条数.隐私")||str.equals("彩版|触屏|语音")){System.out.println("yes");continue;}
			String[] strs = str.split(" ");
			for(int i=0;i<strs.length;i++)System.out.println(i+"-->"+strs[i]);
			int len = strs.length - 1;
			System.out.println(len);
			System.out.println(strs[len - 5]);
			WeiboBean wb = new WeiboBean();
			if (strs[0].equals("转发了"))
				wb.setIsre(1);
			else
				wb.setIsre(0);
			wb.setDevice(strs[len]);
			wb.setDate(strs[len - 1]);
			/*Pattern p = Pattern.compile("(?<=\\[)(.+?)(?=\\])");
			Matcher m1 = p.matcher(strs[len - 5]);*/
			wb.setLove(Integer.valueOf(strs[len-5].substring(2, strs[len-5].length()-1)));
			//Matcher m2 = p.matcher(strs[len - 4]);
			wb.setRetwee(Integer.valueOf(strs[len-4].substring(3, strs[len-4].length()-1)));
			//Matcher m3 = p.matcher(strs[len - 3]);
			wb.setComment(Integer.valueOf(strs[len-3].substring(3, strs[len-3].length()-1)));
			wb.setContext(Tool.filterEmoji(str));
			wb.setUid(uid);
		//	SinaDAO.insertintoWeibo(wb);
		}
	}
	
	public static void init(){
		try {
			br = new BufferedReader(new FileReader(file_url+"all_url\\user_url_cn.txt"));
			//bw = new BufferedWriter(new FileWriter(file_url+"weibo.txt"));
			map = new HashMap<String,String>();
			String line = null;
			while((line=br.readLine())!=null){
				String[] urls = line.split("-->");
				String myurl = null;
				if(urls.length>1)myurl = urls[1];
				if(myurl==null||"".equals(myurl)||" ".equals(myurl)){
					myurl = "http://weibo.cn/u/"+urls[0];
				}
				map.put(urls[0], myurl);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		WeiboCrawler3 crawler = new WeiboCrawler3("weibo_crawler", false);
		crawler.setThreads(1);
		/*Set<Comment> list = SinaDAO.getUrl();
		 //对某人微博第一页进行爬取 
		for (int i = 0; i < list.size(); i++) {
			Comment c = list.get(i);
			crawler.addSeed(new CrawlDatum(c.getUser_url()).meta("uid", c.getUid()));
		}*/
		//crawler.addSeed("http://weibo.cn/u/5533011024");
		/* 对某人微博前5页进行爬取 */
		for(String key:map.keySet()){
			String myurl = map.get(key);
			for (int i = 1; i <= 5; i++) {
				crawler.addSeed(new CrawlDatum(myurl+"?vt=4&page=" + i).meta("uid", key));
			}
		}
		crawler.start(1);
	}

}
