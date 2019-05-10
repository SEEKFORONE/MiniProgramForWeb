<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="domain.*" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/25
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = ((String)session.getAttribute("error") == null) ? "" : (String)session.getAttribute("error");
    user user= new user();
    String choose = (String)session.getAttribute("choose");
    course course = (course)session.getAttribute("course");
    List<unit> unitList = (List<unit>)session.getAttribute("unit");
    Map<Integer, List<knowledge>> knowledges = (Map<Integer, List<knowledge>>) session.getAttribute("knowledge");
    Map<discussion, String > discussions = (Map<discussion, String>) session.getAttribute("discussion");
    if(session.getAttribute("user") != null){
        user = (user)session.getAttribute("user");
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>课程详情</title>
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

<div class="container" style="padding: 10px;">

    <h3><span class="badge badge-secondary"><%=course.getCourseName()%></span>
        <%if(session.getAttribute("user") == null || course.getUID() == user.getUID()){%>
        <% }else if(choose.equals("true")){%>
        <button type="button" class="btn btn-primary" onclick="window.location.href='choosecourse?courseID=<%=course.getCourseID()%>'">选课</button>
       <% }else if(choose.equals("false")){%>
        <button type="button" class="btn btn-primary" onclick="window.location.href='quitcourse?courseID=<%=course.getCourseID()%>'">退课</button>
        <% }%>
    </h3>
    <div class="row" style="margin-top: 10px;">
        <div class="col-5">
            <div id="accordion">


                <!--对章节循环-->

                <%for (unit e:unitList) {%>
                <div class="card">
                    <div class="card-header">
                        <h5 class="mb-0">
                                <%=e.getUnitName()%>
                                <%if(user!=null&&user.getUID()==course.getUID()){%>
                                <a href="newKnowledge.jsp?courseID=<%=course.getCourseID()%>&unitID=<%=e.getUnitID()%>">添加知识点</a>
                                <%}%>
                        </h5>
                    </div>
                    <div class="collapse show" aria-labelledby="heading37" data-parent="#accordion">
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <!-- 对章节知识点循环 -->
                                <%
                                    for (Map.Entry<Integer, List<knowledge>> entry: knowledges.entrySet()) {
                                        if(entry.getKey() == e.getUnitID()){
                                            for(knowledge k : entry.getValue()){%>
                                <li class="list-group-item list-group-item-action">
                                    <a href="knowledge?cellID=<%=k.getCellID()%>&unitID=<%=e.getUnitID()%>"><%=k.getCellName()%></a>
                                <%if(user!=null&&user.getUID()==course.getUID()){%>
                                <a href="deleteKnowledge?cellID=<%=k.getCellID()%>&courseID=<%=course.getCourseID()%>">删除知识点</a>
                                <%}%>
                                </li>
                                <%} } }%>
                            </ul>
                        </div>
                    </div>
                </div>
                <% }%>

            </div>

            <% if(user!=null&&user.getUID()==course.getUID()){%>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createChapter">添加章节</button>
            <div class="modal fade" id="createChapter" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" >添加章节</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <form action="newUnit?courseID=<%=course.getCourseID()%>" method="post">
                                <label class="col-sm-3 col-form-label">章节名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="chapter" placeholder="请输入章节名称" name="unitName">
                                </div>
                                <button id="chapterBt" type="submit" class="btn btn-primary">确定</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>

        </div>


        <div class="col-2">
            <p><%=course.getDiscription()%></p>
        </div>

        <div class="col-4">
            <div>
                <div class="card">
                    <div class="card-header">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapse2">讨论板</button>
                        </h5>
                    </div>
                    <div id="collapse2" class="collapse show" aria-labelledby="heading2" data-parent="#accordion">
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <!-- 对讨论循环 -->
                                <%
                                    if(session.getAttribute("user") != null){
                                        %>
                                <li class="list-group-item list-group-item-action">
                                    <label>创建新的讨论话题</label>
                                    <form method="post" action="newDiscussion?courseID=<%=course.getCourseID()%>">
                                        <textarea class="form-control" rows="3"  name="discussion" placeholder="请输入讨论内容"></textarea>
                                        <button class="btn btn-outline-info" type="submit">发起讨论</button>
                                    </form>
                                </li>
                                <%
                                    }
                                %>

                                <%if(discussions != null){
                                    for(Map.Entry<discussion, String> entry: discussions.entrySet()) {
                                %>
                                <li class="list-group-item list-group-item-action">
                                    <p><i class="fa fa-user" aria-hidden="true"></i><%=entry.getValue()%></p>
                                    <p><%=entry.getKey().getDiscussion()%></p>
                                    <a href="discussion?DID=<%=entry.getKey().getDID()%>&creator=<%=entry.getValue()%>&topic=<%=entry.getKey().getDiscussion()%>">参与讨论</a>
                                </li>
                                <%} }%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<script>

    var bt = document.getElementsById("img_chooser");
    var elem = document.getElementsById('courseimg');
    var img = document.getElementsById('preview');
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
