package servlet.browse;

import domain.user;
import domain.course;
import dao.courseUtil;
import dao.studentUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "myspaceServlet", value = "/myspace")
public class myspaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        user user = (user) session.getAttribute("user");
        int UID = user.getUID();

        courseUtil courseUtil = new courseUtil();
        studentUtil studentUtil = new studentUtil();

        List<course> openlist = new ArrayList<>();
        List<course> chooselist = new ArrayList<>();

        if( courseUtil.getSelectByUID(UID) != null) {
            openlist = courseUtil.getSelectByUID(UID);
        }
        if(studentUtil.getSelectByUID(UID)!= null) {
            chooselist = studentUtil.getSelectByUID(UID);
        }

        session.setAttribute("open", openlist);
        session.setAttribute("choose", chooselist);

        String state = "";
        if(request.getParameter("state")!=null){
            state = request.getParameter("state");
        }
        if(state.equals("2")){
            response.sendRedirect(response.encodeURL(String.format("openedCourse.jsp")));
            return;
        }else {
            response.sendRedirect(response.encodeURL(String.format("selectedCourse.jsp")));
        }


    }
}
