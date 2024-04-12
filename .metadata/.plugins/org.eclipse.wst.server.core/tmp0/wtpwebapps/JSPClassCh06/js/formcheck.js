document.addEventListener("DOMContentLoaded", function() {
	
	const writeForm = document.querySelector("#writeForm");
		writeForm.addEventListener("submit", (e) => {
	// alert("writeForm submit");
	// 판단 - 서버로 전송해도 되는지 판단
		const writer = document.querySelector("#writer").value;
		const title = document.querySelector("#title").value;
		const pass = document.querySelector("#pass").value;
		const content = document.querySelector("#content").value;

		if(writer.trim().length <= 0) {
			alert("please put writer!");
			e.preventDefault();
		}
		
		if(title.trim().length <= 0) {
			alert("please put title!");
			e.preventDefault();
		}
		if(pass.trim().length <= 0) {
			alert("please put password!");
			e.preventDefault();
		}
		if(content.trim().length <= 0) {
			alert("please put content!");
			e.preventDefault();
		}
	
	});
	
});

