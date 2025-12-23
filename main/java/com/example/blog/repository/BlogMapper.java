package com.example.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.blog.entity.Blog;

@Mapper
public interface BlogMapper {

//	・一覧取得 List findAll(); 削除日時が null のブログ記事を取得し、ID が降順になるようにしてください。
	List<Blog> findAll();
	
//	・ID 指定で取得 Blog findById(int id); 指定した ID のブログ記事を取得し、削除日時が null のものだけ返してください。
	Blog findById(int id);
	
//	・新規登録 void save(Blog blog); Blog オブジェクトのタイトルと内容をデータベースに保存し、作成日時・更新日時は現在時刻をセットしてください。
	void save(Blog blog);
	
//	・更新 void update(Blog blog); Blog オブジェクトのタイトルと内容を更新し、更新日時は現在時刻に設定してください。
	void update(Blog blog);
	
//	・削除 void delete(int id); 指定した ID のブログ記事の削除日時に現在時刻をセットしてください。
	void delete(int id);

}
