package project.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.persistance.Images;


public class ImagesDAL {
    public Images getImages(final ResultSet rs) throws SQLException {
        Images images = new Images();
        images.setGameID(rs.getInt("game_id"));
        images.setImageName(rs.getString("image_name"));
        return images;
    }

    public List<Images> getAllImages() {
        String sql = "SELECT * FROM game_images";
        List<Images> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(getImages(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return list;
    }

    public void insertImage(Images image) {
        String insert = "{CALL insert_images(?,?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(insert);) {
            cstm.setInt(1, image.getGameID());
            cstm.setString(2, image.getImageName());
            cstm.execute();
            System.out.println("(*) Import images successfully :) ");

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }
}
