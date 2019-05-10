package servlet.util;

import dao.discussionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteDiscussionServlet", value = "/deleteDiscussion")
public class deleteDiscussionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int courseID = Integer.parseInt(request.getParameter("courseID"));
        int DID = Integer.parseInt(request.getParameter("DID"));

        discussionUtil discussionUtil = new discussionUtil();
        try {
            discussionUtil.delete(DID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/course?courseID=%s",courseID)));
    }
}
