package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller

@RequiredArgsConstructor
public class BlogController {
	private final BlogService blogService;
	
	@GetMapping("/blogs")
	public String list(Model model) {
		model.addAttribute("blogs",blogService.list());
		return "blog/list";
	}
}
