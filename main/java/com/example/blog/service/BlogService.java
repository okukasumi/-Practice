package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Blog;

public interface BlogService {

	//全Blogオブジェクトを返す
	List<Blog> list();
	
}