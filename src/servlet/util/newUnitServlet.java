package servlet.util;

import dao.unitUtil;
import domain.unit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "newUnitServlet",value = "/newUnit")
public class newUnitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        int courseID = Integer.parseInt(request.getParameter("courseID"));
        String unitName = new String(request.getParameter("unitName").trim().getBytes("ISO8859-1"),"UTF-8");

        if(unitName.isEmpty()){
            String error = "章节名不得为空";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/course?courseID=%s",courseID)));
            return;
        }

        unitUtil unitUtil = new unitUtil();
        unit unit = new unit();
        unit.setUnitName(unitName);
        unit.setCourseID(courseID);

        try {
            unitUtil.add(unit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/course?courseID=%s",courseID)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
