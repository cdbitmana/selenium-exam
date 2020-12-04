package com.sbs.selenium.example.dto;

public class NaverNewsArticle {
	private String code;
	private String title;
	private String body;
	private String writer;
	private String imgUrl;
	
	public NaverNewsArticle(String code, String title, String body, String writer, String imgUrl) {
		this.code = code;
		this.title = title;
		this.body = body;
		this.writer = writer;
		this.imgUrl = imgUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
