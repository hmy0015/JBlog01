package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	// 회원가입 폼
	@RequestMapping("/joinForm") 
	public String joinForm() {
		System.out.println("[ joinForm ]");
		
		return "user/joinForm";
	}
	
	// 회원가입
	@RequestMapping("/join") 
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[ join ]");
		
		userService.join(userVo);
		
		return "user/joinSuccess";
	}

	// 로그인 폼
	@RequestMapping("/loginForm") 
	public String loginForm() {
		System.out.println("[ loginForm ]");
		
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[ login ]");
		
		UserVo authVo = userService.login(userVo);
		
		if(authVo != null) { // 로그인 성공 시
			// 세션에 로그인 한 사용자 정보 담기
			session.setAttribute("authUser", authVo);
			
			return "redirect:/";
		} 
		else { // 로그인 실패 시
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	// 로그아웃
	@RequestMapping("/logout") 
	public String logout(HttpSession session){
		System.out.println("[ logout ]");
		
		session.removeAttribute("authUser"); // 지정된 이름에 해당하는 객체를 세션에서 제거
		session.invalidate(); // 해당 세션을 없애고 세션에 속해있는 값들을 삭제
		
		return "redirect:/";
	}

	// 중복체크
	@ResponseBody
	@RequestMapping("/idcheck")
	public boolean idcheck(@RequestParam("id") String id) {
		System.out.println("1. UserController - 중복체크(ajax)");
		
		boolean result = userService.checkId(id);
		
		return result;
	}
}
