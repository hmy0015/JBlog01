package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogAdminDao {
	@Autowired
	private SqlSession sqlSession;
	
	// dao 현재 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("2. dao - 현재 블로그 정보 가져오기");
				
		return sqlSession.selectOne("blog.getBlogInfo", id);
	}

	// dao 기본 설정 수정 
	public int updateBasic(BlogVo blogVo) {
		System.out.println("2. dao - 기본 설정 수정 ");
		
		return sqlSession.update("blog.updateBasic", blogVo);
	}
}
