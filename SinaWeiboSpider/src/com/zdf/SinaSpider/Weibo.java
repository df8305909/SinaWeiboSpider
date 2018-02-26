package com.zdf.SinaSpider;

public class Weibo {
	String uid;//用户名称
	String context;//微博内容
	String device;//设备
	String date;//日期
	int comment;//评论数目
	int retwee;//转发数目
	int love;//点赞数目
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public int getRetwee() {
		return retwee;
	}
	public void setRetwee(int retwee) {
		this.retwee = retwee;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
}
