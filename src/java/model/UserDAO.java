/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import utils.DbUtils;

/**
 *
 * @author admin
 */
public class UserDAO {

    public UserDAO() {

    }

    public boolean login(String username, String password) {
        UserDTO user = getUserByUserName(username);
        return user != null && user.getPassword().equals(password);
    }

    public UserDTO getUserByUserName(String email) {
        UserDTO user = null;
        try {
            String sql = "SELECT * FROM [tblUsers] WHERE email = ?";
            Connection conn = DbUtils.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String pass = rs.getString("password");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String role = rs.getString("role");
                Timestamp createdAt = rs.getTimestamp("created_at");

                user = new UserDTO(id, name, email, pass, address, phoneNumber, role, createdAt);
            }

            rs.close();
            pre.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userList = new ArrayList<>();
        String sql = "SELECT * FROM tblUsers"; // sửa lại
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("user_id")); // Cột đúng là user_id
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean insertUser(UserDTO user) {
        String sql = "INSERT INTO [tblUsers] (name, email, password, address, phone_number, role) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());  // plain text
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(UserDTO user) {
        String sql = "UPDATE [tblUsers] SET name=?, email=?, password=?, address=?, phone_number=?, role=? WHERE user_id=?";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getRole());
            ps.setInt(7, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM [tblUsers] WHERE user_id = ?";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePassword(int userId, String encryptedPassword) {
        String sql = "UPDATE tblUsers SET password = ? WHERE user_id = ?";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, encryptedPassword);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
