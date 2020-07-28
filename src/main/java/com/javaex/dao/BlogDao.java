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
		System.out.println("dao - 블로그 정보 가져오기");

		return sqlSession.selectOne("blog.getBlogInfo", id);
	}

	// dao - 최상단 카테고리 넘버
	public int getMaxCateNo(String id) {
		System.out.println("dao - 최상단 카테고리 넘버 가져오기");
		
		Map<String, Object> map = sqlSession.selectOne("category.getMaxCateNo", id);
		
		// Oracle에서 데이터 타입이 number인 데이터를 Java에서 Integer로 받을 때 오류가 발생
		// 오브젝트를 String으로 만든 후 Integer.parseInt를 통해 Integer로 바꿔줘야 함
		int cateNo = Integer.parseInt(String.valueOf(map.get("CATE_NO")));
		
		return cateNo;
	}

	// dao -  카테고리 내 최근 게시글의 포스트 넘버
	public int getMaxPostNo(int cateNo) {
		System.out.println("dao - 카테고리 내 최근 게시글의 포스트 넘버 가져오기");
		
		Map<String, Object> map = sqlSession.selectOne("post.getMaxPostNo", cateNo);
		int postNo = Integer.parseInt(String.valueOf(map.get("POST_NO")));
		
		return postNo;
	}
	
	// dao - 카테고리 리스트 가져오기 (블로그 메인 3번에서 사용)
	public List<CategoryVo> getCategoryInfo(String id) {
		System.out.println("dao - 카테고리 리스트 가져오기");
		
		List<CategoryVo> cateList = sqlSession.selectList("category.getCategoryInfo", id);				
		
		return cateList;
	}

	// dao - 해당 카테고리 내 게시글 목록 (블로그 메인 4번에서 사용)
	public List<PostVo> getPostList(int cateNo) {
		System.out.println("dao - 해당 카테고리 내 게시글 목록 가져오기");
		
		List<PostVo> pList = sqlSession.selectList("post.getPostList", cateNo);
		
		return pList;
	}

	// dao - 해당 카테고리 내 게시글 정보 (블로그 메인 5번에서 사용)
	public PostVo getPostInfo(int postNo) {
		System.out.println("dao - 해당 카테고리 내 게시글 정보 가져오기");
		
		return sqlSession.selectOne("post.getPostInfo", postNo);
	}
}
