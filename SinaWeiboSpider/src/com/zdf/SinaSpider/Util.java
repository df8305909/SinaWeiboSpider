package com.zdf.SinaSpider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import test.DBUtil;

import com.pojo.Comment;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
//爬取数据去重


public class Util {
public static void main(String[] args) throws Exception {
	Set<Comment> set = new HashSet<Comment>();
	//set.add( new Comment("wq","", "p1"));
//	set.add( new Comment("","c", "p1"));
	/*set.add( new Comment(100, ""));
	set.add( new Comment(200, ""));
	set.add( new Comment(2, "p2"));
	set.add( new Comment(3, "p3"));
	set.add( new Comment(4, "p2"));
	set.add( new Comment(5, "p3"));
	set.add( new Comment(6, "p4"));*/
	//去除set中重复数据的方法
	Set<Comment> set2 = Util.removeDuplicate(set);
	Iterator<Comment> it = set2.iterator();
	while(it.hasNext()){
		Comment next = it.next();
		System.out.println(next.toString());
	}
	
}
public static Set<Comment> removeDuplicate(Set<Comment> set) {
    Map<String, Comment> map = new HashMap<String, Comment>();
    Set<Comment> tempSet = new HashSet<Comment>();
         for(Comment p : set) {
              if(p.getComment()!=null||p.getComment()!=""){
            	  if(map.get(p.getComment()) == null ) {
                      map.put(p.getComment(), p);                        
                      tempSet.add(p);
                 }
              }
          }
    
    return tempSet;
}
//存入数据库
public static void  saveToSql(Comment c){
	Connection con = DBUtil.getCon();
    String sql = "insert into comments(uid,comment,href,sentiment,keyword) values(?,?,?,?,?)";
	PreparedStatement pstm = null;
	ResultSet rst=null;
	try {
		pstm = con.prepareStatement(sql);
		pstm.setString(1, c.getUid());
		pstm.setString(2, c.getComment());
		pstm.setString(3, c.getHref());
		pstm.setString(4, c.getSentiment());
		pstm.setString(5, c.getKeyword());
		pstm.executeUpdate();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	DBUtil.close(con, pstm, rst);
	
}
	
	public static void CopyToTxt (String comment){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter("./commentdata/test.txt"));
			bw.write(comment);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void DelNull (){
		Connection con = DBUtil.getCon();
		  String sql = "delete from comments where comment='' or comment is null";
		  PreparedStatement pstm = null;
			ResultSet rst=null;
			try {
				pstm = con.prepareStatement(sql);
				pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DBUtil.close(con, pstm, rst);
	}
	
}

