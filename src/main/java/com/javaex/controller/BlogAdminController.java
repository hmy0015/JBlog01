package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogAdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
public class BlogAdminController {
	@Autowired
	private BlogAdminService blogAdminService;
	
	// 기본 설정 페이지
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		System.out.println("[ admin basic ]");
		
		BlogVo blogVo = blogAdminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	// 기본 설정 수정
	@RequestMapping("/{id}/admin/modify")
	public String modifyBasic ( @PathVariable("id") String id,
								@ModelAttribute BlogVo blogVo,
								@RequestParam("file") MultipartFile file) {
		System.out.println("[ basic modify ]");
		System.out.println(file.getOriginalFilename());

		blogAdminService.modifyBasic(id, blogVo, file);
		
		return "redirect:/";
	}
	
	// 카테고리 설정 페이지
	@RequestMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {

		List<CategoryVo> cateList = blogAdminService.getCategoryList(id);
		model.addAttribute("cList", cateList);
		
		return "blog/admin/blog-admin-cate";
	}
	
	// 글쓰기 페이지
	@RequestMapping("/{id}/admin/write")
	public String adminWrite(@PathVariable("id") String id) {
		return "blog/admin/blog-admin-write";
	}
}
