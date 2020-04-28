package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpServlet extends HttpServlet {


    //线程不安全，Servlet默认单例，操作共享变量线程不安全

    private Integer count = 0;

    public MyHttpServlet(){
        System.out.println("MyHttpServlet 无参构造函数执行....");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        count++;
        resp.getWriter().write("<h1>Hello</h1> count:" + count);
    }


    //ServletContainerInitializer  servlet容器在初始化的时候可以加载一些第三方依赖jar信息


}
