$(function(){
	$("#writeForm").on("submit", function() {
		if($("#writer").val().length <= 0){
			alert("please enter the writer");
			$("#writer").focus();
			return false;
		}
		if($("#title").val().length <= 0){
			alert("please enter the title");
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length <= 0){
			alert("please enter the password");
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length <= 0){
			alert("please enter the content");
			$("#content").focus();
			return false;
		}
	})
	
	$("#detailUpdate").on("click", function(){
		
		var pass = $("#pass").val();
		if(pass.length <= 0){
			alert("please enter the password");
			return false
		}
	})
})