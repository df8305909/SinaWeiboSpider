package com.zdf.SinaSpider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Start {
	public static void main(String[] args) throws Exception {
	//WeiboCrawler4.start4("");
	//	WeiboCrawler4b.start4b();
		//WeiboCrawler5.start5();
		BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\联想com2.txt"));
		String ss=null;
		while((ss=br.readLine())!=null){
			String[] sp = ss.split(" ");
		 WeiboCrawler4c.second(sp[0], sp[1]);
			//set.addAll(set2);
			//Thread.sleep(2000);
		}
		
		br.close();
	}
}
