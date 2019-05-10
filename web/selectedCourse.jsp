<%@ page import="domain.user" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.course" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/27
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<course> courseList = new ArrayList<>();
    if(session.getAttribute("choose") != null){
        courseList = (List<course>) session.getAttribute("choose");
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人空间</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
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
            <a class="nav-link active" href="myspace?state=1">我选的课</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="myspace?state=2">我开的课</a>
        </li>
    </ul>

    <div style="margin-top: 10px;">
        <a href="search.jsp" class="btn btn-primary">选课</a>
    </div>

    <div class="row">
        <%if(courseList != null){
            for (course c :courseList) {
                String path  ="image/"+c.getImagePath();%>
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
        session.removeAttribute("choose");
        }%>


    </div>
</div>
<script src="js/MD5.js"></script>
<script src="js/register.js"></script>
<script src="js/login.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
