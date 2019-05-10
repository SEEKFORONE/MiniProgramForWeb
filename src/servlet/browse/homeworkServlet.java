package servlet.browse;

import dao.courseUtil;
import dao.homeworkUtil;
import domain.course;
import domain.homework;
import domain.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeworkServlet", value = "/homework")
public class homeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        course course = new course();
        List<homework> list = new ArrayList<>();
        homeworkUtil homeworkUtil = new homeworkUtil();
        courseUtil courseUtil = new courseUtil();

        if(courseID > 0){
            if(courseUtil.getSelectByID(courseID)!= null){
                course = courseUtil.getSelectByID(courseID);
            }
            if(homeworkUtil.getSelect(courseID) != null) {
                list = homeworkUtil.getSelect(courseID);
            }
        }

        session.setAttribute("homework", list);

        user user = (domain.user)session.getAttribute("user");
        if(user.getUID() == course.getUID()){
            response.sendRedirect(response.encodeURL(String.format("homeworkT.jsp")));
            return;
        }else {
            response.sendRedirect(response.encodeURL(String.format("homeworkS.jsp")));
        }

    }
}
