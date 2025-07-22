package model;

import utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // Lấy danh sách thanh toán theo order_id
    public List<PaymentDTO> getPaymentsByOrderId(int orderId) {
        List<PaymentDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE order_id = ?";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PaymentDTO p = new PaymentDTO(
                    rs.getInt("payment_id"),
                    rs.getInt("order_id"),
                    rs.getTimestamp("payment_date"),
                    rs.getString("payment_method"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_status")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm thanh toán mới
    public boolean insertPayment(PaymentDTO payment) {
        String sql = "INSERT INTO Payments (order_id, payment_method, amount, payment_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setBigDecimal(3, payment.getAmount());
            ps.setString(4, payment.getPaymentStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật trạng thái thanh toán
    public boolean updatePaymentStatus(int paymentId, String status) {
        String sql = "UPDATE Payments SET payment_status = ? WHERE payment_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, paymentId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa thanh toán theo ID
    public boolean deletePayment(int paymentId) {
        String sql = "DELETE FROM Payments WHERE payment_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, paymentId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả các payment
    public List<PaymentDTO> getAllPayments() {
        List<PaymentDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Payments ORDER BY payment_id DESC";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PaymentDTO p = new PaymentDTO(
                    rs.getInt("payment_id"),
                    rs.getInt("order_id"),
                    rs.getTimestamp("payment_date"),
                    rs.getString("payment_method"),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_status")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
