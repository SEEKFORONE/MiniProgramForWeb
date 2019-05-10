package servlet.util;

import domain.user;
import domain.discussion;
import dao.discussionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "newDiscussionServlet", value = "/newDiscussion")
public class newDiscussionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        String username = user.getUsername();
        int UID = user.getUID();
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        String discussion = new String(request.getParameter("discussion").trim().getBytes("ISO8859-1"),"UTF-8");

        System.out.println(discussion);

        if(discussion.isEmpty()){
            String error = "讨论内容不得为空";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/course?courseID=%s",courseID)));
            return;
        }

        discussionUtil discussionUtil = new discussionUtil();
        discussion dis = new discussion();
        dis.setUID(UID);
        dis.setCourseID(courseID);
        dis.setDiscussion(discussion);

        try {
            discussionUtil.add(dis);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/course?courseID=%s",courseID)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


