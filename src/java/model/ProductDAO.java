package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

public class ProductDAO {

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblProducts ORDER BY product_id";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductDTO p = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("category_id"),
                        rs.getString("image_url")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDTO> searchProductsByName(String keyword) {
    List<ProductDTO> list = new ArrayList<>();
    String sql = "SELECT * FROM tblProducts WHERE name LIKE ?";

    try (Connection conn = DbUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ProductDTO p = new ProductDTO(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getInt("stock_quantity"),
                    rs.getInt("category_id"),
                    rs.getString("image_url")
            );
            list.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    
    public ProductDTO getProductById(int id) {
        String sql = "SELECT * FROM tblProducts WHERE product_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("category_id"),
                        rs.getString("image_url")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertProduct(ProductDTO p) {
        String sql = "INSERT INTO tblProducts (name, description, price, stock_quantity, category_id, image_url) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getStockQuantity());
            ps.setInt(5, p.getCategoryId());
            ps.setString(6, p.getImageUrl());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(ProductDTO p) {
        String sql = "UPDATE tblProducts SET name=?, description=?, price=?, stock_quantity=?, category_id=?, image_url=? WHERE product_id=?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getStockQuantity());
            ps.setInt(5, p.getCategoryId());
            ps.setString(6, p.getImageUrl());
            ps.setInt(7, p.getProductId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM tblProducts WHERE product_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductDTO> getNewestProducts() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblProducts ORDER BY product_id DESC";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductDTO p = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("category_id"),
                        rs.getString("image_url")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDTO> getProductsOrderByPriceDesc() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblProducts ORDER BY price DESC";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductDTO p = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("category_id"),
                        rs.getString("image_url")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDTO> getProductsByPopularity() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblProducts ORDER BY stock_quantity ASC"; // tạm giả lập mức phổ biến

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductDTO p = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("category_id"),
                        rs.getString("image_url")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
