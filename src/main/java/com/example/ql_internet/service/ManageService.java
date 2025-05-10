package com.example.ql_internet.service;

import com.example.ql_internet.model.Manage;

import java.util.List;

public class ManageService implements IManageService {
 ManageService manageService = new ManageService();
    @Override
    public List<Manage> getManage() {
        return manageService.getManage();
    }

    @Override
    public boolean addManage(Manage manage) {
        return  manageService.addManage(manage);
    }

    @Override
    public boolean delManageByid(int id) {
        return manageService.delManageByid(id);
    }
}
