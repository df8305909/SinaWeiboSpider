package com.zdf.SinaSpider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;






public class adsdas {

	  public static void main(String[] args) throws IOException {   
		  BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\Workspaces\\2014-2\\assm\\commentdata\\urltxt.txt",true));
	        //这是需要获取的文件夹路径  
	        String path = "/Users/XXY/Desktop/test";   
	        Set set = getFile(path,0);   
	        
	        Iterator it = set.iterator();
	        while(it.hasNext()){
	        	Object next = it.next();
	        	BufferedReader br =new BufferedReader(new FileReader("D:\\Workspaces\\2014-2\\assm\\commentdata\\url\\"+next));
	        	String s=null;
	        	while((s=br.readLine())!=null){
	        		bw.write(s);
	        		bw.newLine();
	        		
	        	}
	        	br.close();
	        	
	        }
	        
	        bw.close();
	        
	    }   

	    private static Set getFile(String path,int deep) throws IOException{   
	        // 获得指定文件对象  
	        File file = new File("D:\\Workspaces\\2014-2\\assm\\commentdata\\url");   
	        // 获得该文件夹内的所有文件   
	        File[] array = file.listFiles();   
	        	Set  set=new HashSet();
	        for(int i=0;i<array.length;i++)
	        {   
	            if(array[i].isFile())//如果是文件
	            {   
	                    for (int j = 0; j < deep; j++)//输出前置空格
	                    System.out.print(" ");
	                // 只输出文件名字  
	                System.out.println( array[i].getName());   
	                set.add(array[i].getName());
	                // 输出当前文件的完整路径   
	               // System.out.println("#####" + array[i]);   
	                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下   
	               // System.out.println(array[i].getPath());   
	            }
	            /*else if(array[i].isDirectory())//如果是文件夹
	            {  
	                    for (int j = 0; j < deep; j++)//输出前置空格
	                    System.out.print(" ");

	                    System.out.println( array[i].getName());
	                    //System.out.println(array[i].getPath());
	                    //文件夹需要调用递归 ，深度+1
	                getFile(array[i].getPath(),deep+1);  
	            }   */
	        }
			return set;   
	    }   

}
