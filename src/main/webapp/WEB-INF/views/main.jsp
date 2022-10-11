<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>test page</title>
</head>
<body>
    JSP 페이지입니다
    <%= request.getAttribute("testvalue")%>
</body>
</html>
