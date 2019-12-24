package ru.moskalevms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String jdbcConnectionString = ctx.getInitParameter("jdbcConnectionString");
        String dbUsername= ctx.getInitParameter("dbUsername");
        String dbPassword = ctx.getInitParameter("dbPassword");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, dbUsername, dbPassword);
            ctx.setAttribute("connection" , conn);
        } catch (SQLException ex) {
            logger.error("", ex);
        }

    }
}
