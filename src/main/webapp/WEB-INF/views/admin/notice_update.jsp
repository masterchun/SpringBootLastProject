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
			<td class="text-center">공지 수정</td>
		</tr>
	</table>
	<form action="/admin/notice_update_ok" method="post">
		<table class="table">
			<tbody>
				<tr>
					<th width="15%" class="success">공지 종류</th>
					<td width="85%"><select name="type" class="input-sm">
							<option ${vo.type=='일반 공지'?'selected':'' }>일반 공지</option>
							<option${vo.type=='이벤트 공지'?'selected':'' }>이벤트 공지</option>
							<option${vo.type=='여행 공지'?'selected':'' }>여행 공지</option>
							<option${vo.type=='날씨 공지'?'selected':'' }>날씨 공지</option>
							<option${vo.type=='추천 공지'?'selected':'' }>추천 공지</option>
					</select></td>
				</tr>
				<tr>
					<th width="15%" class="success">제목</th>
					<td width="85%"><input type="text" name="subject" size="60"
						class="input-sm" value="${vo.subject }" required></td>
				</tr>
				<tr>
					<th width="15%" class="success">내용</th>
					<td width="85%"><textarea rows="10" cols="60" name="content" required>${vo.content }</textarea>
					</td>
				</tr>
				<tr>
					<td class="text-center" colspan="2">
						<button type="submit" class="btn-sm btn-warning">수정</button>
						<button type="button" class="btn-sm btn-warning"
							onclick="javascript:history.back()">취소</button>
						<input type="hidden" name="no" value="${vo.no }">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>