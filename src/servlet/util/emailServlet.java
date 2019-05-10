package servlet.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "emailServlet",value = "/email")
public class emailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String verify = request.getParameter("verify");
        try {
            Sendmail(email,"验证码",verify);
        }catch (Exception e){
        }

        PrintWriter out  = response.getWriter();
        out.print("<h1>发送成功</h1>");
    }
    private static void Sendmail(String toMail,String mailTitle,String mailContent) throws Exception {

        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties(); //可以加载一个配置文件

        // 使用smtp：简单邮件传输协议,必须进行身份验证，存储发送邮件服务器的信息
        props.put("mail.smtp.host", "smtp.qq.com");

        //同时通过验证
        props.put("mail.smtp.auth", "true");

        //端口号，QQ邮箱给出了两个端口
        props.put("mail.smtp.port", "587");

        // 此处填写你的账号
        props.put("mail.user", "2274244698@qq.com");

        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", "kfpicbikibukeaah");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        mailSession.setDebug(true);

        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);

        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        //设置收件人,并设置其接收类型为TO
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

        //设置标题
        message.setSubject(mailTitle);

        //设置信件内容
        message.setText(mailContent); //发送 纯文本 邮件 todo
        //message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富

        //设置发信时间
        message.setSentDate(new Date());

        //存储邮件信息
        message.saveChanges();

        // 发送邮件
        Transport.send(message);
    }
}
