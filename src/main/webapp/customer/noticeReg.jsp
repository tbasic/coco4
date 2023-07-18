<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/nstyle.css" />
<script>
function cancelurl() {
	location.href="./notice.do"
}

</script>
</head>
<body>
<h3>noticeReg.jsp</h3>

<form action="noticeRegProc.do" method="post" enctype="multipart/form-data">
	<table class="twidth">
	<colgroup>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col width="35%" />
	</colgroup>
	<caption>Write</caption>
	<tbody>
	<tr>
		<th class="left">제목</th>
		<td colspan="3">
			<input type="text" class="inp" name="title" />
		</td>	
	</tr>
	<tr>
		<th class="left">내용</th>
		<td colspan="3" id="content">
			<textarea name="content" class="txt"></textarea>
		</td>	
	</tr>
	
	<tr>
		<th class="left">첨부</th>
		<td colspan="3" id="addfile">
			<input type="file" name="file" size="50" class="inp" />
		</td>	
	</tr>
	</tbody>
</table>
<input type="hidden" name="uid" value="${uid }" />
<input type="submit" value="save" />
<input type="button" value="cancel" class="cancel" onclick="cancelurl()" />
</form>

</body>
</html>