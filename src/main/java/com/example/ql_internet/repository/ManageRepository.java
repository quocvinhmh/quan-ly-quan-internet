package com.example.ql_internet.repository;

import com.example.ql_internet.model.Manage;
import com.example.ql_internet.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageRepository {

    public boolean addManage(Manage manage) throws SQLException {
        String sql = "INSERT INTO manage (name,username,role) VALUES (?, ?,?)";
        Connection connection = BaseRepository.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, manage.getName());
            ps.setString(2, manage.getUsername());
            ps.setString(3, manage.getRole());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                throw new RuntimeException("❌ Username đã tồn tại", e);
            }
            throw new RuntimeException("Lỗi thêm quản lý: " + e.getMessage(), e);
        }
    }

    public List<Manage> getManage() throws SQLException {
        List<Manage> list = new ArrayList<>();
        Connection connection = BaseRepository.getConnection();
        try {
            String sql = "SELECT m.id, m.name, m.username, l.role, l.password FROM manage m JOIN login l ON m.username = l.username";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");

                Manage manage = new Manage(id, name, username, role, password);
                list.add(manage);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi không thể hiển thị danh sách quản lý: " + e.getMessage(), e);
        } finally {
            connection.close();

        }
        return list;
    }


    public boolean delManageByid(int id) throws SQLException {
        String sql = "DELETE FROM manage WHERE id = ?";
        Connection connection = BaseRepository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
