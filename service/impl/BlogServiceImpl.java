package com.example.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Blog;
import com.example.blog.repository.BlogMapper;
import com.example.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogMapper blogMapper;

	@Override
	public List<Blog> list() {
		return blogMapper.findAll();
	}

}
