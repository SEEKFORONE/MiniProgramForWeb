package servlet.browse;

import domain.user;
import domain.discussion;
import domain.knowledge;
import domain.unit;
import domain.course;
import dao.courseUtil;
import dao.unitUtil;
import dao.knowledgeUtil;
import dao.discussionUtil;
import dao.studentUtil;
import dao.userUtil;

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

@WebServlet(name = "courseServlet", value="/course")
public class courseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        int UID = 0;
        if(user != null){
            UID = user.getUID();
        }

        int courseID = Integer.parseInt(request.getParameter("courseID"));
        course course = new course();
        List<unit> unitList = new ArrayList<>();
        Map<Integer, List<knowledge>> knowledges = new HashMap<>();
        Map<discussion, String> discussions = new HashMap<>();

        courseUtil courseUtil = new courseUtil();
        unitUtil unitUtil = new unitUtil();
        knowledgeUtil knowledgeUtil = new knowledgeUtil();
        discussionUtil discussionUtil = new discussionUtil();
        studentUtil studentUtil = new studentUtil();
        userUtil userUtil = new userUtil();

        session.setAttribute("choose", "true");

        if(courseID > 0) {
            if(courseUtil.getSelectByID(courseID)!= null){
                course = courseUtil.getSelectByID(courseID);
            }
            if(unitUtil.getSelect(courseID) != null){
                unitList = unitUtil.getSelect(courseID);
                if(unitList.size() != 0){
                    for(unit e : unitList){
                        if(knowledgeUtil.getSelect(e.getUnitID()) != null){
                            knowledges.put(e.getUnitID(), knowledgeUtil.getSelect(e.getUnitID()));
                        }
                    }
                }
            }
            if(discussionUtil.getSelect(courseID) != null) {
                List<discussion> list = discussionUtil.getSelect(courseID);
                if(list.size() != 0){
                    for(discussion e : list) {
                        String username = userUtil.getSelectByID(e.getUID());
                        discussions.put(e, username);
                    }
                }
            }

            if(studentUtil.getSelect(courseID) != null) {
                List<Integer> list = studentUtil.getSelect(courseID);
                if(list.size() != 0){
                    for(int e : list){
                        if(UID == e){
                            session.setAttribute("choose", "false");
                        }
                    }
                }
            }
        }

        session.setAttribute("course", course);
        session.setAttribute("unit", unitList);
        session.setAttribute("knowledge", knowledges);
        session.setAttribute("discussion", discussions);

        response.sendRedirect(response.encodeURL(String.format("details.jsp")));
    }
}
