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

public class OrderDetailDAO {

    public List<OrderDetailDTO> getDetailsByOrderId(int orderId) {
        List<OrderDetailDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Order_detail WHERE order_id = ?";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetailDTO d = new OrderDetailDTO(
                    rs.getInt("order_detail_id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("price_at_purchase")
                );
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertOrderDetail(OrderDetailDTO detail) {
        String sql = "INSERT INTO Order_detail (order_id, product_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detail.getOrderId());
            ps.setInt(2, detail.getProductId());
            ps.setInt(3, detail.getQuantity());
            ps.setBigDecimal(4, detail.getPriceAtPurchase());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
