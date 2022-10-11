<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <title>ajax를 이용한 파일 업로드 예제</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>파일업로드</h2>
    <form name="dataForm" id="dataForm" enctype="multipart/form-data" onsubmit="return registerAction()">
<%--        <button id="btn-upload" type="button" style="border: 1px solid #ddd; outline: none;">파일 추가</button>--%>
        <input id="input_file" accept="image/*" multiple="multiple" type="file">
        <span style="font-size:10px; color: gray;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
        <div class="data_file_txt" id="data_file_txt" style="margin:40px;">
            <span>첨부 파일</span>
            <br />
            <div id="imgList">
            </div>
        </div>
        <button type="submit" style="border: 1px solid #ddd; outline: none;">전송하기</button>

      <div id="resultDiv">
                <p h:text="${log}"></p>
            </div>
    </form>
</div>


<!-- 파일 업로드 스크립트 -->
<script>
    $(document).ready(function()
        // input file 파일 첨부시 fileCheck 함수 실행
    {
        $("#input_file").on("change", fileCheck);
    });

    /**첨부파일로직 **/
    // $(function () {
    //     $('#btn-upload').click(function (e) {
    //         e.preventDefault();
    //         $('#input_file').click();
    //     });
    // });

    // 파일 현재 필드 숫자 totalCount랑 비교값
    var fileCount = 0;
    // 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
    var totalCount = 10;
    // 파일 고유넘버
    var fileNum = 0;
    // 첨부파일 배열
    var content_files = new Array();

    function fileCheck(e) {
        var files = e.target.files;

        // 파일 배열 담기
        var filesArr = Array.prototype.slice.call(files);

        // 파일 개수 확인 및 제한
        if (fileCount + filesArr.length > totalCount) {
            alert('파일은 최대 '+totalCount+'개까지 업로드 할 수 있습니다.');
            return;
        } else {
            fileCount = fileCount + filesArr.length;
        }

        // 각각의 파일 배열담기 및 기타
        filesArr.forEach(function (f) {
            var reader = new FileReader();
            reader.onload = function (e) {
                content_files.push(f);

                $('#imgList').append(
                    '<div id="file' + fileNum + '">'
                    + '<img src="' + e.target.result + '">'
                    + '<font style="font-size:12px">' + f.name + '</font>'
                    + '<img src="/upload/delete.jpg" style="width:25px; height:auto; vertical-align: middle; cursor: pointer;" onclick="fileDelete(\'file' + fileNum + '\')"/>'
                    + '<div/>'
                );
                fileNum ++;
            };
            reader.readAsDataURL(f);
        });
        console.log(content_files);
        //초기화 한다.
        $("#input_file").val("");
    }

    // 파일 부분 삭제 함수
    function fileDelete(fileNum){
        var no = fileNum.replace(/[^0-9]/g, "");
        content_files[no].is_delete = true;
        $('#' + fileNum).remove();
        fileCount --;
        console.log(content_files);
    }

    // form submit 로직
    function registerAction(){

        var form = $("form")[0];
        var formData = new FormData(form);
        for (var x = 0; x < content_files.length; x++) {
            // 삭제 안한것만 담아 준다.
            if(!content_files[x].is_delete){
                formData.append("uploadFile", content_files[x]);
            }
        }
        // 파일업로드 multiple ajax 처리
        $.ajax({
            type: "POST",
            url: "/ajax-upload-result",
            data: formData,
            // dataType : "json",       // datatype이란 request로 들어오는 datatype이 아니라 서버에서 response로 넘어오는 datatype
            processData: false,
            contentType: false,
            success: function (data) {
              //  if(JSON.parse(data)['result'] == "OK"){
                    alert("업로드 성공");
                    console.log(data);
             //   } else
               //     alert("잠시 후 다시 시도해주세요");
            },
            error: function (request, error) {
                alert("잠시 후 다시 시도해주시기 바랍니다.");
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                return false;
            }
        });
        return false;
    }
</script>
</body>
</html>