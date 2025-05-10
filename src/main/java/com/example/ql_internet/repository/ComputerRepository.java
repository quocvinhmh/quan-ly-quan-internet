package com.example.ql_internet.repository;

import com.example.ql_internet.model.Computer;
import com.example.ql_internet.util.BaseRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComputerRepository implements IComputerRepository {

    @Override
    public List<Computer> getComputers() {
        List<Computer> computers = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection()) {
            String sql = "SELECT name, state, price, time_use, time_end, service_name FROM computer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String status = resultSet.getString("state");
                double price = resultSet.getDouble("price");

                LocalDateTime timeUse = resultSet.getTimestamp("time_use") != null
                        ? resultSet.getTimestamp("time_use").toLocalDateTime()
                        : null;

                LocalDateTime timeEnd = resultSet.getTimestamp("time_end") != null
                        ? resultSet.getTimestamp("time_end").toLocalDateTime()
                        : null;

                String serviceName = resultSet.getString("service_name");

                Computer computer = new Computer(name, status, price);
                computer.setTimeUse(timeUse);
                computer.setService_name(serviceName);

                // ✅ Tính thời gian sử dụng (phút)
                if (timeUse != null && timeEnd != null) {
                    long duration = Duration.between(timeUse, timeEnd).toMinutes();
                    if (duration == 0) duration = 1; // ít nhất 1 phút
                }

                computers.add(computer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi không hiển thị máy tính: " + e.getMessage(), e);
        }
        return computers;
    }



    @Override
    public Computer addComputer(Computer computer) {
        try (Connection connection = BaseRepository.getConnection()) {
            String sql = "INSERT INTO computer (name, username, state, price) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setString(2, computer.getUsername());
            preparedStatement.setString(3, computer.getStatus().getMoTa());
            preparedStatement.setBigDecimal(4, computer.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return computer;
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi thêm máy tính: " + e.getMessage(), e);
        }
        return null;
    }

    public boolean updateComputer(Computer computer) {
        try (Connection connection = BaseRepository.getConnection()) {
            String sql = "UPDATE computer SET time_use = ?, price = ?, dongia = ?, state = ? WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 1. time_use
            if (computer.getTimeUse() != null) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(computer.getTimeUse()));
            } else {
                preparedStatement.setNull(1, Types.TIMESTAMP);
            }

            // 2. price (đơn giá mỗi phút)
            if (computer.getPrice() != null) {
                preparedStatement.setBigDecimal(2, computer.getPrice());
            } else {
                preparedStatement.setNull(2, Types.DECIMAL);
            }

            // 3. dongia (tổng tiền khi tắt máy)
            if (computer.getDongia() != null) {
                preparedStatement.setBigDecimal(3, computer.getDongia());
            } else {
                preparedStatement.setNull(3, Types.DECIMAL);
            }

            // 4. trạng thái
            preparedStatement.setString(4, computer.getStatus().getMoTa());

            // 5. WHERE name
            preparedStatement.setString(5, computer.getName());
            System.out.println("🧾 Lưu vào DB dongia = " + computer.getDongia());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("❌ Lỗi cập nhật máy tính: " + e.getMessage(), e);
        }
    }




    @Override
    public boolean deleteComputer(String username) {
        try {Connection connection = BaseRepository.getConnection();
            String sql = "DELETE FROM computer WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
           return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa hay ko tìm thấy tài khoản"+e.getMessage()+e);
        }
    }

    public Computer findByName(String name) {
        Computer computer = null;
        try (Connection connection = BaseRepository.getConnection()) {
            String sql = "SELECT name, username, state, price, time_use, dongia FROM computer WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String state = rs.getString("state");
                BigDecimal price = rs.getBigDecimal("price");
                BigDecimal dongia = rs.getBigDecimal("dongia");

                LocalDateTime timeUse = null;
                Timestamp timeUseTimestamp = rs.getTimestamp("time_use");
                if (timeUseTimestamp != null) {
                    timeUse = timeUseTimestamp.toLocalDateTime();
                }

                Computer.Status status = Computer.Status.fromMoTa(state);
                computer = new Computer(name, username, status, price);
                computer.setTimeUse(timeUse);
                computer.setDongia(dongia);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi tìm máy tính theo tên: " + e.getMessage(), e);
        }
        return computer;
    }

}
