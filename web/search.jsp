<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="domain.*" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/25
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = ((String)session.getAttribute("error") == null) ? "" : (String)session.getAttribute("error");
    domain.user user= new user();
    course course = (domain.course)request.getAttribute("course");
    if(session.getAttribute("user") != null){
        user = (domain.user)session.getAttribute("user");
    }

    List<course> list = new ArrayList<>();
    if(session.getAttribute("list") != null) {
        list = (List<domain.course>) session.getAttribute("list");
    }
    Paging paging = (Paging) session.getAttribute("paging");
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>搜索</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        button{
            padding:5px;
            margin:5px;
        }
        .active-nick{
            color:red;
        }
        input{
            width:50px;
            text-align:center;
        }
    </style>
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

<div>
    <div class="row">
        <div class="col-lg-5">
            <form action="sbn" method="post">
                <div class="input-group">
                    课程名字：<input type="text" class="form-control" name="courseName" id="courseName">
                    <input type="submit" class="btn btn-primary col-lg-3" value="Search" style="float: right">
                </div>
            </form>
            <form action="sbd" method="post">
                <div class="input-group">
                    课程简介：<input type="text" class="form-control" name="discription" id="discription">
                    <input type="submit" class="btn btn-primary col-lg-3" value="Search" style="float: right">
                </div>
            </form>
            <form action="sbt" method="post">
                <div class="input-group">
                    教师名字：<input type="text" class="form-control" name="teacherName" id="teacherName">
                    <input type="submit" class="btn btn-primary col-lg-3" value="Search" style="float: right">
                </div>
            </form>
        </div>
    </div>
        <div class="col-lg-4"></div>
        <div class="btn-group col-lg-2" style="float: right">
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> 排序:选课人数
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="sort">顺序</a></li>
                <li><a href="sortDesc">倒序</a></li>
            </ul>
        </div>
</div>

<h2>搜索结果</h2>
<div id="searchresult">
    <div class="row">
        <%
            if(list.size() == 0){
                %>
        <p>无搜索结果</p>
        <%
            }
        %>
        <%for(int i=0;i<list.size();i++) {
            course c = list.get(i);
                String path  ="/image/"+c.getImagePath();%>
        <div class="col-4" style="margin-top: 10px;">
            <div class="card">
                <div class="card-body">
                    <img style="width: 100%; height: 150px;" src=<%=path%>>
                    <h5 style="margin-top: 10px;" class="card-title"><%=c.getCourseName()%></h5>
                    <p class="card-text"><%=c.getDiscription()%></p>
                    <a href="course?courseID=<%=c.getCourseID()%>" class="btn btn-primary">查看</a>
                </div>
            </div>
        </div>
        <%}%>
    </div>
    <br/>
    <br/>
    <div>
        <p class="paging">
            <a href="paging?page=<%=paging.getIndexpage()%>">&lt;&lt; 首页 </a>
            <a href="paging?page=<%=paging.getPage()-1%>">    &lt; 上一页 </a>
            <strong>第<%=paging.getPage()+1%>页/共<%=paging.getPagenumber()%>页</strong>
            <a href="paging?page=<%=paging.getPage()+1%>">下一页 &gt;</a>
            <a href="paging?page=<%=paging.getPagenumber()+1%>">末页 &gt;&gt;</a>
        </p>
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