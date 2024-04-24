package com.Challenge.vo;

import java.sql.Timestamp;

public class ChallengeComment {

	private int commentNo;
	private Timestamp commentRegDate;
	private String commentContent;
	
	// 기본생성자
	public ChallengeComment() {}
	
	// 생성자 오버라이드
	public ChallengeComment(int commentNo, Timestamp commentRegDate, String commentContent) {
		super();
		this.commentNo = commentNo;
		this.commentRegDate = commentRegDate;
		this.commentContent = commentContent;
	}

	// Getter, Setter
	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public Timestamp getCommentRegDate() {
		return commentRegDate;
	}

	public void setCommentRegDate(Timestamp commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
}
