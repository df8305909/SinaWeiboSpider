package com.zdf.SinaSpider;

public class WeiboCrawler4c {
static int util=0;
	
	public static void main(String[] args) throws Exception {
		int a=1;int b=0;int c=0;int d=0,e=0;
		for(int i=1;i<3562;i+=5){
			
			int be=i;
			int end=i+4;
			if(end>3562){
				end=3562;
			}
			
			if(a==1&&e==0){
				WeiboCrawler5b.sta("https://weibo.cn/lenovo?f=search_0", be, end);
				//System.out.println("1");
				a=0;b=1;
				Thread.sleep(400);
			}else if(a==0&&b==1){
				//System.out.println("2");
				b=0;c=1;
				WeiboCrawler5.sta("https://weibo.cn/lenovo?f=search_0", be, end);
				Thread.sleep(400);
			}else if(b==0&&c==1){
				//System.out.println("3");
				c=0;d=1;
				WeiboCrawler5c.sta("https://weibo.cn/lenovo?f=search_0", be, end);
				Thread.sleep(400);
			}else if(c==0&&d==1){
				//System.out.println("4");
				d=0;e=1;
				WeiboCrawler5d.sta("https://weibo.cn/lenovo?f=search_0", be, end);
				Thread.sleep(400);
			}else if(d==0&&e==1){
				//System.out.println("5");
				e=0;a=1;
				WeiboCrawler5e.sta("https://weibo.cn/lenovo?f=search_0", be, end);
				Thread.sleep(400);
			}
			util++;
			
		}
	}
	
	public static void first(String url,String page) throws Exception{
	int	pa=Integer.parseInt(page);
		int a=1;int b=0;int c=0;int d=0,e=0;
		for(int i=1;i<=pa;i+=10){
			
			int be=i;
			int end=i+9;
			if(end>pa){
				end=pa;
			}
			
			if(a==1&&e==0){
				WeiboCrawler5b.sta(url, be, end);
				//System.out.println("1");
				a=0;b=1;
				Thread.sleep(400);
			}else if(a==0&&b==1){
				//System.out.println("2");
				b=0;c=1;
				WeiboCrawler5.sta(url, be, end);
				Thread.sleep(400);
			}else if(b==0&&c==1){
				//System.out.println("3");
				c=0;d=1;
				WeiboCrawler5c.sta(url, be, end);
				Thread.sleep(400);
			}else if(c==0&&d==1){
				//System.out.println("4");
				d=0;e=1;
				WeiboCrawler5d.sta(url, be, end);
				Thread.sleep(400);
			}else if(d==0&&e==1){
				//System.out.println("5");
				e=0;a=1;
				WeiboCrawler5e.sta(url, be, end);
				Thread.sleep(400);
			}
			util++;
			
		}
	}
	
	
	public static void second(String url,String page) throws Exception{
		int	pa=Integer.parseInt(page);
			int a=1;int b=0;int c=0;int d=0,e=0;
			for(int i=1;i<=pa;i+=6){
				
				int be=i;
				int end=i+5;
				if(end>pa){
					end=pa;
				}
				
				if(a==1&&e==0){
					WeiboCrawler6b.sta(url, be, end);
					//System.out.println("1");
					a=0;b=1;
					Thread.sleep(400);
				}else if(a==0&&b==1){
					//System.out.println("2");
					b=0;c=1;
					WeiboCrawler6a.sta(url, be, end);
					Thread.sleep(400);
				}else if(b==0&&c==1){
					//System.out.println("3");
					c=0;d=1;
					WeiboCrawler6c.sta(url, be, end);
					Thread.sleep(400);
				}else if(c==0&&d==1){
					//System.out.println("4");
					d=0;e=1;
					WeiboCrawler6d.sta(url, be, end);
					Thread.sleep(400);
				}else if(d==0&&e==1){
					//System.out.println("5");
					e=0;a=1;
					WeiboCrawler6e.sta(url, be, end);
					Thread.sleep(400);
				}
				util++;
				
			}
		}
	
}
