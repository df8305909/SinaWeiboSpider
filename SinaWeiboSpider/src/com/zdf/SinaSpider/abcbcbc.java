package com.zdf.SinaSpider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class abcbcbc {
public static void main(String[] args) throws Exception {
	
	BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\联想com.txt"));
	BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\联想com2.txt"));
	String s=null;
	while((s=br.readLine())!=null){
			String[] sp = s.split(" ");	
			if(sp.length==2){
			if(sp[1].contains("http")){
				System.out.println(s);
			}else{
				bw.write(s);
				bw.newLine();
			}
			
	}
	}
	
	
	br.close();
	bw.close();
}
}
