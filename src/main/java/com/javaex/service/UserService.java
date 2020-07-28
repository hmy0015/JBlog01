package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	// service 회원가입
	public int join(UserVo userVo) {
		System.out.println("service - join");
		
		// 각 vo에 데이터를 담기 위한 변수들
		String uID = userVo.getId();
		String blogTitle = userVo.getUserName() + "의 블로그입니다.";

		BlogVo blogVo = new BlogVo(uID, blogTitle, "default"); // BlogVo
		CategoryVo categoryVo = new CategoryVo(0, uID, 0, "미분류", "기본으로 생성되는 카테고리입니다.", null); // CategoryVo
		
		userDao.insertUser(userVo); // 회원가입
		userDao.createBlog(blogVo); // 블로그 생성
		userDao.insertCategory(categoryVo); // 기본 카테고리 생성
		
		return 0;
	}

	// service 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("service - login");
		
		return userDao.selectUser(userVo);
	}

	// service 입력한 아이디 값과 중복되는 데이터가 있는 지 검사
	public boolean checkId(String id) {
		System.out.println("UserService - 중복체크");
		UserVo userVo = userDao.checkId(id);
		boolean result = true;
		
		if(userVo == null) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
}
