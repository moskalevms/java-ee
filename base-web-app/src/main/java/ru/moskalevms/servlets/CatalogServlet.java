package ru.moskalevms.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "CatalogServlet" , urlPatterns = "catalog")
public class CatalogServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CartServlet.class);

    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = (Connection) getServletContext().getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Каталог</h1>");
        getServletContext().getRequestDispatcher("/index.html").include(req,resp);
        getServletContext().getRequestDispatcher("/product.html").include(req,resp);
        getServletContext().getRequestDispatcher("/order.html").include(req,resp);
        getServletContext().getRequestDispatcher("/cart.html").include(req,resp);

    }

    @Override
    public void destroy() {
        logger.info("CatalogServlet destroyed", getServletInfo());
    }


}
