package ru.moskalevms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Enumeration;

public class HelloWorldServlet implements Servlet, Serializable {

    private Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);

    private transient ServletConfig servletConfig;
    private transient Connection conn;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
            this.servletConfig =servletConfig;
            this.conn = (Connection) this.servletConfig.getServletContext().getAttribute("dbConnection");
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");
        res.getWriter().println("<h1>Hello World from servlet</h1>");
    }

    @Override
    public String getServletInfo() {
        return "Hellow World Servlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet ready to be destroyed");
    }
}
