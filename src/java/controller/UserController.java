package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.UserDTO;
import utils.DbUtils;
import model.UserDAO;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final String HOME = "home.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if ("login".equals(action)) {
                url = handleLogin(request, response);
            } else if ("logout".equals(action)) {
                url = handleLogout(request, response);
            } else if ("register".equals(action)) {
                url = handleRegister(request, response);
            } else {
                request.setAttribute("message", "Invalid action: " + action);
                url = LOGIN_PAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi xử lý: " + e.getMessage());
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

    private String handleLogin(HttpServletRequest request, HttpServletResponse response) {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        String email = request.getParameter("strUserName");
        String pass = request.getParameter("strPassword");

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUserByUserName(email);

        if (user != null && user.getPassword().equals(pass)) {
            session.setAttribute("user", user);
            url = HOME;
        } else {
            request.setAttribute("message", "Incorrect email or password.");
        }

        return url;
    }

    private String handleLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return LOGIN_PAGE;
    }

    private String handleRegister(HttpServletRequest request, HttpServletResponse response) {
        String url = LOGIN_PAGE;
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");

            // Không mã hóa password
            String role = "User";
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

            UserDTO user = new UserDTO(0, name, email, password, address, phoneNumber, role, createdAt);
            UserDAO dao = new UserDAO();

            if (dao.insertUser(user)) {
                request.setAttribute("message", "Đăng ký thành công. Vui lòng đăng nhập.");
            } else {
                request.setAttribute("message", "Đăng ký thất bại. Vui lòng thử lại.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi: " + e.getMessage());
        }
        return LOGIN_PAGE;
    }
}
