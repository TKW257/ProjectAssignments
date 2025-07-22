/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tranh
 */
import utils.DbUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<OrderDTO> getAllOrders() {
    List<OrderDTO> orders = new ArrayList<>();
    String sql = "SELECT * FROM [Order]";

    try (Connection conn = DbUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            OrderDTO order = new OrderDTO(
                rs.getInt("order_id"),
                rs.getInt("user_id"),
                rs.getTimestamp("order_date"),
                rs.getString("status"),
                rs.getBigDecimal("total_amount")
            );
            orders.add(order);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return orders;
}

    public List<OrderDTO> getOrdersByUserId(int userId) {
        List<OrderDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order] WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDTO o = new OrderDTO(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("order_date"),
                    rs.getString("status"),
                    rs.getBigDecimal("total_amount")
                );
                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertOrder(OrderDTO order) {
        int generatedId = -1;
        String sql = "INSERT INTO [Order] (user_id, status, total_amount) OUTPUT INSERTED.order_id VALUES (?, ?, ?)";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getStatus());
            ps.setBigDecimal(3, order.getTotalAmount());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }
     public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE [Order] SET status = ? WHERE order_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM [Order] WHERE order_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public double getProductPrice(int productId) {
    String sql = "SELECT price FROM tblProducts WHERE product_id = ?";
    try (Connection conn = DbUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("price");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

}

