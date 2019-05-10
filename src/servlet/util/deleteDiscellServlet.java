package servlet.util;

import dao.discellUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteDiscellServlet", value = "/deleteDiscell")
public class deleteDiscellServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int DID = Integer.parseInt(request.getParameter("DID"));
        int ID = Integer.parseInt(request.getParameter("ID"));

        discellUtil discellUtil = new discellUtil();

        try {
            discellUtil.delete(ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/discussion?DID=%s",DID)));
    }
}
