package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.PaymentDAO;

import java.io.IOException;

@WebServlet(name = "PaymentController", urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    private static final String PAYMENT_PAGE = "payment.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = PAYMENT_PAGE;

        if ("updateStatus".equals(action)) {
            url = handleUpdatePaymentStatus(request);
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

    private String handleUpdatePaymentStatus(HttpServletRequest request) {
        int paymentId = Integer.parseInt(request.getParameter("payment_id"));
        String status = request.getParameter("status");

        PaymentDAO dao = new PaymentDAO();
        boolean updated = dao.updatePaymentStatus(paymentId, status);

        if (updated) {
            request.setAttribute("message", "Payment status updated successfully");
        } else {
            request.setAttribute("message", "Failed to update payment status");
        }
        return PAYMENT_PAGE;
    }
}
