<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var count = 1;
						$("#append")
								.click(
										function() {
											if (count >= 10) {
												alert("최대 업로드수는 " + count
														+ "개입니다.");
											} else {
												count++;
												$("#fileArea")
														.append(
																"<input type='file' name='userfile"+count+"' id='" + count + "' />");

											}
										});
						$("#delete").click(function() {
							if (count <= 1) {
								alert("최소 업로드수는 " + count + "개입니다.");
							} else {
								$("#userfile" + count).remove();
								count--;
							}
						});
						$("#fileArea").on("click", "input[name^='userfile']",
								function(e) {

									// 						}
									// 						$("input[name^='userfile']").on("click", function(e) {

									console.log($(this).attr("id"));
								});

					});
</script>

</head>
<body>
<body>
	<button id="append">Append</button>
	<button id="delete">Delete</button>
	<form action="/notice/file/write" enctype="multipart/form-data"
		method="post">
		<div id="fileArea">
			<input type="file" name="userfile" id="1" />
		</div>
		<input type="submit" value=" 파일전송 ">
	</form>
</body>

</body>



</html>