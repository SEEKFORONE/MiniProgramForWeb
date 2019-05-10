package servlet.util;

import dao.executionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "scoreServlet", value = "/score")
public class scoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        executionUtil executionUtil = new executionUtil();
        int score = Integer.parseInt(request.getParameter("score"));
        int UID = Integer.parseInt(request.getParameter("UID"));
        int HID = Integer.parseInt(request.getParameter("HID"));

        try {
            executionUtil.updateScore(score,UID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/execution?HID=%s",HID)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
