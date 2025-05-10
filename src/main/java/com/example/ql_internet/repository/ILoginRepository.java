package com.example.ql_internet.repository;

import com.example.ql_internet.model.Login;

import java.sql.SQLException;
import java.util.List;

public interface ILoginRepository {
    List<Login> getLoginsByUsername(String username) throws SQLException;
    List<Login> getLoginsByPassword(String password);
    List<Login> getLogins();
    Login addLogin(Login login) throws SQLException;
    boolean byUsername(String username) throws SQLException;
}
