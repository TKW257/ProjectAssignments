package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductDAO;
import model.ProductDTO;
import model.CategoryDAO;
import model.CategoryDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "dashboard";

        switch (action) {
            case "products":
                List<ProductDTO> productList = productDAO.getAllProducts();
                List<CategoryDTO> categoryList = categoryDAO.getAllCategories();
                request.setAttribute("products", productList);
                request.setAttribute("categories", categoryList);
                request.getRequestDispatcher("adminProducts.jsp").forward(request, response);
                break;

            case "addProductForm":
                List<CategoryDTO> categories = categoryDAO.getAllCategories();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                break;

            case "editProductForm":
                int productId = Integer.parseInt(request.getParameter("productId"));
                ProductDTO product = productDAO.getProductById(productId);
                List<CategoryDTO> editCategories = categoryDAO.getAllCategories();
                request.setAttribute("product", product);
                request.setAttribute("categories", editCategories);
                request.getRequestDispatcher("editProduct.jsp").forward(request, response);
                break;

            default:
                request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addProduct".equals(action)) {
            try {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String imageUrl = request.getParameter("imageUrl");

                ProductDTO newProduct = new ProductDTO(0, name, description, price, stock, categoryId, imageUrl);
                productDAO.insertProduct(newProduct);

                response.sendRedirect("admin?action=products");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("admin?action=addProductForm&error=1");
            }

        } else if ("deleteProduct".equals(action)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                productDAO.deleteProduct(productId);
                response.sendRedirect("admin?action=products");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("admin?action=products&error=1");
            }

        } else if ("updateProduct".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("productId"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String imageUrl = request.getParameter("imageUrl");

                ProductDTO updated = new ProductDTO(id, name, description, price, stock, categoryId, imageUrl);
                productDAO.updateProduct(updated);
                response.sendRedirect("admin?action=products");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("admin?action=editProductForm&productId=" + request.getParameter("productId") + "&error=1");
            }
        }
    }
} 