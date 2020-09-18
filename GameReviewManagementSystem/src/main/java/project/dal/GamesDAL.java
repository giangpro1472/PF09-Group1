package project.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.persistance.Games;

public class GamesDAL {

    public Games getGames(final ResultSet rs) throws SQLException {
        Games games = new Games();
        games.setGameID(rs.getInt("game_id"));
        games.setGameName(rs.getString("game_name"));
        games.setGenre(rs.getString("genre_name"));
        games.setGenreID(rs.getInt("genre_id"));
        games.setDevelopers(rs.getString("developers"));
        games.setReleaseDate(rs.getString("release_date"));
        games.setScore(rs.getDouble("score"));
        return games;
    }

    public List<Games> getAllGames() {
        String sql = "SELECT * FROM games_scores";
        List<Games> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(getGames(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return list;
    }

    public List<Games> rankingGames() {
        String sql = "SELECT * FROM ranking_games";
        List<Games> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(getGames(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return list;
    }

    public boolean insertGame(Games games) {
        String insert = "{CALL insert_game(?,?,?,?)}";
        boolean result = false;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(insert);) {

            cstm.setString(1, games.getGameName());
            cstm.setInt(2, games.getGenreID());
            cstm.setString(3, games.getDevelopers());
            cstm.setString(4, games.getReleaseDate());
            if (!cstm.execute()) {
                System.out.println("(*) Game successfully added to the queue :) ");
                System.out.println("(*) Now please add more images to finish post the game!");
                result = true;
            } else {
                System.out.println("(*) Something has went wrong!");
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
    }

    public void deleteGame(int gameID) {
        String sql = "{CALL deleteGame(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(sql)) {
            cstm.setInt(1, gameID);
            cstm.execute();
            System.out.println("(*) Delete Successfully :) ");
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public int getGameID() {
        int result = 0;
        String sql = "SELECT * FROM getGameID;";
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt("game_id");
            } else {
                System.out.println("error");
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
    }

    public List<Games> searchByName(String gameName) {
        String sql = "{CALL search_name(?)}";
        List<Games> list = new ArrayList<>();
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(sql)) {
            cstm.setString(1, gameName);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                list.add(getGames(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return list;
    }

    public List<Games> searchByGenre(int genre) {
        String sql = "{CALL search_genre(?)}";
        List<Games> list = new ArrayList<>();
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(sql)) {
            cstm.setInt(1, genre);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                list.add(getGames(rs));
            }
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return list;
    }

    public Games getGameByID(int gameID) {
        Games games = new Games();
        String sql = "{CALL searchGameID(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(sql)) {
            cstm.setInt(1, gameID);
            ResultSet rs = cstm.executeQuery();
            rs.next();
            games.setGameID(rs.getInt("game_id"));
            games.setGameName(rs.getString("game_name"));
            games.setGenre(rs.getString("genre_name"));
            games.setGenreID(rs.getInt("genre_id"));
            games.setDevelopers(rs.getString("developers"));
            games.setReleaseDate(rs.getString("release_date"));
            games.setScore(rs.getDouble("score"));

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            System.err.println("get games failed");
        }
        return games;
    }

    public int getSearchNameResult(String gameName) {
        String search = "{CALL search_name(?)}";
        int count = 0;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(search);) {
            cstm.setString(1, gameName);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return count;
        }
    }

    public int getGenreSearchResult(int id) {
        String search = "{CALL search_genre(?)}";
        int count = 0;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(search);) {
            cstm.setInt(1, id);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return count;
        }
    }

    public int checkGameID(int gameID) {
        String check = "{CALL checkGamesID(?)}";
        int result = 0;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setInt(1, gameID);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("game_id");
            } else{
                System.out.println("(*) Sorry we cannot find this Game-ID :(");
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
    }

    public boolean checkExistGameName(String gameName) {
        String check = "{CALL checkGameName(?)}";
        boolean result = false;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, gameName);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                System.out.println("(*) This game already exist :( ");
            } else {
                result = true;
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
