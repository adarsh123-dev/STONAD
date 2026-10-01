package com.stonad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.stonad.connection.MyJdbcConnection;

public class LikeDao {

    // Check whether user already liked artwork
    public boolean hasLiked(int artworkId, int userId) {

        String sql = "SELECT id FROM artwork_likes " +
                     "WHERE artwork_id = ? AND user_id = ?";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artworkId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Add like
    public void addLike(int artworkId, int userId) {

        String sql = "INSERT INTO artwork_likes " +
                     "(artwork_id, user_id) VALUES (?, ?)";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artworkId);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Remove like
    public void removeLike(int artworkId, int userId) {

        String sql = "DELETE FROM artwork_likes " +
                     "WHERE artwork_id = ? AND user_id = ?";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artworkId);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Count likes
    public int getLikeCount(int artworkId) {

        String sql = "SELECT COUNT(*) FROM artwork_likes " +
                     "WHERE artwork_id = ?";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artworkId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}