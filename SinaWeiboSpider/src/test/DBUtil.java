package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBUtil {
	
	
	public static Connection getCon(){
		Connection con =null;
		try {
			//������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8","root","123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	public static void close(Connection con,PreparedStatement stm,ResultSet res){
		try {
			if(con !=null){
				con.close();
			}
			if(stm !=null){
				stm.close();
			}if(res !=null){
				res.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	public static void close(Connection con,Statement stm,ResultSet res){
		try {
			if(con !=null){
				con.close();
			}
			if(stm !=null){
				stm.close();
			}if(res !=null){
				res.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getCon());
	}
}
