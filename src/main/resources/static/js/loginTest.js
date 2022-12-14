
function signUp1(){
    let nickname = $("#signUpNickname").val();
    let password = $("#signUpPassword").val();
    let username = $("#username").val();
    let userRole = $("#userRoleCheck").prop('checked');
    console.log("check : "+userRole)
    let adminToken = $("#adminToken").val();

    let data = {"uid":nickname, "password":password, "name":username, "admin":userRole, "adminToken":adminToken}

    //ajax 를 통해 controller 와 연결
    $.ajax({
        type: "POST",
        url: '/sign-api/sign-up',
        data: JSON.stringify(data),
        dataType: "json",
        contentType: 'application/json',
        false: function (response){
            console.log(response);
            alert("아이디, 비밀번호를 확인해주세요.")
        },
        success: function (response) {
            console.log(response);
            alert("회원가입 완료");
        }
    });
}

function signIn1(){
    let nickname = $("#signInNickname").val();
    let password = $("#signInPassword").val();

    let data = {"uid":nickname, "password":password}

    //ajax 를 통해 controller 와 연결
    $.ajax({
        type: "POST",
        url: '/sign-api/sign-in',
        data: JSON.stringify(data),
        dataType: "json",
        // async: false,
        contentType: 'application/json',
        success: function (response) {
            console.log(response);
            alert("로그인 완료");
            location.href="/signOn";
            // location.href="/test";
        }
    });
}