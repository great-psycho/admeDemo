$(document).ready(function(){
    getSignList();
});

function getSignList(){
    let cookie = getCookie("TokenCookie");
    let cookiebrow = document.cookie;
    console.log("cookiebrow = "+cookiebrow);
    console.log("cookiebrow Token = "+ cookiebrow);


    $.ajax({
        type:"GET",
        url:'/sign-api/cookie',
        data:{},
        headers: {"X-AUTH-TOKEN": cookie},
        success: function (response) {
            for (let i=0; i<response.length; i++){
                console.log(response);
            }
        }
    })
}

function getCookie(key){
    let cookieKey = key +"=";
    let result = "";
    let cookieArr = document.cookie.split(";");

    for (let i=0; i<cookieArr.length; i++){
        if (cookieArr[i][0] === " "){
            cookieArr[i] = cookieArr[i].substring(1);
        }
        if(cookieArr[i].indexOf(cookieKey) === 0) {
            result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
            return result;
        }
    }
    return result;
}

function logOut(){
    location.href="/user/logout";
}

function testPage(){
    location.href="/test";
}