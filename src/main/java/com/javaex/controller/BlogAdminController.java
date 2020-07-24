package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogAdminController {
	
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		return "blog/admin/blog-admin-basic";
	}
}
