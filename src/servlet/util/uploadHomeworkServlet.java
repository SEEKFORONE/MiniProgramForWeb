package servlet.util;

import dao.executionUtil;
import domain.execution;
import domain.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "uploadHomeworkServlet",value = "/uploadHW")
public class uploadHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        executionUtil executionUtil = new executionUtil();
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        int UID = user.getUID();
        int HID = Integer.parseInt(request.getParameter("HID"));
        int courseID = Integer.parseInt(request.getParameter("courseID"));

        String homework = new String(request.getParameter("homework").trim().getBytes("ISO8859-1"),"UTF-8");

        if(homework.isEmpty()){
            String error = "不得提交为空的作业";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/homework?courseID=%s",courseID)));
            return;
        }

        if(!executionUtil.select(HID,UID)){
            execution execution = new execution();
            execution.setUID(UID);
            execution.setHID(HID);
            execution.setHomework(homework);
            try {
                executionUtil.add(execution);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                executionUtil.update(homework, UID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(response.encodeURL(String.format("/homework?courseID=%s",courseID)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
