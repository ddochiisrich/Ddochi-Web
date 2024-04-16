$(function() {
	
	$("#productAdd").on("submit", function(){
	if($("#productName").val().length <= 0){
		alert("상품명이 입력되지않았습니다. 상품명을 입력해주세요.")
		$("#productName").focus();
		return false;
	}
	if($("#productPrice").val().length <= 0){
		alert("판매가가 입력되지않았습니다. 판매가를 입력해주세요.")
		$("#productPrice").focus();
		return false;
	}
	if($("#productCode").val().length <= 0){
		alert("상품코드가 입력되지않았습니다. 상품코드를 입력해주세요.")
		$("#productCode").focus();
		return false;
	}
	if($("#productComment").val().length <= 0){
		alert("상품설명이 입력되지않았습니다. 상품설명을 입력해주세요.")
		$("#productComment").focus();
		return false;
	}
	
});


	$("#edit").on("click", function(){
		
		$("#checkForm").attr("action", "updateForm")
		$("#checkForm").attr("method", "post")
		
		$("#checkForm").submit();
	})
	
	$("#delete").on("click", function(){
	    var productName = $("#pdName").val();
	    var result = confirm(productName + " 상품을 삭제하시겠습니까?");
	    
	    if (result) {
	        $("#checkForm").attr("action", "deleteProcess");
	        $("#checkForm").attr("method", "post");
	        $("#checkForm").submit();
	    }
	})
});
