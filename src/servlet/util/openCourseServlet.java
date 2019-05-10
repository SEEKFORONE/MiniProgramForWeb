package servlet.util;

import domain.user;
import domain.course;
import dao.courseUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "openCourseServlet",value="/opencourse")
public class openCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        user user = (user)session.getAttribute("user");
        int UID = user.getUID();

        String savePath = this.getServletContext().getRealPath("/image");
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        String message = "";
        course course = new course();
        course.setUID(UID);

        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return;
            }
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8").trim();

                    if(value.isEmpty()){
                        message = "课程名字或简介不得为空";
                        session.setAttribute("message",message);
                    }

                    if(name.equals("courseName")){
                        String courseName = value;
                        course.setCourseName(courseName);
                    }
                    if(name.equals("discription")){
                        String discription = value;
                        course.setDiscription(discription);
                    }
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    //System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //得到上传文件的扩展名
//                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
//                    if(fileExtName.equals("jpg")||fileExtName.equals("png")||fileExtName.equals("bmp")||fileExtName.equals("jpeg")){
//                        continue;
//                    }else {
//                        message = "上传文件不是图片格式";
//                        session.setAttribute("message",message);
//                        //回开课那个表单页面,message是返回的信息，处理一下
//                        response.sendRedirect(response.encodeURL(String.format("/myspace?state=2")));
//                    }
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = makeFileName(filename);

                    course.setImagePath(saveFilename);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFilename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();

        }

        courseUtil courseUtil = new courseUtil();
        try {
            courseUtil.add(course);
        } catch (SQLException e) {
            response.sendRedirect(response.encodeURL(String.format("/myspace?state=2")));
            return;
        }

        session.setAttribute("message",message);
        //回开课那个表单页面,message是返回的信息，处理一下
        response.sendRedirect(response.encodeURL(String.format("/myspace?state=2")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }
}
