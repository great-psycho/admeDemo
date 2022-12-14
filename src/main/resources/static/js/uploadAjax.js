$(document).ready(function() {
    console.log("시작")
    $('#uploadBtn').click(function(){
        console.log("업로드버튼 눌림")
        saveFile()
    });

    // // 생성된 사진 클릭시 이미지 닫힘
    // $('.bigPictureWrapper').click(function(){
    //     $(".bigPicture").animate({width : '0%', height : '0%'}, 1000);
    //     setTimeout(() => {
    //         $(this).hide();
    //     }, 1000);
    // });
    //
    //
    // $(".uploadResult").click(function (){
    //     let targetFile = $(this).data("file");
    //     let type = $(this).data("type");
    //     console.log(targetFile);
    //
    //     $.ajax({
    //         url : '/deleteFile',
    //         data : {fileName : targetFile, type : type},
    //         dataType : 'text',
    //         type : 'POST',
    //         success : function(result){
    //             alert(result);
    //         }
    //     }); // $ajax
    // });
});

function saveFile() {
    // 업로드 후 초기화용 변수
    let cloneObj = $(".uploadDiv").clone();

    // formData = 가상의 <form> 태그라 생각하면 된다.
    let formData = new FormData();
    let inputFile = $("input[name='uploadFile']");
    let files = inputFile[0].files;

    // formData에 값들을 for문으로 입력해서 담아준다.
    for (let i=0; i<files.length; i++){
        // 파일 확장자, 크기에 대한 예외처리
        if(!checkExtension(files[i].name, files[i].size)){
            return false;
        }
        formData.append("uploadFile", files[i]);
    }

    //ajax 를 통해 controller 와 연결
    $.ajax({
        type: "POST",
        url: '/10s/videos',
        data: formData,
        dataType: "json",
        // dataType: "multipart/form-data",
        contentType: false,
        async: false,
        processData: false,
        success: function(result) {
            console.log(result);
            // 업로드 이미지 처리
            showUploadedFile(result);
            // input 초기화
            $(".uploadDiv").html(cloneObj.html());
        }
    });
}

// 파일 확장자, 크기 처리
function checkExtension(fileName, fileSize){
    let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    let maxSize = 5242880; //5MB

    if(fileSize >= maxSize){
        alert("파일 사이즈 초과");
        return false;
    }

    if(regex.test(fileName)){
        alert("해당 종류의 파일은 업로드할 수 없습니다.");
        return false;
    }
    return true;
}

function showUploadedFile(uploadResultArr){
    let str = "";
    $(uploadResultArr).each(function(i, obj){
        if(!obj.image){
            let fileCallPath = encodeURIComponent(obj.uploadPath + obj.fileName);
            console.log("111" + fileCallPath)
            str += "<li><a href='/download?fileName="+ fileCallPath +"'>" + "<img src='/display?fileName=" + fileCallPath + "'>" + obj.fileName + "</a>" + "<span data-file=\'" + fileCallPath + "\'data-type='file'>x</span>" + "<div></li>"
        }
        else{
            let fileCallPath = encodeURIComponent(obj.uploadPath + obj.fileName);
            // let originPath = encodeURIComponent(obj.uploadPath + obj.uuid + "_" + obj.fileNae)
            console.log("fileName: " + obj.fileName)
            console.log("222" + fileCallPath)
            str += "<li><a href=\"javascript:showImage(\'" + fileCallPath + "\')\"><img src='/display?fileName=" + fileCallPath + "'></a>" + "<span data-file=\'" + fileCallPath + "\'data-type='image'>x</span></li>";
        }
    });

    $(".uploadResult > ul").append(str);

    alert("동작 완료")
}

// // 원본 이미지 보여주기
// function showImage(fileCallPath){
//     $(".bigPictureWrapper").css("display", "flex").show();
//
//     $(".bigPicture")
//         .html("<img src = '/display?fileName=" + encodeURI(fileCallPath)+"'>")
//         .animate({width:'100%', height:'100%'}, 1000);
// }