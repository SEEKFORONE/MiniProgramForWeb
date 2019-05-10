package servlet.browse;

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

//学生作业详情页面（提交作业和看分数），exe...那个是老师作业详情页面，homework那个是课程作业页面
@WebServlet(name = "myHomeworkServlet", value = "/myHW")
public class myHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        executionUtil executionUtil = new executionUtil();
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        int UID = user.getUID();
        int HID = Integer.parseInt(request.getParameter("HID"));

        execution result = new execution();

        if(executionUtil.getMyHW(HID, UID)!= null){
            result = executionUtil.getMyHW(HID, UID);
        }

        session.setAttribute("result", result);

        response.sendRedirect(response.encodeURL(String.format("/gradeS.jsp?HID=%s",HID)));
    }
}
