package servlet.util;

import dao.knowledgeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteKnowledgeServlet", value = "/deleteKnowledge")
public class deleteKnowledgeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int courseID = Integer.parseInt(request.getParameter("courseID"));
        int cellID = Integer.parseInt(request.getParameter("cellID"));
        knowledgeUtil knowledgeUtil = new knowledgeUtil();

        try {
            knowledgeUtil.delete(cellID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/course?courseID=%s",courseID)));
    }
}
