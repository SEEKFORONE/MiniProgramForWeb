package servlet.browse;

import dao.executionUtil;
import dao.userUtil;
import domain.execution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "executionServlet", value = "/execution")
public class executionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        int HID = Integer.parseInt(request.getParameter("HID"));
        List<execution> list = new ArrayList<>();
        Map<String, execution> execution = new HashMap<>();

        executionUtil executionUtil = new executionUtil();
        userUtil userUtil = new userUtil();

        if(HID > 0){
            if(executionUtil.getSelect(HID) != null) {
                list = executionUtil.getSelect(HID);
                if(list.size() != 0){
                    for(execution e: list){
                        String username = userUtil.getSelectByID(e.getUID());
                        execution.put(username, e);
                    }
                }
            }
        }

        session.setAttribute("execution", execution);

        response.sendRedirect(response.encodeURL(String.format("grade.jsp?HID=%s",HID)));
    }
}
