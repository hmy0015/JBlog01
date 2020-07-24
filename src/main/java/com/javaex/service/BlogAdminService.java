package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogAdminDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogAdminService {
	@Autowired
	private BlogAdminDao blogAdminDao;
	
	// service 현재 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("1. service - 현재 블로그 정보 가져오기");
				
		return blogAdminDao.getBlogInfo(id);
	}
}
