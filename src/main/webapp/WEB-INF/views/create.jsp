<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
<head>
    <title>새 연락처</title>
</head>
<body>
<h1>새 연락처 등록하기</h1>
<form method="POST" enctype="multipart/form-data">
    <p>이름 : <input type="text" name="name" /> </p>
    <p>전화번호 : <input type="text" name="phoneno" /> </p>
    <p>주소 : <input type="text" name="addr" /> </p>
    <p>이메일 : <input type="text" name="email" /> </p>
    <p>성별 : <input type="text" name="gender" /> </p>
    <div class="card">
        <label>프로필 </label>--%>
        <input type="file" accept="image/*" name="uploadFile" id="uploadFile" onchange="imageView(this)">
        <div class="select_image">
            <div style="width:100px; height:100px;"><img id = 'tempImage'/>
                <input type="hidden" name="profile" value="${data.m_profile}" />
            </div>
        </div>
        <div>
            <p>저장경로 <%=request.getSession().getServletContext().getRealPath("/")%></p>
        </div>
    </div>


    <input type="submit" value="저장" /> </p>
    <input type="submit" value="HttpServletRequest" onclick="action='createServletRequest'" />
    <input type="submit" value="@RequestParam" onclick="action='createRequestParam'" />
    <!-- onclick 이벤트로 액션 속성을 바꿔준다. -->
    <input type="submit" value="Data Object"  onclick="action='createDataObject'" />
    <input type="submit" value="@PathVariable" onclick="go_submit( this.form )" />
    <button type="button" onclick="location.href='/list' ">목록으로</button>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
    function go_submit(f) {
        f.action = 'createPathVariable/' + f.name.value
            + '/' + f.phoneno.value
            + '/' + f.addr.value
            + '/' + f.email.value;
    }

    const maxSize = 1048576 * 2; //2MB

    function imageView(input) {
        var formData = new FormData();

        if (input.files && input.files[0]) {
            var reader = new FileReader();
            if(input.files[0].size > maxSize){
                alert("파일 사이즈는 2MB를 넘을 수 없습니다");
                $(input).val('');
                return false;
            }
            else {
                reader.onload = function (e) {
                    $('#tempImage').attr('src', e.target.result)
                        .width(100)
                        .height(100);

                    console.log(e.target);
                }

                reader.readAsDataURL(input.files[0]);
                formData.append("uploadFile", input.files[0]);


                // $.ajax({
                //     url: '/uploadAjaxAction',
                //     processData : false,
                //     contentType : false,
                //     data : formData,
                //     type : 'POST',
                //     dataType : 'json'
                // }); // end ajax
            }

            //var filePath=$(input).val();
            var filePath = input.value;
            const selectedFile = [...input.files];

            console.log(input.files[0].name);
            console.log(filePath);
            console.log(input.files[0].size);
            console.log(selectedFile);

        }
    }

</script>
</body>
</html>