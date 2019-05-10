var login = document.getElementById("login_in");
login.onclick = function (ev) {
    var name = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var str = "";
    if(name == null || name === ""){
        str += "用户名不能为空";
    }else {
        if(password == null|| password === ""){
            str += "密码不能为空";
        } else {
            var hashPassword =hex_md5(password);
            var path = "../login?name=" +name + "&&password=" + hashPassword;
            window.location.href = path;
            }
    }
    if(str !== ""){
        alert(str);
    }
};