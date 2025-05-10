package com.example.ql_internet.service;

import com.example.ql_internet.model.Service;

import java.util.List;

public class ServiceService implements IServiceService {
IServiceService serviceService = new ServiceService();

    @Override
    public List<Service> getServiceList() {
        return serviceService.getServiceList();
    }

    @Override
    public Service addService(Service service) {
        return serviceService.addService(service);
    }

    @Override
    public boolean deleteService(int id) {
        return serviceService.deleteService(id);
    }

    @Override
    public boolean updateService(int id, Service service) {
        return serviceService.updateService(id, service);
    }

    @Override
    public Service findById(int id) {
        return serviceService.findById(id);
    }
}
