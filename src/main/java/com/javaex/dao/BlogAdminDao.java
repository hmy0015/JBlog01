package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class BlogAdminDao {
	@Autowired
	private SqlSession sqlSession;

	// dao 현재 블로그 정보 가져오기
	public BlogVo getBlogInfo(String id) {
		System.out.println("dao - 현재 블로그 정보 가져오기");

		return sqlSession.selectOne("blog.getBlogInfo", id);
	}

	// dao 기본 설정 수정 
	public int updateBasic(BlogVo blogVo) {
		System.out.println("dao - 기본 설정 수정 ");

		return sqlSession.update("blog.updateBasic", blogVo);
	}

	// dao 카테고리 정보 불러오기 (카테고리 관리 페이지에서 사용)
	public List<CategoryVo> getCategoryList(String id) {
		System.out.println("dao - 카테고리 정보 불러오기 ");

		List<CategoryVo> cateList = sqlSession.selectList("category.getCategoryList", id);

		return cateList;
	}

	// dao 카테고리 추가
	public void addCate(CategoryVo cateVo) {
		System.out.println("dao - 카테고리 추가하기 ");

		sqlSession.insert("category.addCate", cateVo);
	}

	// dao 등록한 카테고리 정보 가져오기
	public CategoryVo selectByNo(int cateNo) {
		System.out.println("dao - 등록한 카테고리 정보 가져오기 ");

		return sqlSession.selectOne("category.selectByNo", cateNo);
	}

	// dao 카테고리 삭제
	public int delCate(CategoryVo cateVo) {
		System.out.println("dao - 카테고리 삭제하기 ");

		sqlSession.delete("category.delCate", cateVo);

		return 1;
	}

	// dao 카테고리 정보 불러오기 (글쓰기 관리 페이지에서 사용)
	public List<CategoryVo> getCategoryInfo(String id) {
		System.out.println("dao - 카테고리 정보 불러오기 ");

		List<CategoryVo> cateList = sqlSession.selectList("category.getCategoryInfo", id);

		return cateList;
	}
	
	// dao 게시글 등록
	public int addPost(PostVo postVo) {
		System.out.println("dao - 게시글 등록 ");
		
		return sqlSession.insert("post.addPost", postVo);
	}
}
