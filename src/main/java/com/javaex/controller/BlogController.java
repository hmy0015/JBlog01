package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	// 블로그 메인
	@RequestMapping("/{id}")
	public String main( @PathVariable("id") String id,
						Model model) {
		System.out.println("[ blog main ]");
		
		// 로그인 한 사용자가 해당 블로그의 사용자인 지 판단, 블로그 타이틀 클릭 시 해당 블로그로 이동 (블로그 헤더에서 사용)
		model.addAttribute(id); 

		// 현재 블로그의 정보 가져오기
		BlogVo bVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", bVo);

		// 현재 블로그의 카테고리 정보 가져오기
		List<CategoryVo> cVo = blogService.getCategoryInfo(id);
		model.addAttribute("cateList", cVo);

		// 현재 블로그의 게시글 목록 가져오기 (가장 최근 카테고리)
		List<PostVo> pVo = blogService.getlistInfo(id);
		model.addAttribute("postList", pVo);

		// 현재 블로그의 최근 게시글 가져오기 (가장 최근 카테고리)
		PostVo post = blogService.getPostInfo(id);
		model.addAttribute("postVo", post);
		
		return "blog/blog-main";
	}
}
