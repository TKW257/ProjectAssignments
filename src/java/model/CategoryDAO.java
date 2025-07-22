/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author tranh
 */
public class CategoryDAO {
   

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories ORDER BY category_id";

        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoryDTO c = new CategoryDTO(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("description")
                );
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean insertCategory(CategoryDTO c) {
        String sql = "INSERT INTO Categories (category_name, description) VALUES (?, ?)";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setString(2, c.getDescription());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCategory(CategoryDTO c) {
        String sql = "UPDATE Categories SET category_name = ?, description = ? WHERE category_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getCategoryId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(int categoryId) {
        String sql = "DELETE FROM Categories WHERE category_id = ?";
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

    

