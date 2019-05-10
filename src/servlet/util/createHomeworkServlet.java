package servlet.util;

import dao.homeworkUtil;
import domain.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "createHomeworkServlet", value="/createHW")
public class createHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        homework homework = new homework();
        homeworkUtil homeworkUtil = new homeworkUtil();
        int courseID = Integer.parseInt(request.getParameter("courseID"));

        String discription = new String(request.getParameter("discription").trim().getBytes("ISO8859-1"),"UTF-8");

        if(discription.isEmpty()){
            String error = "作业题目不得为空";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/homework?courseID=%s",courseID)));
            return;
        }

        homework.setDiscription(discription);
        homework.setCourseID(courseID);

        try {
            homeworkUtil.add(homework);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("homework?courseID=%s",courseID)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
