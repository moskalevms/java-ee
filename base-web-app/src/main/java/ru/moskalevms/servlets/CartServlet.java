package ru.moskalevms.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.moskalevms.HelloWorldServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "CartServlet" , urlPatterns = "cart")
public class CartServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CartServlet.class);

    private Connection connection;

    @Override
    public void init() throws ServletException{
        connection = (Connection) getServletContext().getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Корзина</h1>");

       // getServletContext().getRequestDispatcher("/header.html").include(req, resp);

        //Redirect to main page
        getServletContext().getRequestDispatcher("/index.html").include(req,resp);

        getServletContext().getRequestDispatcher("/product.html").include(req,resp);
        getServletContext().getRequestDispatcher("/order.html").include(req,resp);
        getServletContext().getRequestDispatcher("/catalog.html").include(req,resp);

       // getServletContext().getRequestDispatcher("/footer.html").include(req, resp);

        resp.getWriter().println("<p>Response with cookie</p>");
        Cookie ck=new Cookie("user", "");
        ck.setMaxAge(0);
        resp.addCookie(ck);

    }


    @Override
    public void destroy() {
        logger.info("CartServlet destroyed", getServletInfo());
    }
}
