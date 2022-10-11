<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 업로드 하기</title>

</head>
<body>
<h1>파일 업로드 하기</h1>
<form method="post"
      action="${pageContext.request.contextPath}/upload-result" enctype="multipart/form-data">
    <input type="button" value="파일 추가" onClick="addFile()"><br>

    <div id="d_file">

    </div>
    <input type="submit" value="업로드">
</form>
</body>

<script src = "http://code.jquery.com/jquery-latest.js"></script>
<script>
    // var cnt = 1;
    function addFile(){
        $("#d_file").append("<br>" + "<input type='file' name='uploadFile' accept='image/*'/>");
    }
</script>

</html>
