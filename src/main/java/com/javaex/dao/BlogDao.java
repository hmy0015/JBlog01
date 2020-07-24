package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;

	// dao - 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("2. dao - 블로그 정보 가져오기");

		return sqlSession.selectOne("blog.getBlogInfo", id);
	}

	// dao - 카테고리 정보 가져오기
	public List<CategoryVo> getCategoryInfo(String id) {
		System.out.println("2. dao - 카테고리 정보 가져오기");

		List<CategoryVo> cVo = sqlSession.selectList("category.getCategoryInfo", id);

		return cVo;
	}

	// dao - 최근 카테고리의 게시글 목록 가져오기
	public List<PostVo> getlistInfo(String id) {
		System.out.println("2. dao - 최근 카테고리의 게시글 목록 가져오기");

		List<PostVo> pVo = sqlSession.selectList("post.getlistInfo", id);

		return pVo;
	}

	// dao - 최근 게시글 정보 가져오기
	public PostVo getPostInfo(String id) {
		System.out.println("2. dao - 최근 게시글 정보 가져오기");

		PostVo post = sqlSession.selectOne("post.getPostInfo", id);

		return post;
	}
}
