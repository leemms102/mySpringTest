<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>업로드 완료</html>


<div class="result-images">
    <c:forEach var="file" items="${map.fileList }">
      <p><img src = ${file}>
          ${file}
        </p><br>
    </c:forEach>

</div>