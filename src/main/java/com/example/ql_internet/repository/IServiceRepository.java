package com.example.ql_internet.repository;

import com.example.ql_internet.model.Service;

import java.util.List;

public interface IServiceRepository {
    List<Service> getServiceList();
    boolean addService(Service service);
    boolean deleteService(int id);
    boolean updateService(int id, Service service);

    Service findById(int id);
}
