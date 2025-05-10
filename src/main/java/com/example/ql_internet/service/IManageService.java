package com.example.ql_internet.service;

import com.example.ql_internet.model.Manage;

import java.util.List;

public interface IManageService {
    List<Manage> getManage();
    boolean addManage(Manage manage);
    boolean delManageByid(int id);
}
