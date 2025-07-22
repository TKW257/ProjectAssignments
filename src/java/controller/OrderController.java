package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderDAO;
import model.OrderDTO;
import model.OrderDetailDAO;
import model.OrderDetailDTO;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.UserDTO;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    private static final String ORDER_SUCCESS_PAGE = "orderSuccess.jsp";
    private static final String CART_PAGE = "cart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = CART_PAGE;
        try {
            String action = request.getParameter("action");
            if ("checkout".equals(action)) {
                url = handleCheckout(request, response);
            } else {
                request.setAttribute("message", "Invalid action: " + action);
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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

    private String handleCheckout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null || cart == null || cart.isEmpty()) {
            request.setAttribute("message", "You must login and have items in cart to checkout.");
            return CART_PAGE;
        }

        try {
            OrderDAO orderDAO = new OrderDAO();
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            OrderDTO order = new OrderDTO(0, user.getUserId(), now, "pending", null);
            int orderId = orderDAO.insertOrder(order);

            List<OrderDetailDTO> orderDetails = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                orderDetails.add(new OrderDetailDTO(0, orderId, productId, quantity, null));
            }

            for (OrderDetailDTO detail : orderDetails) {
                orderDetailDAO.insertOrderDetail(detail);
            }

            session.removeAttribute("cart");
            request.setAttribute("message", "Order placed successfully.");
            return ORDER_SUCCESS_PAGE;

        } catch (Exception e) {
            request.setAttribute("message", "Checkout failed: " + e.getMessage());
            e.printStackTrace();
            return CART_PAGE;
        }
    }
}
