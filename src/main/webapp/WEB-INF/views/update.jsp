<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <title>연락처 수정</title>
</head>
<body>
<h1>${data.m_name}</h1>
<form method="POST" enctype="multipart/form-data">
    <p>이름 : <input type="text" name="name" value="${data.m_name }"/> </p>
    <p>전화번호 : <input type="text" name="phoneno" value="${data.m_phoneno }"/> </p>
    <p>주소 : <input type="text" name="addr" value="${data.m_addr }"/> </p>
    <p>이메일 : <input type="text" name="email" value="${data.m_email }"/> </p>
    <p><div class="card">
        <label>프로필 변경</label>--%>
            <img src = "${data.m_profile}" id="file">
            <input type="file" accept="image/*" name="uploadFile" id ="uploadFile" onchange="imageView(this)">
            </div>
    </div>

    <div>
        <p>저장경로 <%=request.getSession().getServletContext().getRealPath("/")%></p>
    </div>
    </p>
    <p><input type="submit" value="저장" /> </p>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
    const maxSize = 1048576 * 2; //2MB

    function imageView(input) {
        var formData = new FormData();

        if (input.files && input.files[0]) {
            var reader = new FileReader();
            if (input.files[0].size > maxSize) {
                alert("파일 사이즈는 2MB를 넘을 수 없습니다");
                $(input).val('');
                return false;
            } else {
                reader.onload = function (e) {
                    $('#file').attr('src', e.target.result)

                    console.log(e.target);
                }

                reader.readAsDataURL(input.files[0]);
                formData.append("uploadFile", input.files[0]);
            }
        }
    }
</script>
</body>
</html>

