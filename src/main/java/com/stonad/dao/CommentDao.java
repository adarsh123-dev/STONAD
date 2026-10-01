package com.stonad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stonad.connection.MyJdbcConnection;
import com.stonad.model.Comment;

public class CommentDao {

    // =====================================================
    // ADD COMMENT
    // =====================================================

    public boolean addComment(int artworkId, int userId, String comment) {

        String sql = "INSERT INTO artwork_comments "
                   + "(artwork_id, user_id, comment) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artworkId);
            ps.setInt(2, userId);
            ps.setString(3, comment);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // GET COMMENTS BY ARTWORK
    // =====================================================

    public List<Comment> getComments(int artworkId) {

        List<Comment> comments = new ArrayList<>();

        String sql =
                "SELECT c.id, c.artwork_id, c.user_id, "
              + "u.name, c.comment, c.created_at "
              + "FROM artwork_comments c "
              + "JOIN users u ON c.user_id = u.id "
              + "WHERE c.artwork_id = ? "
              + "ORDER BY c.created_at DESC";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artworkId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Comment comment = new Comment();

                comment.setId(rs.getInt("id"));
                comment.setArtworkId(rs.getInt("artwork_id"));
                comment.setUserId(rs.getInt("user_id"));

                // Get username from users table
                comment.setUsername(rs.getString("name"));

                comment.setComment(rs.getString("comment"));
                comment.setCreatedAt(rs.getString("created_at"));

                comments.add(comment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }


    // =====================================================
    // DELETE COMMENT
    // =====================================================

    public boolean deleteComment(int commentId, int userId) {

        String sql =
                "DELETE FROM artwork_comments "
              + "WHERE id = ? AND user_id = ?";

        try (Connection con = MyJdbcConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
 // =====================================================
 // COUNT COMMENTS
 // =====================================================

 public int getCommentCount(int artworkId) {

     String sql = "SELECT COUNT(*) FROM artwork_comments "
                + "WHERE artwork_id = ?";

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