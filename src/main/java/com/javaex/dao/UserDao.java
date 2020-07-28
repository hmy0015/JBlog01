package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	// service 회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("dao - join");
		
		return sqlSession.insert("user.insertUser", userVo);
	}
	
	public int createBlog(BlogVo blogVo) {
		System.out.println("dao - createBlog");
		
		return sqlSession.insert("blog.createBlog", blogVo);
	}

	public int insertCategory(CategoryVo categoryVo) {
		System.out.println("dao - insertCategory");
		
		return sqlSession.insert("category.insertCategory", categoryVo);
	}
	
	// dao 로그인
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao - login");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}

	// dao 입력한 아이디 값과 중복되는 데이터가 있는 지 검사
	public UserVo checkId(String id) {
		System.out.println("UserDao - 중복체크");
		return sqlSession.selectOne("user.selectById", id);
	} 
}
