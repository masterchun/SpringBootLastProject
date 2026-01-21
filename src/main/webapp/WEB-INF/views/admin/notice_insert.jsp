<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center">공지사항</td>
		</tr>
	</table>
	<form action="/admin/notice_insert_ok" method="post"
		enctype="multipart/form-data">
		<table class="table">
			<tbody>
				<tr>
					<th width="15%" class="success">공지 종류</th>
					<td width="85%"><select name="type" class="input-sm">
							<option>일반 공지</option>
							<option>이벤트 공지</option>
							<option>여행 공지</option>
							<option>날씨 공지</option>
							<option>추천 공지</option>
					</select></td>
				</tr>
				<tr>
					<th width="15%" class="success">제목</th>
					<td width="85%"><input type="text" name="subject" size="60"
						class="input-sm" required></td>
				</tr>
				<tr>
					<th width="15%" class="success">내용</th>
					<td width="85%"><textarea rows="10" cols="60" name="content" required></textarea>
					</td>
				</tr>
				<tr>
					<th width="15%" class="success">첨부파일</th>
					<td width="85%"><input type="file" name="files" size="20"
						class="input-sm" accept="image/*" multiple></td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<button type="submit" class="btn-sm btn-warning">등록</button>
						<button type="button" class="btn-sm btn-warning"
							onclick="javascript:history.back()">취소</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>