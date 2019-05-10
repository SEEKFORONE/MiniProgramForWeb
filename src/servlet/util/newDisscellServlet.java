package servlet.util;

import domain.user;
import domain.discell;
import dao.discellUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "newDisscellServlet", value = "/newDiscell")
public class newDisscellServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        String username = user.getUsername();
        int UID = user.getUID();
        int DID = Integer.parseInt(request.getParameter("DID"));

        String creator = (String) session.getAttribute("creator");
        String topic = (String) session.getAttribute("topic");

        String discussion = new String(request.getParameter("discussion").trim().getBytes("ISO8859-1"),"UTF-8");

        if(discussion.isEmpty()){
            String error = "讨论内容不得为空";
            session.setAttribute("error",error);
            response.sendRedirect(response.encodeURL(String.format("/discussion?DID=%s&creator=%s&topic=%s",DID, creator, topic)));
            return;
        }

        discellUtil discellUtil = new discellUtil();
        discell discell = new discell();
        discell.setDID(DID);
        discell.setUID(UID);
        discell.setDiscussion(discussion);

        try {
            discellUtil.add(discell);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(response.encodeURL(String.format("/discussion?DID=%s&creator=%s&topic=%s",DID,creator,topic)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
