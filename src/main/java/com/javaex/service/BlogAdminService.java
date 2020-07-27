package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogAdminDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogAdminService {
	@Autowired
	private BlogAdminDao blogAdminDao;

	// service 현재 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("1. service - 현재 블로그 정보 가져오기");

		return blogAdminDao.getBlogInfo(id);
	}

	// service 기본 설정 수정 
	public int modifyBasic(String id, BlogVo blogVo, MultipartFile file) {
		System.out.println("1. service - 기본 설정 수정 ");

		////////////////데이터 추출 ////////////////

		// 파일이 저장 될 하드의 위치
		String saveDir = "C:\\javaStudy\\upload";

		// 업로드 된 파일의 이름
		String orgName = file.getOriginalFilename();
		System.out.println("파일명 : " + orgName);

		// 해당 파일의 확장자 추출
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("확장자 : " + exName);

		// 업로드 된 파일의 사이즈
		long fileSize = file.getSize(); // getSize의 자료형은 long임
		System.out.println("파일 사이즈 : " + fileSize);

		// 하드에 저장 될 파일의 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; // 현재 시간 + 영문과 숫자를 조합한 랜덤 이름 + 확장자
		System.out.println("저장명 : " + saveName);

		// 하드에 저장 된 파일의 경로
		String filePath = saveDir + "\\" + saveName;

		////////////////추출한 데이터 서버에 복사 ////////////////
		try {

			byte[] fileData = file.getBytes(); // 저장할 파일을 'fileData' 변수에 담기
			OutputStream out = new FileOutputStream(filePath); // 하드에 사진을 저장하기 위해서 아웃풋스트림이 사용됨
			BufferedOutputStream bOut = new BufferedOutputStream(out); // 버퍼에 담기 (속도 향상)

			bOut.write(fileData);
			bOut.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 수정한 내용 담기
		BlogVo bVo = new BlogVo(id, blogVo.getBlogTitle(), saveName);

		return blogAdminDao.updateBasic(bVo);
	}

	// service 카테고리 정보 불러오기
	public List<CategoryVo> getCategoryList(String id) {
		System.out.println("1. service - 카테고리 정보 불러오기 ");
		
		List<CategoryVo> cateList = blogAdminDao.getCategoryList(id);

		return cateList;
	}
	
	// service 카테고리 추가
	public CategoryVo addCate(String id, CategoryVo cateVo) {
		System.out.println("1. service - 카테고리 추가하기 ");

		cateVo.setId(id);		
		blogAdminDao.addCate(cateVo); // 저장

		int cateNo = cateVo.getCateNo(); // 해당 카테고리의 no값 가져오기
		
		return blogAdminDao.selectByNo(cateNo);
	}
	
	// service 카테고리 삭제
	public int delCate(int cateNo) {
		System.out.println("1. service - 카테고리 삭제하기 ");
		
		CategoryVo cateVo = blogAdminDao.selectByNo(cateNo); // 삭제하려는 카테고리의 정보 받아오기
		int postCnt = cateVo.getCnt(); // 해당 카테고리의 게시글 개수 파악
		System.out.println(postCnt);
		
		if(postCnt == 0) { // 게시글이 없을 경우 해당 카테고리 삭제

			return blogAdminDao.delCate(cateVo);
		}
		else { // 아닐 경우 삭제하지 않고 0 반환
			return 0;
		}
	}
}
