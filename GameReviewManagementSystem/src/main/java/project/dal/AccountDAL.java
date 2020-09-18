package project.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.persistance.Account;

import java.sql.Statement;

public class AccountDAL {
    
    public Account getAccount(final ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("pass_word"));
        account.setEmail(rs.getString("email"));
        account.setGender(rs.getString("gender"));
        account.setAddress(rs.getString("address"));
        account.setPhone(rs.getString("phone"));
        account.setAccount_status(rs.getString("account_status"));
        account.setCreate_date(rs.getString("create_date"));
        account.setUser_title(rs.getString("user_title"));
        account.setRole_id(rs.getInt("role_id"));
        account.setLast_date(rs.getString("last_date"));
        return account;
    }

    public List<Account> getAllActiveUser() {
        String sql = "SELECT * FROM active_users";
        List<Account> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(getAccount(rs));
            }
        } catch (SQLException e) {
            System.err.println("get account failed");
        }
        return list;
    }

    public List<Account> getAllDisableUser() {
        String sql = "SELECT * FROM disable_users";
        List<Account> list = new ArrayList<>();
        try (Connection con = DbUtil.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(getAccount(rs));
            }
        } catch (SQLException e) {
            System.err.println("get account failed");
        }
        return list;
    }

    public void insertAccount(Account account) {
        String insert = "{CALL register(?,?,?,?,?,?,?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(insert);) {
            cstm.setString(1, account.getUsername());
            cstm.setString(2, account.getPassword());
            cstm.setString(3, account.getEmail());
            cstm.setString(4, account.getGender());
            cstm.setString(5, account.getAddress());
            cstm.setString(6, account.getPhone());
            cstm.setInt(7, account.getRole_id());
            cstm.execute();
            System.out.println("(*) Register Succsesfully :)");

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void disableAccount(String username) {
        String disable = "{CALL disable_account(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(disable);) {
            cstm.setString(1, username);
            cstm.execute();
            System.out.println("(*) Deactivate Account Succsesfully :) ");

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void unlockAccount(String username) {
        String unlock = "{CALL unlock_account(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(unlock);) {
            cstm.setString(1, username);
            cstm.execute();
            System.out.println("(*) Unlock Account Succsesfully :) ");

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    
    public void editProfile(String username, String email, String gender, String address, String phone) {
        String update = "{CALL edit_profile(?,?,?,?,?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(update);) {
            cstm.setString(1, username);
            cstm.setString(2, email);
            cstm.setString(3, gender);
            cstm.setString(4, address);
            cstm.setString(5, phone);
            cstm.execute();
            System.out.println("(*) Update Successfully :)");

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public boolean checkAccount(String username, String password) {
        boolean result = false;
        String check = "{CALL checkAccount(?,?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, username);
            cstm.setString(2, password);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                result = true; 
            } else{
                System.out.println("(*) Error >:( ");
            }
            return result;
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
        
    }

    public int checkRole(String username) {
        int result = 0;
        String check = "{CALL checkRole(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, username);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("role_id"); 
            } else{
                System.out.println("(*) Cannot get role-id >:( ");
            }
            return result;
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
        
    }

    public boolean checkExistUsername(String username) {
        String check = "{CALL checkUsername(?)}";
        boolean result = false;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, username);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                System.out.println("(*) This username already exist :( ");
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

    public boolean checkExistEmail(String email) {
        String check = "{CALL checkEmail(?)}";
        boolean result = false;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, email);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                System.out.println("(*) This email already exist :( ");
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

    public boolean checkExistPhone(String phone) {
        String check = "{CALL checkPhone(?)}";
        boolean result = false;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, phone);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                System.out.println("(*) This phone is already exist :( ");
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

    public boolean checkUsername(String username) {
        String check = "{CALL checkUsername(?)}";
        boolean result = false;
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, username);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                System.out.println("(*) Error :( ");
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
    }

    public String checkStatus(String username) {
        String check = "{CALL checkStatus(?)}";
        String result = "";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(check);) {
            cstm.setString(1, username);
            cstm.execute();
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                result = rs.getString("account_status");
            } else {
                System.out.println("(*) Error :( ");
            }
            return result;
        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return result;
        }
    }
  
    public void lastLogin(String username) {
        String last = "{CALL last_login(?)}";
        try (CallableStatement cstm = DbUtil.getConnection().prepareCall(last);) {
            cstm.setString(1, username);
            cstm.execute();

        } catch (SQLException e) {
            System.out.println("SQLeception: " + e.getMessage());
            System.out.println("SQLrate: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}
