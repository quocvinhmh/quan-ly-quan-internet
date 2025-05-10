package com.example.ql_internet.repository;

import com.example.ql_internet.model.Login;
import com.example.ql_internet.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository implements ILoginRepository {

    @Override
    public List<Login> getLoginsByUsername(String username) throws SQLException {
        List<Login> logins = new ArrayList<>();
        Connection conn = BaseRepository.getConnection();
        try {
            String sql = "SELECT * FROM login WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String role = resultSet.getString("role");
                logins.add(new Login(user, pass, role));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return logins;
    }

    @Override
    public List<Login> getLoginsByPassword(String password) {
        List<Login> logins = new ArrayList<>();
        Connection conn = BaseRepository.getConnection();
        try {
            String sql = "SELECT * FROM login WHERE password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String role = resultSet.getString("role");
                logins.add(new Login(user, pass, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return logins;
    }

    @Override
    public List<Login> getLogins() {
        List<Login> logins = new ArrayList<>();
        Connection conn = BaseRepository.getConnection();
        try {
            String sql = "SELECT * FROM login";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String role = resultSet.getString("role");
                logins.add(new Login(user, pass, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return logins;
    }

    @Override
    public Login addLogin(Login login) throws SQLException {
        if (isUsernameExist(login.getUsername())) {
            throw new RuntimeException("⚠️ Tên đăng nhập đã tồn tại!");
        }

        String sql = "INSERT INTO login (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ps.setString(3, login.getRole());
            ps.executeUpdate();
            return login;
        }
    }

    @Override
    public boolean byUsername(String username) throws SQLException {
        Connection conn = BaseRepository.getConnection();
        try {
            String sql = "delete from login where username = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            return ps.executeUpdate() > 0 ;// true nếu tồn tại
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }



    public Login checkLogin(String username, String password) throws SQLException {
        Connection conn = BaseRepository.getConnection();
        Login login = null;

        try {
                String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String role = resultSet.getString("role");
                login = new Login(user, pass, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi không tìm thấy tài khoản: " + e.getMessage(), e);
        } finally {
            conn.close();
        }

        return login;
    }
    public boolean isUsernameExist(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM login WHERE username = ?";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

}
