<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>📕 Challenge BLOG 📕</title>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/formCheck.js"></script>
</head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
	integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<body>
	<div class="container">
		<%@ include file="../page/offcanvas_teamHeader.jsp"%>
		<%@ include file="../page/header.jsp"%>
		<div class="row">
			<%@ include file="../page/leftSide.jsp"%>
			<div class="col-9">
				<!-- 캐러셀 -->
				<div id="carouselExampleAutoplaying" class="carousel slide mb-3"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="./img/1.png" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/2.png" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/3.png" class="d-block w-100" alt=""
								height="200px">
						</div>
						<div class="carousel-item">
							<img src="./img/4.png" class="d-block w-100" alt=""
								height="200px">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>

				<form name="postDetailCheck" id="postDetailCheck">
					<input type="hidden" name="detailPostNo" id="detailPostNo"
						value="${ post.postNo }" /> <input type="hidden"
						name="detailPostPageNum" id="detailPostPageNum"
						value="${ pageNum }" />
					<c:if test="${ searchOption }">
						<input type="hidden" name="type" value="${ type }" />
						<input type="hidden" name="keyword" value="${ keyword }" />
					</c:if>
				</form>

				<div class="row">
					<div class="col-1"></div>
					<div class="col-11">
						<h3>Category</h3>
					</div>
				</div>

				<div class="row">
					<div class="col-1"></div>
					<div class="col-5">
						<h1>${ post.postTitle }</h1>
					</div>
					<div class="col-5 text-end">
						<!-- 삭제아이콘 -->
						<c:if test="${ check }">
							<a type="button" id="detailDelete"><i
								class="fa-solid fa-trash fa-2xl text-danger"></i></a>
						</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<!-- 수정아이콘 -->
						<c:if test="${ check }">
							<a id="detailUpdate" type="button"><i
								class="fa-solid fa-pen-to-square fa-2xl" id="detailUpdate"></i></a>
						</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp; <a type="button" href="#"
							onclick="clip(); return false;"
							class="link-dark text-decoration-none"> <i
							class="fa-solid fa-share-nodes fa-2xl"></i></a>
					</div>
					<div class="col-1"></div>
				</div>

				<hr style="width: 802px; margin: 0 auto;">

				<div class="row">
					<div class="col-1"></div>
					<div class="col-10">
						<c:if test="${ empty post.postFile }">
							<i class="fa-solid fa-floppy-disk"></i> Not Found File</c:if>

						<c:if test="${ not empty post.postFile }">
							<a href="upload/${ post.postFile }"><i
								class="fa-solid fa-floppy-disk"></i> ${ post.postFile }</a>
						</c:if>
					</div>
					<div class="col-1"></div>
				</div>

				<div class="row">
					<div class="col-1"></div>
					<div class="col-10 my-5" style="height: 500px;">${ post.postContent }</div>
					<div class="col-1"></div>
				</div>

				<div class="row justify-content-center">
					<div class="col-10 text-center  my-2">
						<c:if test="${ not searchOption }">
							<input class="btn btn-danger" type="button" value="List"
								onclick="location.href='postMain?pageNum=${ pageNum }'" style="width:100px" />
						</c:if>
						<c:if test="${ searchOption }">
							<input class="btn btn-dnager" type="button" value="List"
								onclick="location.href='postMain?pageNum=${ pageNum }&type=${ type }&keyword=${ keyword }'"  style="width:100px"/>
						</c:if>
					</div>
				</div>


				<hr style="width: 802px; margin: 0 auto;">

				<div class="row my-3">
					<div class="col-1"></div>
					<div class="col-10">
						<i class="fa-solid fa-thumbs-up"></i> 좋아요 : ${ post.like1 }
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i class="fa-solid fa-eye"></i> 조회수
						: ${ post.postView }
					</div>
					<div class="col-1"></div>
				</div>

				<hr style="width: 802px; margin: 0 auto;">


				<div class="row my-3">
					<div class="col-1"></div>
					<div class="col-10">
						<h4>Comment</h4>
					</div>
					<div class="col-1"></div>
				</div>
				<div class="row my-1">
					<div class="col-2"></div>
					<div class="col-8">
						<pre>ㄴ ADMIN : LOL!!!!!</pre>
					</div>
					<div class="col-2"></div>
				</div>
				<!-- Footer -->
				<hr>
				<%@ include file="../page/footer.jsp"%>
			</div>
		</div>
	</div>
	<script src="bootstrap/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		function clip() {

			var url = '';
			var textarea = document.createElement("textarea");
			document.body.appendChild(textarea);
			url = window.document.location.href;
			textarea.value = url;
			textarea.select();
			document.execCommand("copy");
			document.body.removeChild(textarea);
			alert("URL has been copied.")
		}
	</script>

</body>
</html>