package example.jdbc.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import example.jdbc.bean.Article;
import example.jdbc.utils.JDBCUtils;

public class ArticleDao implements DaoInterface<Article, Integer> {

    @Override
    public Collection<Article> retrieveAll() {
        Collection<Article> articles = new ArrayList<>();
        String sqlQuery = "SELECT art_id, art_name, category, date_created, creator_name FROM article_master";
        
        try (Connection conn = JDBCUtils.getConnetion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            while (rs.next()) {
                int articleId = rs.getInt("art_id");
                String articleName = rs.getString("art_name");
                String category = rs.getString("category");
                LocalDate dateCreated = rs.getDate("date_created").toLocalDate();
                String creatorName = rs.getString("creator_name");

                Article article = new Article(articleId, articleName, category, dateCreated, creatorName);
                articles.add(article);
            }

            if (articles.isEmpty()) {
                System.out.println("No articles found in the database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }


    @Override
    public Article retrieveOne(Integer id) {
        String sqlQuery = "SELECT art_id, art_name, category, date_created, creator_name FROM article_master WHERE art_id = ?";
        try (Connection conn = JDBCUtils.getConnetion();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int articleId = rs.getInt("art_id");
                String name = rs.getString("art_name");
                String category = rs.getString("category");
                LocalDate dateCreated = rs.getDate("date_created").toLocalDate();
                String creatorName = rs.getString("creator_name");

                return new Article(id, name, category, dateCreated, creatorName);
            } else {
                System.out.println("Article with ID " + id + " not found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
            return null;
        }
    }


    @Override
    public void create(Article newArticle) {
        if (newArticle == null) {
            System.out.println("Cannot add null article.");
            return;
        }

        String sqlQuery = "INSERT INTO article_master (art_name, category, date_created, creator_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnetion();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            pstmt.setString(1, newArticle.getName());
            pstmt.setString(2, newArticle.getCategory());
            pstmt.setDate(3, java.sql.Date.valueOf(newArticle.getDateCreated()));
            pstmt.setString(4, newArticle.getCreatorName());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Article created successfully.");
            } else {
                System.out.println("Failed to create the article.");
            }
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }


    @Override
    public void update(Article modifyArticle) {
        // Validate if the date_created is in the past
        if (modifyArticle.getDateCreated().isAfter(LocalDate.now())) {
            System.out.println("Error: Date created cannot be in the future.");
            return;
        }

        String sqlQuery = "UPDATE article_master SET art_name = ?, category = ?, date_created = ?, creator_name = ? WHERE art_id = ?";
        try (Connection conn = JDBCUtils.getConnetion();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            pstmt.setString(1, modifyArticle.getName());
            pstmt.setString(2, modifyArticle.getCategory());
            pstmt.setDate(3, java.sql.Date.valueOf(modifyArticle.getDateCreated()));
            pstmt.setString(4, modifyArticle.getCreatorName());
            pstmt.setInt(5, modifyArticle.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Article updated successfully.");
            } else {
                System.out.println("Failed to update the article.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e1) {
			e1.printStackTrace();
		}
    }

    @Override
    public void delete(Integer id) {
        String sqlQuery = "DELETE FROM article_master WHERE art_id = ?";
        try (Connection conn = JDBCUtils.getConnetion();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Article deleted successfully.");
            } else {
                System.out.println("Article with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e1) {
			e1.printStackTrace();
		}
    }
}