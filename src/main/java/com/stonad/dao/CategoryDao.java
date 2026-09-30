package com.stonad.dao;

import com.stonad.connection.MyJdbcConnection;

import java.sql.*;
import java.util.*;

public class CategoryDao {

    public Map<Integer, String> getAll() throws SQLException {
        Map<Integer, String> categories = new LinkedHashMap<>();
        String sql = "SELECT id,name FROM categories ORDER BY name";
        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.put(rs.getInt("id"), rs.getString("name"));
            }
        }
        return categories;
    }
}
