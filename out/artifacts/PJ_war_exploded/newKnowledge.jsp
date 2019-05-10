<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="domain.*" %><%--
  Created by IntelliJ IDEA.
  User: LHY
  Date: 2018/8/3
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = ((String)session.getAttribute("error") == null) ? "" : (String)session.getAttribute("error");
    user user= new user();
    String choose = (String)session.getAttribute("choose");
    course course = (course)session.getAttribute("course");
    int unitID = Integer.parseInt(request.getParameter("unitID"));
    if(session.getAttribute("user") != null){
        user = (user)session.getAttribute("user");
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>知识点创建</title>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    if(!error.isEmpty()){
%>
<script>
    alert("<%=error%>");
</script>
<%
        session.removeAttribute("error");
    }
%>
<header>
    <div id="sign">
        <%if(session.getAttribute("user") == null){%>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#registerModal"><h1>注册</h1></a>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#loginModal"><h1>登录</h1></a>
        <%}else {  user= (domain.user)session.getAttribute("user");  %>
        <a href="myspace?state=1" class="col-lg-2" ><h1><%=user.getUsername()%></h1></a>
        <a href="out" class="col-lg-2"><h1>登出</h1></a>
        <%}%>
    </div>
    <form action="sbn" method="post">
        <div class="container">
            <div class="row">
                <a href="home" class="col-lg-2"><h1>首页</h1></a>
                <input class="form-control col-lg-3" type="search" placeholder="Search" aria-label="Search" name="courseName">
                <button class="btn btn-outline-info col-lg-2" type="submit">Search</button>
                <p class="col-lg-5"></p>
            </div>
            <div class="row">
                <ul id="nav" class="col-lg-10">
                    <li><a href="course?courseID=<%=course.getCourseID()%>" style="color: aqua">详情</a></li>
                    <%if(session.getAttribute("user") != null && (choose.equals("false")|| user.getUID() == course.getUID())){%>
                    <li><a href="resource?courseID=<%=course.getCourseID()%>">资源</a></li>
                    <li><a href="homework?courseID=<%=course.getCourseID()%>">作业</a></li>
                    <%}else{%>
                    <li><a href="#" aria-disabled="true" style="color: gray">资源</a></li>
                    <li><a href="#" aria-disabled="true" style="color: gray">作业</a></li>
                    <%}%>
                    <li><a href="paging">搜索</a></li>
                    <li><a href="home">首页</a></li>
                </ul>
            </div>
        </div>
    </form>

    <!-- Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLongTitle">登录</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <form>
                        <div class="form-group row">
                            <label for="username" class="col-sm-2 col-form-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="username" placeholder="User Name">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-2 col-form-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="password" placeholder="Password">
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button id="login_in" type="button" class="btn btn-primary">登录</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="registerModalLongTitle">注册</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <form>
                        <div class="form-group row">
                            <label for="username" class="col-sm-3 col-form-label">用户名</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="reg-username" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3 col-form-label">密码</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="reg-password" placeholder="请输入密码，密码至少为6位且不全为数字">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3 col-form-label">确认密码</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="check-password" placeholder="请重新输入密码">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3 col-form-label">邮箱</label>
                            <div class="col-sm-9">
                                <input type="email" class="form-control" id="reg-email" placeholder="请输入邮箱">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3 col-form-label">验证邮箱</label>
                            <button id="get-verify" type="button" class="btn btn-primary">获得验证码</button>
                            <div class="col-sm-9">
                                <input type="email" class="form-control" id="reg-verify" placeholder="请输入验证码">
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button id="register" type="button" class="btn btn-primary">注册</button>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div style="margin-top: 10px;">
        <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#openCourseModal">创建知识点</a>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="openCourseModal" tabindex="-1" role="dialog" aria-labelledby="openCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="openCourseModalLabel">创建知识点</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  action="newK?unitID=<%=unitID%>&courseID=<%=course.getCourseID()%>" enctype="multipart/form-data" id="openCourseForm">
                    <div class="form-group">
                        <label for="knowledgeName" class="col-form-label">知识点名称</label>
                        <input name="knowledgeName" type="text" class="form-control" id="knowledgeName" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="discription" class="col-form-label">知识点描述:</label>
                        <textarea name="discription" class="form-control" id="discription"></textarea>
                    </div>

                    <button id="img_chooser" type="button" class="btn btn-primary">选择文件</button>

                    <input id="courseimg" type="file" name="filepath" accept="video/*" hidden/>

                    <video style="max-width: 100%; margin-top: 10px;" src="" id="preview"></video>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button form="openCourseForm" type="submit" class="btn btn-primary" id="createCourse">创建</button>
            </div>
        </div>
    </div>
</div>
<script>

    var bt = document.getElementById("img_chooser");
    var elem = document.getElementById('courseimg');
    var img = document.getElementById('preview');
    bt.onclick =function (ev) {
        elem.click()
    };
    elem.onchange
        = function() {
        var files = elem.files;
        var reader =  new FileReader();

        if(files&& files[0]){
            reader.onload= function(ev) {
                img.src= ev.target.result;
            };
            reader.readAsDataURL(files[0]);
        }
    };

</script>
<script src="js/MD5.js"></script>
<script src="js/register.js"></script>
<script src="js/login.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
