package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogAdminService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogAdminController {
	@Autowired
	private BlogAdminService blogAdminService;
	
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		
		BlogVo blogVo = blogAdminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
}
