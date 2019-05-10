package servlet.util;

import dao.resourceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteResourceServlet", value = "/deleteResource")
public class deleteResourceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int courseID = Integer.parseInt(request.getParameter("courseID"));
        int RID = Integer.parseInt(request.getParameter("RID"));

        resourceUtil resourceUtil = new resourceUtil();
        try {
            resourceUtil.delete(RID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/resource?courseID=%s",courseID)));
    }
}
