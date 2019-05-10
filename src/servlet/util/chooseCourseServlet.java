package servlet.util;

import dao.studentUtil;
import domain.user;
import domain.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "chooseCourseServlet", value = "/choosecourse")
public class chooseCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");

        int courseID = Integer.parseInt(request.getParameter("courseID"));

        studentUtil studentUtil = new studentUtil();
        student student = new student();

        student.setCourseID(courseID);
        student.setUID(user.getUID());

        try {
            studentUtil.add(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/myspace?state=1")));
    }
}
