$(document).ready(function(){
    $(".container").fadeIn(1000);
    $(".menu").fadeIn(1000);
    $(".paging-button").fadeIn(1000);
    $(".upload-button").fadeIn(1000);
    getList();
    item_click();
});

function saveArticle() {
    console.log("[saveArticle] '게시물 등록'")
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
        url: '/tenseconds/video',
        data: formData,
        contentType: false,
        processData: false,
        success: function (response) {
            $(".upload-box").html(cloneObj.html());
            console.log("[saveArticle] '게시물 등록 SUCCESS!'")
            alert("게시물 등록 완료");

            window.location.reload();
        }
    });
}


function getList() {
    console.log("[getList] '게시물 조회'")

    $.ajax({
        type: "GET",
        url: '/tenseconds/list',
        data:{},
        success: function (response) {
            console.log("[getList] '게시물 조회 SUCCESS!'")
            console.log(response);
            for (let i=0; i<response.length; i++) {
                let num = response.length-i;
                videoListPost(response[i], num);
            }
        }
    })
}

function videoListPost(article, index) {
    let tempHtml = `<div onclick="item_click()" class="item" id="${index}">
                        <img class="thumbnail" src="${"files/" + "thumb_" + article["uuid"] +".jpg"}" alt="thumbnail">
                    </div>
                    `;
    $(".items").append(tempHtml);
}

// 크기 처리
function checkExtension(fileSize){
    let maxSize = 256000000;

    if(fileSize >= maxSize){
        alert("파일 사이즈 초과");
        return false;
    }
    return true;
}



function item_click(){
    $(".item").click(function () {
        // alert("모달 클릭 확인");
        $(".modal").show();
        close_click();
        // background_click();
    });
}

function close_click(){
    $(".close").click(function (){
       $("#myModal").hide();
    });
}

var modal = document.getElementById("myModal");

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

function upload() {
    $(".container").hide();
    $(".upload-button").hide();
    $(".paging-button").hide();
    $(".uc1").fadeIn(300);
    $(".upload").show();

    // dropdown
    $("#default-public-button").show();
    $("#default-private-button").hide();
    $("#public-button").hide();
    $("#private-button").hide();
}

// dropdown
function choice_button() {
    $("#default-public-button").hide();
    $("#default-private-button").hide();
    $("#public-button").show();
    $("#private-button").show();
}
function public_check() {
    $("#default-public-button").show();
    $("#default-private-button").hide();
    $("#public-button").hide();
    $("#private-button").hide();
}
function private_check() {
    $("#default-public-button").hide();
    $("#default-private-button").show();
    $("#public-button").hide();
    $("#private-button").hide();
}