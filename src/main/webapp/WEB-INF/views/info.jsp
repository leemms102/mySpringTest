<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>info JSP</title>
</head>
<body>
<h3>등록 결과 - ${method }</h3>

성명 : ${name }<br />
전화번호 : ${phoneno }<br />
주소 : ${email }<br />
이메일 : ${addr }<br /><br>
성별 : ${gender }<br /><br>
<hr>
성명 : ${vo.name }<br />
전화번호 : ${vo.phoneno }<br />
주소 : ${vo.email }<br />
이메일 : ${vo.addr }<br />
아이디: ${vo.id }<br>
<a href="create">등록 화면으로</a>
</body>
</html>