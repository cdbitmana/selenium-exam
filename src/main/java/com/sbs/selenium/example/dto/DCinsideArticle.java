package com.sbs.selenium.example.dto;

public class DCinsideArticle {
	private int id;
	private String title;
	private String writer;
	private String date;
	private int hit;
	private int recommend;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public DCinsideArticle(int id, String title, String writer, String date, int hit, int recommend) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.date = date;
		this.hit = hit;
		this.recommend = recommend;
	}
}
