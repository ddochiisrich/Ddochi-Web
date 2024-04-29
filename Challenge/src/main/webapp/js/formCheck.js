$(function(){
	$("#signUpBtn").on("click", function(){
		// 회원가입 공백 반환 유효성검사
		if($("#memberId").val().trim().length <= 0){
			alert("please enter the id!");
			$("#memberId").focus();
			return false;
		}
		
		if($("#memberPw1").val().trim().length <= 0){
			alert("please enter the password!");
			$("#memberPw1").focus();
			return false;
		}
		
		if($("#memberName").val().trim().length <= 0){
			alert("please enter the name!");
			$("#memberName").focus();
			return false;
		}
		
		if($("#memberNickname").val().trim().length <= 0){
			alert("please enter the Nickname!");
			$("#memberNickname").focus();
			return false;
		}
		
		if($("#memberEmail").val().trim().length <= 0){
			alert("please enter the Email!");
			$("#memberEmail").focus();
			return false;
		}
		
		if($("#memberAddress").val().trim().length <= 0){
			alert("please enter the Address!");
			$("#memberAddress").focus();
			return false;
		}
		
		if($("#memberPhone").val().trim().length <= 0){
			alert("please enter the Phone Number!");
			$("#memberPhone").focus();
			return false;
		}
		// 비밀번호 일치하는지 확인하는 유효성검사
		if(($("#memberPw1").val().trim()) !== ($("#memberPw2").val().trim())){
			alert("please enter the same password!!");
			$("#memberPw2").focus();
			return false;
		}
	})
	
	$("#postForm").on("submit", function(){
		if($("#postTitle").val().trim().length <= 0){
			alert("please enter the Title!")
			$("#postTitle").focus();
			return false;
		}
		
		if($("#postContent").val().trim().length <= 0){
			alert("please enter the Content!")
			$("#postContent").focus();
			return false;
		}
	})


	$("#detailUpdate").on("click", function() {

		$("#postDetailCheck").attr("action", "updateForm");
		$("#postDetailCheck").attr("method", "post");
		$("#postDetailCheck").submit();
	})
	
	$("#detailDelete").on("click", function(){
		
		$("#postDetailCheck").attr("action", "deleteProcess");
		$("#postDetailCheck").attr("method", "post");
		$("#postDetailCheck").submit();
	})
	
	$("#searchForm").on("submit", function() {
		var keyword = $("#keyword").val();
		if(keyword.length <= 0) {
		alert("please enter the keyword!!");
		return false;
	}
		$(this).attr("method", "post");
		$(this).attr("action", "postMain");
	});
})