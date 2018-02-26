package com.zdf.SinaSpider;

public class User {
	private String uid="";//昵称
	private String sex="";//性别
	private String location="";//地区
	private String certification="";//认证
	private String birth="";//生日
	private String cert_info="";//认证信息
	private String intro="";//简介
	private int member=-1;//会员等级
	private int weibo_n=0;//微博数
	private int foing_n=0;//关注数
	private int group_n=0;//分组
	private int foer_n=0;//粉丝数
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCert_info() {
		return cert_info;
	}
	public void setCert_info(String cert_info) {
		this.cert_info = cert_info;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getWeibo_n() {
		return weibo_n;
	}
	public void setWeibo_n(int weibo_n) {
		this.weibo_n = weibo_n;
	}
	public int getFoing_n() {
		return foing_n;
	}
	public void setFoing_n(int foing_n) {
		this.foing_n = foing_n;
	}
	public int getGroup_n() {
		return group_n;
	}
	public void setGroup_n(int group_n) {
		this.group_n = group_n;
	}
	public int getFoer_n() {
		return foer_n;
	}
	public void setFoer_n(int foer_n) {
		this.foer_n = foer_n;
	}
}
