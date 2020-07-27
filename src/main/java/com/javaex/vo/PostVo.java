package com.javaex.vo;

public class PostVo {
	private int postNo;
	private int cateNo;
	private String id;
	private String postTitle;
	private String postContent;
	private String regDate;
	
	public PostVo() {}
	
	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate) {
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", cateNo=" + cateNo + ", id=" + id + ", postTitle=" + postTitle
				+ ", postContent=" + postContent + ", regDate=" + regDate + "]";
	}	
}
