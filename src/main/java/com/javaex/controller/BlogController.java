package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	// 블로그 메인
	@RequestMapping("/{id}")
	public String main( @PathVariable("id") String id,
						@ModelAttribute PostVo postVo,
						@RequestParam(value="crtCateNo", required=false, defaultValue = "0") int crtCateNo,
						Model model) {
		System.out.println("[ blog main ]");
		
		// 로그인 한 사용자가 해당 블로그의 사용자인 지 판단, 블로그 타이틀 클릭 시 해당 블로그로 이동 (블로그 헤더에서 사용)
		model.addAttribute(id); 

		// 현재 블로그의 정보 가져오기
		BlogVo bVo = blogService.getBlogInfo(id);
		model.addAttribute("blogVo", bVo);
		
		Map<String, Object> map = blogService.getMainInfo(id, crtCateNo, postVo);
		model.addAttribute("cateList", map.get("cateList"));
		model.addAttribute("postList", map.get("postList"));
		model.addAttribute("postVo", map.get("postVo"));

		return "blog/blog-main";
	}

}
