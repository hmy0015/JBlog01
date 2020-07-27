package com.javaex.dao;

import java.util.List;
import java.util.Map;

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

	// dao - 카테고리 리스트 가져오기 (블로그 메인 3번에서 사용)
	public List<CategoryVo> getCategoryInfo(String id) {
		System.out.println("2. dao - 카테고리 리스트 가져오기");
		
		List<CategoryVo> cateList = sqlSession.selectList("category.getCategoryInfo", id);				
		
		return cateList;
	}

	// dao - 최상단 카테고리 넘버
	public int getMaxCateNo(String id) {
		System.out.println("2. dao - 최상단 카테고리 넘버 가져오기");
		
		Map<String, Object> map = sqlSession.selectOne("category.getMaxCateNo", id);
		int cateNo = (int)map.get("CATE_NO");
		System.out.println(cateNo);
		
		return cateNo;
	}

	// dao - 최상단 카테고리 내 최근 게시글의 포스트 넘버
	public int getMaxPostNo(String id) {
		System.out.println("2. dao - 최상단 카테고리 내 최근 게시글의 포스트 넘버 가져오기");
		
		Map<String, Object> map = sqlSession.selectOne("category.getMaxCateNo", id);
		int postNo = (int)map.get("POST_NO");
		System.out.println(postNo);
		
		return postNo;
	}

	// dao - 해당 카테고리 내 게시글 목록 (블로그 메인 4번에서 사용)
	public List<PostVo> getPostList(int cateNo) {
		System.out.println("2. dao - 해당 카테고리 내 게시글 목록 가져오기");
		
		List<PostVo> pList = sqlSession.selectList("category.getPostList", cateNo);
		
		return pList;
	}

	// dao - 해당 카테고리 내 게시글 정보 (블로그 메인 5번에서 사용)
	public PostVo getPostInfo(int postNo) {
		System.out.println("2. dao - 해당 카테고리 내 게시글 정보 가져오기");
		
		return sqlSession.selectOne("category.getPostInfo", postNo);
	}
}
