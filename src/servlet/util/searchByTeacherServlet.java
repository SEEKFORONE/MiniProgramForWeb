package servlet.util;

import dao.courseUtil;
import domain.course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchByTeacherServlet", value = "/sbt")
public class searchByTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String teacher = new String(request.getParameter("teacherName").trim().getBytes("ISO8859-1"),"UTF-8");

        if(teacher.isEmpty()){
            String error = "搜索内容不得为空";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/search.jsp")));
            return;
        }

        courseUtil courseUtil = new courseUtil();
        List<course> result = new ArrayList<>();

        if(courseUtil.getSelectByTeacher(teacher) != null) {
            result = courseUtil.getSelectByTeacher(teacher);
        }

        session.setAttribute("result", result);

        response.sendRedirect(response.encodeURL(String.format("/paging")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
