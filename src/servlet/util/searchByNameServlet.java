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

@WebServlet(name = "searchByNameServlet", value="/sbn")
public class searchByNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String name = new String(request.getParameter("courseName").trim().getBytes("ISO8859-1"),"UTF-8");
        //System.out.println(name);

        if(name.isEmpty()){
            String error = "搜索内容不得为空";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/search.jsp")));
            return;
        }

        courseUtil courseUtil = new courseUtil();
        List<course> result = new ArrayList<>();

        if(courseUtil.getSelectByName(name) != null) {
            result = courseUtil.getSelectByName(name);
        }

        session.setAttribute("result", result);

        response.sendRedirect(response.encodeURL(String.format("/paging")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
