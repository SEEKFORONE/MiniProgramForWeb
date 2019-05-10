<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="domain.*" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/29
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int HID =Integer.parseInt(request.getParameter("HID"));
    String choose = (String)session.getAttribute("choose");
    course course = (course)session.getAttribute("course");
    user user= new user();
    user =(user)session.getAttribute("user");
    Map<String, execution> exe = new HashMap<>();
    if(session.getAttribute("execution")!=null){
        exe = (Map<String, execution>) session.getAttribute("execution");
    }
%>
<html>
<head>
    <title>评分</title>
    <link href="css/all.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<header>
    <div id="sign">
        <%if(session.getAttribute("name") ==null){%>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#registerModal"><h1>注册</h1></a>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#loginModal"><h1>登录</h1></a>
        <%}else {%>
        <a href="mySpace?state=1" class="col-lg-2" ><h1><%=session.getAttribute("name")%></h1></a>
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
                    <li><a href="course?courseID=<%=course.getCourseID()%>">详情</a></li>
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

</header>
<h3><span class="badge badge-secondary"><%=course.getCourseName()%></span></h3>
<div class="card">
    <div class="card-header">
        <h5 class="mb-0">问题<%=HID%></h5>
    </div>
    <div class="collapse show" aria-labelledby="heading37" data-parent="#accordion">
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <!-- 对回答循环 -->
                <%
                    if(exe.size() == 0) {
                        %>
                <li class="list-group-item list-group-item-action">
                    <p>尚未有学生提交改作业</p>
                </li>
                <%
                    }else {
                        for(Map.Entry<String, execution> entry: exe.entrySet()){
                       %>
                <li class="list-group-item list-group-item-action">
                    <p><i class="fa fa-user" aria-hidden="true"><%=entry.getKey()%></i></p>
                    <%
                        if(entry.getValue().getHomework().isEmpty()){
                    %>
                    <p>未答题</p>
                    <%
                    }else{
                    %>
                    <p><%=entry.getValue().getHomework()%></p>
                    <%
                        }
                    %>
                    <%
                        if(entry.getValue().getScore() == -1){
                            %>
                    <p>未批阅</p>
                    <%
                        }else{
                            %>
                    <p>分数:<%=entry.getValue().getScore()%></p>
                    <%
                        }
                    %>
                    <form method="post" action="/score?UID=<%=entry.getValue().getUID()%>&HID=<%=entry.getValue().getHID()%>">
                        <input type="number" min="0" max="100" placeholder="" name="score"><button type="submit">改分</button>
                    </form>
                </li>
                <%
                    } }%>

            </ul>
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
