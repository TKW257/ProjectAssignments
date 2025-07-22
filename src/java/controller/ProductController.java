package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductDAO;
import model.ProductDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet {

    private static final String PRODUCT_PAGE = "shop.jsp";

    private ProductDAO productDAO = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = PRODUCT_PAGE;

        if ("add".equals(action)) {
            url = handleAddProduct(request);
        } else if ("filter".equals(action)) {
            url = handleFilter(request);
        } else if ("search".equals(action)) {
            url = handleSearch(request);
        } else {
            List<ProductDTO> products = productDAO.getAllProducts();
            request.setAttribute("productList", products);
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

    private String handleAddProduct(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String imageUrl = request.getParameter("imageUrl");

            ProductDTO product = new ProductDTO(0, name, description, price, stock, categoryId, imageUrl);
            productDAO.insertProduct(product);

            request.setAttribute("message", "Product added successfully");
        } catch (Exception e) {
            request.setAttribute("message", "Error adding product: " + e.getMessage());
        }
        return PRODUCT_PAGE;
    }

    private String handleFilter(HttpServletRequest request) {
        String sort = request.getParameter("sort"); // Lấy giá trị từ URL: ?action=filter&sort=newest
        List<ProductDTO> products;

        switch (sort) {
            case "price_high":
                products = productDAO.getProductsOrderByPriceDesc();
                break;
            case "most_popular":
                products = productDAO.getProductsByPopularity(); // Phải có hàm này trong DAO
                break;
            case "newest":
            default:
                products = productDAO.getNewestProducts(); // Phải có hàm này trong DAO
                break;
        }

        request.setAttribute("productList", products);
        return PRODUCT_PAGE; // "product.jsp"
    }

    private String handleSearch(HttpServletRequest request) {
    String keyword = request.getParameter("keyword");
    List<ProductDTO> products = productDAO.searchProductsByName(keyword);
    request.setAttribute("productList", products);
    request.setAttribute("searchKeyword", keyword); // optional: để hiển thị lại từ khóa trong ô tìm kiếm
    return PRODUCT_PAGE;
}

}
