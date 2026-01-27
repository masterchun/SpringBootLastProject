<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="toast-container text-center">
		<div id="reserveToast" class="toast">
			<div class="toast-header bg-success text-white">
				<strong class="me-auto">알림</strong>
				<button class="btn-close" onclick="hideToast()">x</button>
			</div>
			<div class="toast-body" id="toastMsg"></div>
		</div>
	</div>
</body>
</html>