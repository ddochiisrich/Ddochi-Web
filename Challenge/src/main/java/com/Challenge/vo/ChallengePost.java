package com.Challenge.vo;

import java.sql.Timestamp;

public class ChallengePost {
	
	private int postNo;
	private int postView;
	private String postContent;
	private String postTitle;
	private int like1;
	private String postFile;
	private Timestamp postRegDate;
	private String writer;
	
	// 기본생성자
	public ChallengePost() {}
	
	// 생성자 오버라이드
	public ChallengePost(int postNo, int postView, String postContent, String postTitle, int like1, String postFile,
			Timestamp postRegDate, String writer) {

		this.postNo = postNo;
		this.postView = postView;
		this.postContent = postContent;
		this.postTitle = postTitle;
		this.like1 = like1;
		this.postFile = postFile;
		this.postRegDate = postRegDate;
		this.writer = writer;
	}
	
	// Getter, Setter
	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getPostView() {
		return postView;
	}

	public void setPostView(int postView) {
		this.postView = postView;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public int getLike1() {
		return like1;
	}

	public void setLike1(int like1) {
		this.like1 = like1;
	}

	public String getPostFile() {
		return postFile;
	}

	public void setPostFile(String postFile) {
		this.postFile = postFile;
	}

	public Timestamp getPostRegDate() {
		return postRegDate;
	}

	public void setPostRegDate(Timestamp postRegDate) {
		this.postRegDate = postRegDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	

}
