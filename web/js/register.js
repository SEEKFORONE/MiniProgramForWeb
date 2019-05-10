var result = '';   //返回的结果变量
for (var i=0; i<6; i++) //根据指定的长度
{
    result += Math.floor(Math.random()*10);
}

var register = document.getElementById("register");
register.onclick = function (ev) {
    var name = document.getElementById("reg-username").value;
    var password  =document.getElementById("reg-password").value;
    var check = document.getElementById("check-password").value;
    var email  =document.getElementById("reg-email").value;
    var regVerify = document.getElementById("reg-verify").value;
    var str = "";

    if(name == null || name === ""){
        str += "用户名不能为空";
    }else {
        if(password == null|| password === ""){
            str += "密码不能为空";
        } else {
            if(!isNaN(password)){
                str += "密码不能为纯数字";
            }else {
                if(password.length <6){
                    str += "密码长度不能小于6";
                }else {
                    if(password !== check){
                        str += "两次密码输入不同";
                    }else {
                        if(email == null || email === ""){
                            str += "邮箱不能为空";
                        }else {
                            var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
                            if(!myReg.test(email)){
                                str += "邮箱格式不正确";
                            }else {
                                if(regVerify == null || regVerify === ""){
                                    str += "验证码不能为空";
                                }else {
                                    if(regVerify!==result){
                                        str += "验证码不正确";
                                    }else {
                                        var hashPassword =hex_md5(password);
                                        var path = "../register?name=" +name + "&&password=" + hashPassword;
                                        window.location.href = path;
                                    }
                                }


                            }
                        }
                    }
                }
            }
        }
    }
    if(str !== ""){
        alert(str);
    }
};


// 发送验证码倒计时60s
$(function() {

    var btn = $("#get-verify");
    $(function() {

        btn.click(function (ev) {
            var email  =document.getElementById("reg-email").value;
            if(email == null || email === ""){
                alert("邮箱不能为空");
            }else {
                var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
                if(!myReg.test(email)){
                    alert("邮箱格式不正确") ;
                }else {
                    settime();

                    var path1 = "../email?email="+email+"&verify=" + result;
                    window.open(path1)

                }
            }

        });
    });
    var countdown = 60;//倒计时总时间，为了演示效果，设为5秒，一般都是60s
    function settime() {
        if (countdown == 0) {
            btn.attr("disabled", false);
            btn.html("获取验证码");
            btn.removeClass("disabled");
            countdown = 5;
            return;
        } else {
            btn.addClass("disabled");
            btn.attr("disabled", true);
            btn.html("重新发送(" + countdown + ")");
            countdown--;
        }
        setTimeout(settime, 1000);
    }

});