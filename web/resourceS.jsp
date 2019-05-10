<%@ page import="domain.*" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/29
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = ((String)session.getAttribute("message") == null) ? "" : (String)session.getAttribute("message");
    user user= (domain.user)session.getAttribute("user");
    course course = (course)session.getAttribute("course");
    String choose = (String)session.getAttribute("choose");
    List<resource> resourceList = (List<domain.resource>)session.getAttribute("resource");
%>
<html>
<head>
    <title>作业</title>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
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
        <%}else { %>
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
                    <%if(session.getAttribute("user") != null && (choose.equals("false")||user.getUID() == course.getUID())){%>
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

</header>
<div class="container" style="padding: 10px;">

    <h3><span class="badge badge-secondary"><%=course.getCourseName()%></span></h3>
    <div class="row" style="margin-top: 10px;">
        <div class="col-12">
            <div id="resource">
                <div class="card">
                    <div class="card-header" id="heading37">
                        <h5 class="mb-0">资源下载</h5>
                    </div>
                    <div id="collapse1" class="collapse show" aria-labelledby="heading1" data-parent="#resource">
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <%
                                    if(resourceList != null && resourceList.size()>0){
                                        for (resource r:resourceList) {
                                            String path = "resource/"+r.getPath();%>
                                <li class="list-group-item list-group-item-action">
                                    <%=r.getPath()%>
                                    <a href="<%=path%>" download="<%=r.getPath()%>">下载</a>

                                </li>
                                <%}}else{
                                        %>
                                <li class="list-group-item list-group-item-action">
                                    <p>该课程教师尚未上传任何资源</p>
                                </li>
                                <%
                                }%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="js/MD5.js"></script>
<script src="js/register.js"></script>
<script src="js/login.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
