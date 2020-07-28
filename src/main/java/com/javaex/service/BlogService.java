package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		System.out.println("service - 블로그 정보 가져오기");
		
		return blogDao.getBlogInfo(id);
	}
	
	// service
	public Map<String, Object> getMainInfo(String id, int crtCateNo, PostVo postVo) {
		System.out.println("service - 게시글 및 카테고리 글 목록 가져오기");
		
		Map<String, Object> mainInfo = new HashMap<String, Object>();
		int cateNo = crtCateNo; // 카테고리 넘버 받아오기
		int postNo = postVo.getPostNo(); // 포스트 넘버 받아오기

		if(cateNo == 0) { // 카테고리 넘버가 0인 경우 (블로그 메인 첫 방문시)
			cateNo = blogDao.getMaxCateNo(id); // 최상단 카테고리 넘버 불러와 담기
		}
		
		if(postNo == 0) { // 포스트 넘버가 0인 경우 (블로그 메인 첫 방문시)
			postNo = blogDao.getMaxPostNo(cateNo); // 카테고리 내 최근 게시글의 포스트 넘버 불러와 담기
		}
		
		// 카테고리 리스트 담기
		List<CategoryVo> cateList = blogDao.getCategoryInfo(id);
		mainInfo.put("cateList", cateList);
		
		// 해당 카테고리 내 게시글 목록 담기
		List<PostVo> postList = blogDao.getPostList(cateNo);
		mainInfo.put("postList", postList);
		
		// 게시글 정보 담기
		PostVo pVo = blogDao.getPostInfo(postNo);
		mainInfo.put("postVo", pVo);
		
		return mainInfo;
	}
}
