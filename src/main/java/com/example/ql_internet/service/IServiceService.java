package com.example.ql_internet.service;

import com.example.ql_internet.model.Service;

import java.util.List;

public interface IServiceService {
    List<Service> getServiceList();
    Service addService(Service service);
    boolean deleteService(int id);
    boolean updateService(int id, Service service);
    Service findById(int id);
}
