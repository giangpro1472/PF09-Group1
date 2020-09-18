package project.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.persistance.Review;

import java.sql.Statement;

public class ReviewDAL {

    public Review getReview(final ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setReviewID(rs.getInt("review_id"));
        review.setUsername(rs.getString("username"));
        review.setScore(rs.getInt("user_score"));
        review.setPostDate(rs.getString("post_date"));
        review.setContent(rs.getString("content"));
        review.setGameID(rs.getInt("game_id"));
        return review;
    }

    public void insertReview(Review review) {
        String insert = "{CALL post_review(?,?,?,?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(insert);) {
            cstm.setString(1, review.getUsername());
            cstm.setInt(2, review.getGameID());
            cstm.setInt(3, review.getScore());
            cstm.setString(4, review.getContent());
            cstm.execute();
            System.out.println("(*) Post Review Successfully :)");
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }

    public List<Review> getReviewsByID(int gameID) {
        String sql = "{CALL getReviews(?)}";
        List<Review> list = new ArrayList<>();
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(sql)) {
            cstm.setInt(1, gameID);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                list.add(getReview(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return list;
    }

    public List<Review> getReviewsInDay() {
        String sql = "SELECT * FROM review_in_day";
        List<Review> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(getReview(rs));
            }
        } catch (SQLException e) {
            System.err.println("get review failed");
        }
        return list;
    }

    public void deleteReview(int reviewID) {
        String sql = "{CALL deleteReview(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(sql)) {
            cstm.setInt(1, reviewID);
            cstm.execute();
            System.out.println("(*) Delete Successfully :) ");
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public int checkReviewID(int reviewID) {
        String check = "{CALL checkReviewID(?)}";
        int result = 0;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setInt(1, reviewID);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("review_id");
            } else {
                System.out.println("(*) Sorry we cannot find this Review ID :( ");
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
    }

}
