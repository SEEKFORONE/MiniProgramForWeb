package servlet.util;

import domain.course;
import dao.courseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "sortServlet", value = "/sort")
public class sortServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        courseUtil courseUtil = new courseUtil();

        List<course> list = new ArrayList<>();
        if(session.getAttribute("result")!=null){
            list = (List<course>) session.getAttribute("result");
        }
        List<course> result = new ArrayList<>();

        if(list.size() != 0){
            for(int i = 0; i < list.size(); i++){
                if(result.size() == 0){
                    result.add(list.get(i));
                }else if(courseUtil.selectStudentNum(result.get(i-1).getCourseID()) >
                        courseUtil.selectStudentNum(list.get(i).getCourseID())){
                    course temp = result.get(i-1);
                    result.remove(result.get(i-1));
                    result.add(list.get(i));
                    result.add(temp);
                }else {
                    result.add(list.get(i));
                }
            }
        }

        session.setAttribute("result", result);

        response.sendRedirect(response.encodeURL(String.format("/paging")));
    }
}
