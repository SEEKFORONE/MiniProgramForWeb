<%@ page import="java.util.Date" %>
<%@ page import="domain.user" %>
<%@ page import="domain.course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/23
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = ((String)session.getAttribute("error") == null) ? "" : (String)session.getAttribute("error");
    List<course> hotCourse = (List<course>) session.getAttribute("hot");
    List<course> newCourse = (List<course>) session.getAttribute("new");
    domain.user user= new user();
    if(session.getAttribute("user") != null){
        user = (user)session.getAttribute("user");
    }
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/Home.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>课程首页</title>
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
        <%if(user.getUsername()==null){%>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#registerModal"><h1>注册</h1></a>
        <a href="#" class="col-lg-2" data-toggle="modal" data-target="#loginModal"><h1>登录</h1></a>
        <%}else {%>
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
<section id="section">
    <h2>HOTTEST COURSES</h2>
    <div id="demo" class="carousel slide" data-ride="carousel">

        <!-- 指示符 -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
        </ul>

        <!-- 轮播图片 -->
        <%--todo(更改为最热门三门课的courseId，图片路径，标题，简介)--%>
        <div class="carousel-inner">
            <%
                if(hotCourse.size() == 0){
                    %>
            <div class="carousel-item active">
                <a><img src="/image/default.png"></a>
                <div class="carousel-caption">
                    <h1></h1>
                    <p>无热门课程</p>
                </div>
            </div>
            <%
                }else {
                    course temp = hotCourse.get(0);
                    String path = "/image/"+temp.getImagePath();
                    %>
            <div class="carousel-item active">
                <a href="course?courseID=<%=temp.getCourseID()%>"><img src="<%=path%>"></a>
                <div class="carousel-caption">
                    <h1><%=temp.getCourseName()%></h1>
                    <p><%=temp.getDiscription()%></p>
                </div>
            </div>
            <%
                }
               for(int i=1;i<3;i++){
                    if(hotCourse.size() < i+1){
                       %>
            <div class="carousel-item">
                <a><img src="/image/default.png"></a>
                <div class="carousel-caption">
                    <h1></h1>
                    <p>无热门课程</p>
                </div>
            </div>
            <%
                    }else{
                        course temp = hotCourse.get(i);
                        String path = "/image/"+temp.getImagePath();
            %>
            <div class="carousel-item">
                <a href="course?courseID=<%=temp.getCourseID()%>"><img src="<%=path%>"></a>
                <div class="carousel-caption">
                    <h1><%=temp.getCourseName()%></h1>
                    <p><%=temp.getDiscription()%></p>
                </div>
            </div>
            <%
                    }
               }
            %>

        </div>
        <!-- 左右切换按钮 -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev" id="a1">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next" id="a2">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
</section>
<section>
    <h2>LATEST COURSES</h2>
    <%--todo(更改为最新三门课的courseId，图片路径，标题，简介)--%>
    <table>
        <tr><%
            for(int i=0;i<=2;i++){
                if(newCourse.size() < i+1){%>
            <td>
                <a><img src="/image/default.png"></a>
            </td>
        <%}else {
            course temp = newCourse.get(i);
            String path = "";
            if(temp.getImagePath().isEmpty()){
                path  = "/image/default.png";
            }else{
                path  ="/image/"+temp.getImagePath();
            }%>
            <td>
                <a href="course?courseID=<%=temp.getCourseID()%>"><img src=<%=path%>></a>
            </td><%}}%>
        </tr>

        <tr><%
            for(int i=0;i<=2;i++){
                if(newCourse.size() < i+1){%>
            <td>
                <h3>无更多新课</h3>
            </td>
            <%}else {
                course temp = newCourse.get(i);
            %>
            <td>
                <h3><%=temp.getCourseName()%></h3>
            </td><%}}%>
        </tr>

        <tr><%
            for(int i=0;i<=2;i++){
                if(newCourse.size() < i+1){%>
            <td>
                <h3>无更多新课</h3>
            </td>
            <%}else {
                course temp = newCourse.get(i);
            %>
            <td>
                <h3><%=temp.getDiscription()%></h3>
            </td><%}}%>
        </tr>

        <tr><%
            for(int i=0;i<=2;i++){
                if(newCourse.size() < i+1){%>
            <td></td><%}else {
                course temp = newCourse.get(i);
            %>
            <td>
                <button type="button" class="btn btn-primary" onclick="window.location.href='/course?courseID=<%=temp.getCourseID()%>'">查看</button>
            </td><%}}%>
        </tr>

    </table>
</section>
<footer>
    <p>Copyright © 2018 Online School</p>
</footer>
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
<script src="js/MD5.js"></script>
<script src="js/register.js"></script>
<script src="js/login.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
