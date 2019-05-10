package servlet.browse;

import dao.courseUtil;
import domain.course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeServlet", value = "/home")
public class homeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        courseUtil courseUtil = new courseUtil();
        List<course> hotCourse = new ArrayList<>();
        List<course> newCourse = new ArrayList<>();

        if(courseUtil.getSelect() != null) {
            List<course> temp = courseUtil.getSelect();
            for(int i=0; i<=2; i++){
                if(temp.size()>0&&temp.get(i) != null){
                    newCourse.add(temp.get(i));
                }
            }
        }
        if(courseUtil.selectHot() != null) {
            List<Integer> list = new ArrayList<>();
            list = courseUtil.selectHot();

            if(list.size() >= 3){
                for(int i=0; i<=2; i++){
                    if (courseUtil.getSelectByID(list.get(i)) != null){
                        course course = courseUtil.getSelectByID(list.get(i));
                        hotCourse.add(course);
                    }
                }
            }else if(list.size() == 2){
                course course1 = courseUtil.getSelectByID(list.get(0));
                course course2 = courseUtil.getSelectByID(list.get(1));
                hotCourse.add(course1);
                hotCourse.add(course2);
            }else if(list.size() == 1){
                course course = courseUtil.getSelectByID(list.get(0));
                hotCourse.add(course);
            }
        }

        session.setAttribute("hot", hotCourse);
        session.setAttribute("new", newCourse);

        response.sendRedirect(response.encodeURL(String.format("index.jsp")));
    }
}
