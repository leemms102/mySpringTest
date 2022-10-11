<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <title>연락처 수정</title>
</head>
<body>
<h1>${vo.name}</h1>
<form method="POST">

    <p>아이디 : <input type="text" name="id" value="${vo.id }"/> </p>
    <p>이름 : <input type="text" name="name" value="${vo.name }"/> </p>
    <p>전화번호 : <input type="text" name="phoneno" value="${vo.phoneno }"/> </p>
    <p>주소 : <input type="text" name="addr" value="${vo.addr }"/> </p>
    <p>이메일 : <input type="text" name="email" value="${vo.email }"/> </p>
    <input type="submit" value="저장" />
    <input type="submit" value="삭제" onclick="action='/vo_delete?m_id=${vo.id }'">
</form>
</body>
</html>