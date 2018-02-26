package com.pojo;


public class Comment {
	
	public Comment(String uid,String comment,String href,
			String sentiment,String keyword){
		this.uid=uid;
		this.comment=comment;
		this.href=href;
		this.sentiment=sentiment;
		this.keyword=keyword;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	private String uid;
	private String comment;
	private String href;
	private String sentiment;
	private String keyword;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comment [href=" + href + ", uid=" + uid + ", comment="
				+ comment + "]";
	}
	
	
	
}
