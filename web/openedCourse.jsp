<%@ page import="domain.user" %>
<%@ page import="domain.course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/27
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = ((String)session.getAttribute("message") == null) ? "" : (String)session.getAttribute("message");
    List<course> courseList = new ArrayList<>();
    if(session.getAttribute("open") != null){
        courseList = (List<course>) session.getAttribute("open");
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>个人空间</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    if(!message.isEmpty()){
%>
<script>
    alert("<%=message%>");
</script>
<%
        session.removeAttribute("message");
    }
%>
<header>
    <div id="sign">
        <%if(session.getAttribute("user") == null){%>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#registerModal"><h1>注册</h1></a>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#loginModal"><h1>登录</h1></a>
        <%}else {  user user= (domain.user)session.getAttribute("user");  %>
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
        </div>
    </form>

</header>
<div class="container">

    <ul class="nav nav-tabs" style="margin-top: 10px;">
        <li class="nav-item">
            <a class="nav-link" href="myspace?state=1">我选的课</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="myspace?state=2">我开的课</a>
        </li>
    </ul>
    <div style="margin-top: 10px;">
        <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#openCourseModal">开课</a>
    </div>
    <div class="row">

        <%if(courseList != null){
            for (course c :courseList) {
                String path  ="/image/"+c.getImagePath();%>
        <div class="col-3" style="margin-top: 10px;">
            <div class="card">
                <div class="card-body">
                    <img style="width: 100%; height: 150px;" src=<%=path%>>
                    <h5 style="margin-top: 10px;" class="card-title"><%=c.getCourseName()%></h5>
                    <p class="card-text"><%=c.getDiscription()%></p>
                    <a href="course?courseID=<%=c.getCourseID()%>" class="btn btn-primary">查看</a>
                </div>
            </div>
        </div>
        <%}
        session.removeAttribute("open");
        }%>

    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="openCourseModal" tabindex="-1" role="dialog" aria-labelledby="openCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="openCourseModalLabel">开设课程</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  action="opencourse" enctype="multipart/form-data" id="openCourseForm">
                    <div class="form-group">
                        <label for="courseName" class="col-form-label">课程名称</label>
                        <input name="courseName" type="text" class="form-control" id="courseName" placeholder="请输入课程名称">
                    </div>
                    <div class="form-group">
                        <label for="discription" class="col-form-label">课程描述:</label>
                        <textarea name="discription" class="form-control" id="discription"></textarea>
                    </div>

                    <button id="img_chooser" type="button" class="btn btn-primary">选择图片</button>

                    <input id="courseimg" type="file" name="filepath" accept="image/*" hidden/>

                    <img style="max-width: 100%; margin-top: 10px;" src="" id="preview">
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
