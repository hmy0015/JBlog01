package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	// service - 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("1. service - 블로그 정보 가져오기");

		return blogDao.getBlogInfo(id);
	}

	// service - 카테고리 정보 가져오기
	public List<CategoryVo> getCategoryInfo(String id) {
		System.out.println("1. service - 카테고리 정보 가져오기");

		List<CategoryVo> cVo = blogDao.getCategoryInfo(id);

		return cVo;
	}

	// service - 최근 카테고리의 게시글 목록 가져오기
	public List<PostVo> getlistInfo(String id) {
		System.out.println("1. service - 최근 카테고리의 게시글 목록 가져오기");

		List<PostVo> pVo = blogDao.getlistInfo(id);

		return pVo;
	}

	// service - 최근 게시글 정보 가져오기
	public PostVo getPostInfo(String id) {
		System.out.println("1. service - 최근 게시글 정보 가져오기");

		PostVo post = blogDao.getPostInfo(id);

		return post;
	}
}
