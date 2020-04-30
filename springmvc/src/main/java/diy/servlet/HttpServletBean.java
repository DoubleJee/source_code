package diy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletBean extends HttpServlet {

    @Override
    public void init() throws ServletException {
        initServletBean();
    }

    protected void initServletBean(){

    }
}
