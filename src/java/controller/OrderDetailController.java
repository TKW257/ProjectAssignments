package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.OrderDetailDAO;

import java.io.IOException;

@WebServlet(name = "OrderDetailController", urlPatterns = {"/order-detail"})
public class OrderDetailController extends HttpServlet {

    private static final String DETAIL_PAGE = "orderDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdStr = request.getParameter("order_id");

        if (orderIdStr != null) {
            int orderId = Integer.parseInt(orderIdStr);
            OrderDetailDAO dao = new OrderDetailDAO();
            request.setAttribute("details", dao.getDetailsByOrderId(orderId));
        }

        request.getRequestDispatcher(DETAIL_PAGE).forward(request, response);
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
}