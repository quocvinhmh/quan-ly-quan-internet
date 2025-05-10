package com.example.ql_internet.repository;

import com.example.ql_internet.model.Manage;

import java.sql.SQLException;
import java.util.List;

public interface IManageRepository {
    List<Manage> getManage() throws SQLException;
    boolean addManage(Manage manage) throws SQLException;
    boolean delManageByid(int id) throws SQLException;
}
