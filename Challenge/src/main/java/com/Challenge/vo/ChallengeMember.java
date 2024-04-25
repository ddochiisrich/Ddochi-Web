package com.Challenge.vo;

public class ChallengeMember {

	private int memberNo;
	private String nickName;
	private String name;
	private String pass;
	private String id;
	private String email;
	private String address;
	private String phone;
	private String mailCheck;
	
	// 기본생성자
	public ChallengeMember() {}

	// 생성자 오버라이드
	public ChallengeMember(int memberNo, String nickName, String name, String pass, String id, String email,
			String address, String phone, String mailCheck) {
		super();
		this.memberNo = memberNo;
		this.nickName = nickName;
		this.name = name;
		this.pass = pass;
		this.id = id;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.mailCheck = mailCheck;
	}

	// Getter, Setter
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMailCheck() {
		return mailCheck;
	}

	public void setMailCheck(String mailCheck) {
		this.mailCheck = mailCheck;
	}
	
	
	
}
