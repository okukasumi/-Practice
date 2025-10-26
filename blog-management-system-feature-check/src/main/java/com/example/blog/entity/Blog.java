package com.example.blog.entity;
import java.time.LocalDateTime;

public class Blog {
	
	public Blog(){
	}
	
	private int id;
	private String title;
	private String content;
	private LocalDateTime creation;
	private LocalDateTime update;
	private LocalDateTime delete;
	
    //"id" CHAR(5) PRIMARY KEY,
	
	public void setId(int x) {
		this.id=x;
	}
	
	public int getId() {
		return this.id;
	}
	
    //"タイトル" VARCHAR(30)
	
	public void setTitle(String s) {
		this.title=s;
	}
	
	public String getTitle() {
		return this.title;
	}
	
    //"内容" VARCHAR(40000),
	
	public void setContent(String s) {
		this.content=s;
	}
	
	public String getContent() {
		return this.content;
	}
	
    //"作成日時" TIMESTAMP
	
	public void setCreation(LocalDateTime d) {
		this.creation=d;
	}
	
	public LocalDateTime getCreation() {
		return this.creation;
	}
	
    //"更新日時" TIMESTAMP
	
	public void setUpdate(LocalDateTime d) {
		this.update=d;
	}
	
	public LocalDateTime getUpdate() {
		return this.update;
	}
	
	//"削除日時" TIMESTAMP
	
	public void setDelete(LocalDateTime d) {
		this.delete=d;
	}
	
	public LocalDateTime getDetele() {
		return this.delete;
	}

}
