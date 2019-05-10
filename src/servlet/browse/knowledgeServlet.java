package servlet.browse;

import domain.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "knowledgeServlet",value = "/knowledge")
public class knowledgeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int cellID = Integer.parseInt(request.getParameter("cellID"));
        int unitID = Integer.parseInt(request.getParameter("unitID"));
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        int UID = 0;
        if(user != null){
            UID = user.getUID();
        }

        response.sendRedirect(response.encodeURL(String.format("points.jsp?cellID=%s&unitID=%s",cellID,unitID)));
    }
}
