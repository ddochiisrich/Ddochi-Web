$(function(){
	
		$("#detailDelete").on("click", function(){
		
		$("#pass").val();
		let pass = $("#pass").val();
		if(pass.trim().length <= 0){
			alert("please enter the password");
			return false;
		}	
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "deleteProcess");
		$("#checkForm").attr("method", "post");
		
		$("#checkForm").submit();
	});
	
	$("#detailUpdate").on("click", function(){
		
		$("#pass").val();
		let pass = $("#pass").val();
		if(pass.trim().length <= 0){
			alert("please enter the password");
			return false;
		}	
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "updateForm");
		$("#checkForm").attr("method", "post");
		
		$("#checkForm").submit();
	});


$("#updateForm").on("submit", function(){
		
		if($("#writer").val().trim().length <= 0){
			alert("please enter the writer")	;
			$("#writer").val("");
			$("#writer").focus();
			return false;		
		}
		if($("#title").val().trim().length <= 0){
			alert("please enter the title")	;
			return false;		
		}
		if($("#pass").val().trim().length <= 0){
			alert("please enter the password");
			return false;		
		}
		if($("#content").val().trim().length <= 0){
			alert("please enter the content");
			return false;		
		}
		
	});
	
	
	
	$("#writeForm").on("submit", function(){
		
		if($("#writer").val().trim().length <= 0){
			alert("please enter the writer")	;
			$("#writer").val("");
			$("#writer").focus();
			return false;		
		}
		if($("#title").val().trim().length <= 0){
			alert("please enter the title")	;
			return false;		
		}
		if($("#pass").val().trim().length <= 0){
			alert("please enter the password");
			return false;		
		}
		if($("#content").val().trim().length <= 0){
			alert("please enter the content");
			return false;		
		}
		
	});
	
});