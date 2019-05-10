package servlet.browse;

import dao.discellUtil;
import dao.userUtil;
import domain.discell;

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

@WebServlet(name = "discussionServlet", value = "/discussion")
public class discussionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        userUtil userUtil = new userUtil();
        int DID = Integer.parseInt(request.getParameter("DID"));

        String creator = request.getParameter("creator");
        String topic = request.getParameter("topic");

        if(session.getAttribute("creator")!=null){
            creator = (String) session.getAttribute("creator");
        }
        if(session.getAttribute("topic")!=null){
            topic = (String) session.getAttribute("topic");
        }

        discellUtil discellUtil = new discellUtil();
        List<discell> list = new ArrayList<>();
        Map<discell, String> discells = new HashMap<>();

        if(DID > 0){
            if(discellUtil.getSelect(DID) != null) {
                list = discellUtil.getSelect(DID);
                if(list.size() != 0){
                    for(discell e: list){
                        String username = userUtil.getSelectByID(e.getUID());
                        discells.put(e,username);
                    }
                }
            }
        }

        System.out.println(creator+"    "+topic);

        session.setAttribute("creator",creator);
        session.setAttribute("topic",topic);
        session.setAttribute("discell", discells);

        response.sendRedirect(response.encodeURL(String.format("discuss.jsp?DID=%s",DID)));
    }
}
