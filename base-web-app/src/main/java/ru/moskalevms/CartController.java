package ru.moskalevms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.moskalevms.persist.Item;
import ru.moskalevms.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartController", urlPatterns = {"/", ""})
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductRepository productRepository;

    public CartController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            displayCart(req, resp);
        } else {
            if(action.equalsIgnoreCase("buy")){
                try {
                    buy(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (action.equalsIgnoreCase("remove")){
                remove(req, resp);
            }
        }
    }

    protected void buy(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex){
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        HttpSession session = req.getSession();
        if(session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productRepository.findById(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = productInCart(req.getParameter("id"), cart);
            if (index == -1){
                cart.add(new Item(productRepository.findById(id), 1));
            }else {
                int quantity = cart.get(index).getQuantity() +1;
                cart.get(index).setQuantity(quantity);
        }
            session.setAttribute("cart", cart);
        }
        resp.sendRedirect("cart");
    }

    protected void remove(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = productInCart(req.getParameter("id"), cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        try {
            resp.sendRedirect("cart");
        } catch (IOException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void displayCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    private int productInCart(String id, List<Item> cart){
        for (int i = 0; i < cart.size(); i++){
            if (cart.get(i).getProduct().getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
