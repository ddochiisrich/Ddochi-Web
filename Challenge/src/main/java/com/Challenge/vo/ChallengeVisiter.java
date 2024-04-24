package com.Challenge.vo;

import java.sql.Timestamp;

public class ChallengeVisiter {
	
	private Timestamp visiterRegDate;
	private int visiter;

	// 기본생성자
	public ChallengeVisiter() {}
	
	// 생성자 오버라이드
	public ChallengeVisiter(Timestamp visiterRegDate, int visiter) {
		super();
		this.visiterRegDate = visiterRegDate;
		this.visiter = visiter;
	}
	
	// Getter, Setter
	public Timestamp getVisiterRegDate() {
		return visiterRegDate;
	}

	public void setVisiterRegDate(Timestamp visiterRegDate) {
		this.visiterRegDate = visiterRegDate;
	}

	public int getVisiter() {
		return visiter;
	}

	public void setVisiter(int visiter) {
		this.visiter = visiter;
	}
}
