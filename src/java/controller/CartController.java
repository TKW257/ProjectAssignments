package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private static final String CART_PAGE = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = CART_PAGE;

        if ("update".equals(action)) {
            url = handleCartUpdate(request);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private String handleCartUpdate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        try {
            String type = request.getParameter("type");
            int productId = Integer.parseInt(request.getParameter("product_id"));

            switch (type) {
                case "add":
                    cart.put(productId, cart.getOrDefault(productId, 0) + 1);
                    break;

                case "remove":
                    cart.remove(productId);
                    break;

                case "updateQty":
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    if (quantity <= 0) {
                        cart.remove(productId);
                    } else {
                        cart.put(productId, quantity);
                    }
                    break;

                default:
                    request.setAttribute("message", "Invalid cart action.");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error updating cart: " + e.getMessage());
        }

        session.setAttribute("cart", cart);
        return CART_PAGE;
    }
}
