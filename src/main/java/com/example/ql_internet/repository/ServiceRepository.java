package com.example.ql_internet.repository;

import com.example.ql_internet.model.Service;
import com.example.ql_internet.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository implements IServiceRepository {
    @Override
    public List<Service> getServiceList() {
        List<Service> serviceList;
        try {
            Connection connection = BaseRepository.getConnection();
            String sql = "select * from service";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            serviceList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double servicePrice = resultSet.getDouble("price");
                Service service = new Service(id, name, servicePrice);
                serviceList.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceList;
    }

    @Override
    public boolean addService(Service service) {
        try {
            Connection connection = BaseRepository.getConnection();
            String sql = "insert into service(name,price) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getPrice());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteService(int id) {
        try {
            Connection connection = BaseRepository.getConnection();
            String sql = "delete from service where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateService(int id, Service service) {
        try {
            Connection connection = BaseRepository.getConnection();
            String sql = "update service set name = ?, price = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.setInt(3, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Service findById(int id) {
        Service service = null;
        try {
            Connection connection = BaseRepository.getConnection();
            String sql = "SELECT * FROM service WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                service = new Service(id, name, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return service;
    }
}