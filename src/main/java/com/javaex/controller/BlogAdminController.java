package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogAdminService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogAdminController {
	@Autowired
	private BlogAdminService blogAdminService;

	// 기본 설정 페이지
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		System.out.println("[ admin basic ]");

		// 블로그 정보 가져오기
		BlogVo blogVo = blogAdminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("admin", "basic"); // 어드민 메뉴 인클루드 시 현재 위치한 메뉴를 표시해주기 위함

		return "blog/admin/blog-admin-basic";
	}

	// 기본 설정 수정
	@RequestMapping("/{id}/admin/modify")
	public String modifyBasic(@PathVariable("id") String id, @ModelAttribute BlogVo blogVo,
			@RequestParam("file") MultipartFile file) {
		System.out.println("[ basic modify ]");

		blogAdminService.modifyBasic(id, blogVo, file);

		return "redirect:/{id}/admin/basic";
	}

	// 카테고리 설정 페이지 로드
	@RequestMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
	
		// 블로그 정보 가져오기
		BlogVo blogVo = blogAdminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("admin", "category"); // 어드민 메뉴 인클루드 시 현재 위치한 메뉴를 표시해주기 위함
	
		return "blog/admin/blog-admin-cate";
	}

	// 카테고리 리스트 가져오기
	@ResponseBody
	@RequestMapping("/{id}/admin/cateList")
	public List<CategoryVo> cateList(@PathVariable("id") String id) {

		// 카테고리 리스트 가져오기
		List<CategoryVo> cateVo = blogAdminService.getCategoryList(id);

		return cateVo;
	}

	// 카테고리 추가
	@ResponseBody
	@RequestMapping("/{id}/admin/addCate")
	public CategoryVo addCate(@PathVariable("id") String id, @ModelAttribute CategoryVo cateVo) {
		System.out.println("[ addCate ]");

		CategoryVo categoryVo = blogAdminService.addCate(id, cateVo);

		return categoryVo;
	}

	// 카테고리 삭제
	@ResponseBody
	@RequestMapping("/admin/delCate")
	public int delCate(@RequestParam("cateNo") int cateNo) {
		System.out.println("[ delete ]");
		
		int cnt = blogAdminService.delCate(cateNo);
		
		return cnt;
	}
	
	// 글쓰기 페이지
	@RequestMapping("/{id}/admin/writeForm")
	public String adminWriteForm(@PathVariable("id") String id, Model model) {
		System.out.println("[ writeForm ]");

		// 블로그 정보 가져오기
		BlogVo blogVo = blogAdminService.getBlogInfo(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("admin", "write"); // 어드민 메뉴 인클루드 시 현재 위치한 메뉴를 표시해주기 위함

		// 카테고리 정보 가져오기
		List<CategoryVo> cateVo = blogAdminService.getCategoryInfo(id);
		model.addAttribute("cateVo", cateVo);
		
		return "blog/admin/blog-admin-write";
	}
	

	@RequestMapping("/{id}/admin/write")
	public String adminWrite(@PathVariable("id") String id, @ModelAttribute PostVo postVo) {
		System.out.println("[ write ]");
		
		blogAdminService.write(postVo);
		
		return "redirect:/{id}/admin/writeForm";
	}
}
