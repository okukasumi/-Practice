package com.example.blog.entity;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Blog {
	
	private int id;
	private String title;
	private String content;
	private LocalDateTime creation;
	private LocalDateTime update;
	private LocalDateTime delete;
	
    //"id" CHAR(5) PRIMARY KEY,
    //"タイトル" VARCHAR(30)
    //"内容" VARCHAR(40000),
    //"作成日時" TIMESTAMP	
    //"更新日時" TIMESTAMP
	//"削除日時" TIMESTAMP

	}


