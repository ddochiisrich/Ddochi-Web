$(function(){
	
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