<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>list JSP</title>
</head>
<body>
<div id="content">
    <h3>연락처 목록</h3>
    <p>
    <form>
        <input type="text" placeholder="검색" name="keyword" value="${keyword}" />
        <input type="submit" value="검색" />
        <input type="hidden" name="id" value="10" />
</form>
    </p>
    <!-- CSS 파일을 변경 후에 바로 Refresh가 안된다면 common.css 파일로 들어가서 바로 Refresh 해주면 적용 된다. -->
    <table>
        <tr>
            <th class='w-px150'>이름</th>
            <th class='w-px200'>전화번호</th>
            <th class='w-px200'>주소</th>
            <th class='w-px200'>이메일</th>
        </tr>
        <!-- for(꺼낸 배열 변수를 담을 새로운 변수 (String x) : 배열 변수(list)) -->
        <!-- items : 배열 변수 -->
        <!-- var : 꺼낸 배열 변수를 담을 새로운 변수 -->
        <core:forEach items="${data }" var="row">
            <tr>
                <td><a href='/detail?m_id=${row.m_id }'>${row.m_name }</a>
                </td>
                <td>${row.m_phoneno }</td>
                <td>${row.m_addr }</td>
                <td>${row.m_email }</td>
                <td><form method = "get"><input type="hidden" name="id" value="${row.m_id }"  />
                    <input type="submit" value="수정" onclick="action='/vo_update?m_id=${row.m_id }'">
                </form></td>
                <td>

            </tr>
        </core:forEach>
    </table>

    <button type="button" onclick="location.href='create'">새 연락처 등록</button>
</div>
</body>
</html>