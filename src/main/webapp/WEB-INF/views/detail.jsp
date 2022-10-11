<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>연락처 상세</title>
</head>
<body>
<h1>${ data.m_name }</h1>
<img src="${data.m_profile}" style="width:100px; height:100px;"/>
<p>전화번호 : ${data.m_phoneno } </p>
<p>주소 : ${data.m_addr } </p>
<p>이메일 : ${data.m_email }</p>

${data.m_profile}

<p> <button type="button" onclick="location.href='/update?m_id=${id }' ">수정</button>
    <button type="button" onclick="location.href='/delete?m_id=${id }' ">삭제</button>
    <button type="button" onclick="location.href='/list' ">목록으로</button>

<p>
</p>
</body>
</html>
