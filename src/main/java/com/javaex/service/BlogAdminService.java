package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogAdminDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogAdminService {
	@Autowired
	private BlogAdminDao blogAdminDao;

	// service 현재 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("1. service - 현재 블로그 정보 가져오기");

		return blogAdminDao.getBlogInfo(id);
	}

	// service 블로그 정보 수정
	public int modifyBasic(String id, BlogVo blogVo, MultipartFile file) {
		System.out.println("1. service - 블로그 정보 수정");

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
}
