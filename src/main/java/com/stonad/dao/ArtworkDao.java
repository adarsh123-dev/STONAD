package com.stonad.dao;

import com.stonad.connection.MyJdbcConnection;
import com.stonad.model.Artwork;

import java.sql.*;
import java.util.*;

public class ArtworkDao {

    private Artwork map(ResultSet rs) throws SQLException {
        Artwork a = new Artwork();
        a.setId(rs.getInt("id"));
        a.setArtistId(rs.getInt("artist_id"));
        a.setArtistName(rs.getString("artist_name"));
        a.setTitle(rs.getString("title"));
        a.setDescription(rs.getString("description"));
        a.setCategory(rs.getString("category"));
        a.setCategoryId(rs.getInt("category_id"));
        a.setImagePath(rs.getString("image_path"));
        return a;
    }

    public boolean create(Artwork a) throws SQLException {
        String sql = "INSERT INTO artworks(artist_id,title,description,category_id,image_path) VALUES(?,?,?,?,?)";
        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getArtistId());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getDescription());
            ps.setInt(4, a.getCategoryId());
            ps.setString(5, a.getImagePath());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Artwork> findAll(String search, Integer categoryId) throws SQLException {
        List<Artwork> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT a.*, u.name artist_name, c.name category " +
            "FROM artworks a JOIN users u ON a.artist_id=u.id " +
            "LEFT JOIN categories c ON a.category_id=c.id WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            sql.append("AND (a.title LIKE ? OR a.description LIKE ? OR u.name LIKE ?) ");
            String q = "%" + search.trim() + "%";
            params.add(q); params.add(q); params.add(q);
        }
        if (categoryId != null) {
            sql.append("AND a.category_id=? ");
            params.add(categoryId);
        }

        sql.append("ORDER BY a.created_at DESC");

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public Artwork findById(int id) throws SQLException {
        String sql = "SELECT a.*, u.name artist_name, c.name category " +
                     "FROM artworks a JOIN users u ON a.artist_id=u.id " +
                     "LEFT JOIN categories c ON a.category_id=c.id WHERE a.id=?";
        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public boolean delete(int id, int artistId) throws SQLException {
        String sql = "DELETE FROM artworks WHERE id=? AND artist_id=?";
        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, artistId);
            return ps.executeUpdate() > 0;
        }
    }
}
