package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {
  public static void main(String[] args) throws UnsupportedEncodingException {
	  String keyword="联想电脑";
	String encode1 = URLEncoder.encode(keyword, "utf-8");
	String encode2 = URLEncoder.encode(encode1, "utf-8");
	System.out.println(encode2);
}
}
