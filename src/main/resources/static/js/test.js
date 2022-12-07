function saveArticle2() {
    // 업로드 후 초기화용 변수
    let cloneObj = $(".upload-box").clone();

    let data = {
        "title" : $(".upload-title-content").val(),
        "content" : $(".upload-content-content").val()
    }

    let video = $("input[name='uploadFile']"); // input 타입중 name이 'uploadFile'인 것 jquery 로 표현
    let videoFile = video[0].files;

    let formData = new FormData();

    formData.append("sideData", new Blob([JSON.stringify(data)], {type: "application/json"}))

    checkExtension(videoFile.size);
    formData.append("videoFile", videoFile[0]);

    //ajax 를 통해 controller 와 연결
    $.ajax({
        type: "POST",
        url: '/tenseconds/test',
        contentType: false,
        processData: false,
        data: formData,
        success: function (result) {
            console.log(result);
            $(".upload-box").html(cloneObj.html());
            alert("동작 완료");

            window.location.reload();
        }
    });
}

// 파일 확장자, 크기 처리
function checkExtension(fileSize){
    let maxSize = 256000000;

    if(fileSize >= maxSize){
        alert("파일 사이즈 초과");
        return false;
    }
    return true;
}

// //파일 저장
// function saveArticle() {
//     // 업로드 후 초기화용 변수
//     let cloneObj = $(".upload-box").clone();
//
//     // formData = 가상의 <form> 태그라 생각하면 된다.
//     let formData = new FormData();
//     let inputFile = $("input[name='uploadFile']");
//     let files = inputFile[0].files;
//
//     // formData에 값들을 for문으로 입력해서 담아준다.
//     for (let i=0; i<files.length; i++){
//         // 파일 확장자, 크기에 대한 예외처리
//         if(!checkExtension(files[i].name, files[i].size)){
//             return false;
//         }
//         formData.append("uploadFile", files[i]);
//     }
//
//     //ajax 를 통해 controller 와 연결
//     $.ajax({
//         type: "POST",
//         url: '/tenseconds/videos',
//         data: formData,
//         dataType: "json",
//         contentType: false,
//         async: false,
//         processData: false,
//         success: function (result) {
//             console.log(result);
//             // // 업로드 이미지 처리
//             // showUploadedFile(result);
//             // input 초기화
//             $(".upload-box").html(cloneObj.html());
//             alert("동작 완료");
//             window.location.reload();
//         }
//     });
// }
// // 파일 확장자, 크기 처리
// function checkExtension(fileSize){
//     // let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
//     // let regex = new RegExp("(.*?)\.(mp4)$");
//     let maxSize = 256000000;
//
//     if(fileSize >= maxSize){
//         alert("파일 사이즈 초과");
//         return false;
//     }
//
//     // if(!regex.test(fileName)){
//     //     alert("파일이 mp4형식인지 확인 해주세요.");
//     //     return false;
//     // }
//     return true;
// }